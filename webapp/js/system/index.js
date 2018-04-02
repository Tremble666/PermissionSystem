/* zTree插件加载目录的处理  */
var zTree;

var setting = {
	callback: {
		beforeClick: function(treeId, treeNode) {
			$("#rightMain").attr("src",treeNode.path);
			return false;
		}
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	}
};

function loadMenu(resourceType, treeObj){
	menus={
			"business":[{"isParent":true,"name":"业务模块","sn":"business"}],
			"systemManage":[{"id":1,"pId":0,"name":"系统管理"},
			                {"id":2,"pId":1,"name":"员工管理","path":"/employee.action"},
			                {"id":3,"pId":1,"name":"部门管理","path":"/department.action"},
			                {"id":4,"pId":1,"name":"权限管理","path":"/permission.action"},
			                {"id":5,"pId":1,"name":"角色管理","path":"/role.action"}],
			"charts":[{"isParent":true,"name":"报表","sn":"charts"}]
	};
    // 将返回的数据赋给zTree
    $.fn.zTree.init($("#dleft_tab1"),setting, menus[resourceType]);
    zTree = $.fn.zTree.getZTreeObj("dleft_tab1");
}

//加载当前日期
function loadDate(){
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."	+ myDay;
}

/**
 * 隐藏或者显示侧边栏
 * 
 **/
function switchSysBar(flag){
	var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
	if( flag==true ){	// flag==true
		left_menu_cnt.show(500, 'linear');
		side.css({width:'280px'});
		$('#top_nav').css({width:'77%', left:'304px'});
    	$('#main').css({left:'280px'});
	}else{
        if ( left_menu_cnt.is(":visible") ) {
			left_menu_cnt.hide(10, 'linear');
			side.css({width:'60px'});
        	$('#top_nav').css({width:'100%', left:'60px', 'padding-left':'28px'});
        	$('#main').css({left:'60px'});
        	$("#show_hide_btn").find('img').attr('src', '/images/common/nav_show.png');
        } else {
			left_menu_cnt.show(500, 'linear');
			side.css({width:'280px'});
			$('#top_nav').css({width:'77%', left:'304px', 'padding-left':'0px'});
        	$('#main').css({left:'280px'});
        	$("#show_hide_btn").find('img').attr('src', '/images/common/nav_hide.png');
        }
	}
}

$(function(){
	loadDate();
	
	$('#TabPage2 li').click(function(){
		loadMenu($(this).data('rootmenu'));
		
		var index = $(this).index();
		$(this).find('img').attr('src', '/images/common/'+ (index+1) +'_hover.jpg');
		$(this).css({background:'#fff'});
		$('#nav_module').find('img').attr('src', '/images/common/module_'+ (index+1) +'.png');
		$('#TabPage2 li').each(function(i, ele){
			if( i!=index ){
				$(ele).find('img').attr('src', '/images/common/'+ (i+1) +'.jpg');
				$(ele).css({background:'#044599'});
			}
		});
		
		// 显示侧边栏
		switchSysBar(true);
	});
	loadMenu('business');
	
	// 显示隐藏侧边栏
	$("#show_hide_btn").click(function() {
        switchSysBar();
    });
});
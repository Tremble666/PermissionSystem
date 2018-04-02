$(function(){
	$("#all").click(function(){
		selectOrClearAllCheckbox(this);
	});
	
	$(".btn_batchDelete").click(function(){
		if($("input[name='IDCheck']:checked").size()<=0){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'至少选择一条', ok:true,});
			return;
		}
		var selected=[];
		var refreshLocation=$(this).attr("data-refresh-href");
		$("input[name='IDCheck']:checked").each(function(index,item){
			selected[index]=$(item).val();
		});
		$.ajax({
	        url : $(this).attr("data-href"),
	        data: $.param({"ids":selected},true),
	        type : "POST",
	        dataType : 'json',
	        success : function (result){
	        	art.dialog({icon:'info', title:'提示', drag:false, resize:false, content:'批量删除成功', 
	        		ok:function(){window.location.href=refreshLocation}});
	        }
		});
	});
	
	if($("#editForm").size()>0){
		$("#editForm").validate({
			rules:{
				"employee.name":{
					required:true,
					minlength: 4
				},
				"employee.password":{
					required:true,
					minlength: 4
				},
				repassword:{
					equalTo: "#password"
				},
				"employee.email":"email",
				"employee.age":{
					digits: true,
					range: [18, 60]
				}
			},
			messages:{
				"employee.name":{
					required:"用户名不能为空!",
					minlength:"用户名长度至少4位"
				},
				"employee.password":{
					required:"密码不能为空!",
					minlength:"密码长度至少4位"
				},
				repassword:"两次输入的密码不一致",
				"employee.email":"请输入正确格式的EMAIL",
				"employee.age":{
					digits:"请输入合法的年龄",
					range:"年龄范围在18到60岁"
				}
			}
		});
	}
	
	$("#select").click(function(){
		$(".all_roles option:selected").appendTo(".selected_roles");
	});
	
	$("#selectAll").click(function(){
		$(".all_roles option").appendTo(".selected_roles");
	});
	
	$("#deselect").click(function(){
		$(".selected_roles option:selected").appendTo(".all_roles");
	});
	
	$("#deselectAll").click(function(){
		$(".selected_roles option").appendTo(".all_roles");
	});
	
	$("#editForm").submit(function(){
		if($(".selected_roles option:selected").size()!=$(".selected_roles option")){
			$(".selected_roles option").prop("selected",true);
		}
	});
	
	if($(".selected_roles option").size()>0){
		var arr=$.map($(".selected_roles option"),function(option){
			return $(option).attr("value");
		});
		$(".all_roles option").filter(function(index){
			return $.inArray($(this).attr("value"),arr)>=0;
		}).remove();
	}
});
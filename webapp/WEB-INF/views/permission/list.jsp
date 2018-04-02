<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<link rel="/stylesheet" type="text/css" href="style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript" src="/js/artDialog/artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/common_page.js"></script>
<script type="text/javascript">
$(function(){
	$(".loadpermission_btn").click(function(){
		jQuery.ajax({
			url:$(this).data("href"),
	        type : "POST",
	        dataType : 'json',
	        success : function (result){
	        	art.dialog({icon:'succeed', title:'提示', drag:false, resize:false, content:'重新加载成功', 
	        		ok:function(){window.location.reload();}});
	        }
		});
	});
	//翻页
	$(".btn_page")
			.click(
					function() {
						$(":input[name='qo.currentPage']")
								.val(
										$(this).data("page")
												|| $(
														":input[name='qo.currentPage']")
														.val());
						$("#searchForm").submit();
					});
	//每页多少条数据
	$(":input[name='qo.pageSize']").change(function() {
		$("#searchForm").submit();
	});
})

</script>
<title>PSS-权限管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<s:form id="searchForm" action="permission" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<!-- <input type="button" value="查询" class="ui_input_btn01 page_btn"/> -->
							<input type="button" value="加载权限" class="ui_input_btn01 loadpermission_btn" data-href='<s:url action="permission_reload" />'/>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"></th>
							<th>编号</th>
							<th>权限表达式</th>
							<th>权限名称</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.listData">
								<tr>
									<td></td>
									<td><s:property value="id"/></td>
									<td style="text-align:left;padding-left: 50px;"><s:property value="expression"/></td>
									<td><s:property value="name"/></td>
									<td>
										<s:a action="permission_delete"><s:param name="permission.id" value="id"/>删除</s:a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<%@include file="/WEB-INF/views/common/common_page.jsp" %>
			</div>
		</div>
	</s:form>
</body>
</html>

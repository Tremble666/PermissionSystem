<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>PSS-账户管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
<script type="text/javascript">
	$(function() {
		$(".btn_input").click(function() {
			window.location.href = $(this).data("url");
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
	});
</script>
</head>
<body>
	
	<form id="searchForm" action="/employee" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							姓名/邮箱<s:textfield  name="qo.keyword" class="ui_input_txt02"/>所属部门
							<s:select list="#depts" name="qo.deptId" 
								headerKey="-1" headerValue="全部部门"
								listKey="id" listValue="name" cssClass="ui_select01"/>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page" />
							<input type="button" value="新增" class="ui_input_btn01 btn_input"
								data-url='<s:url namespace="/" action="employee_input"/>' />
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%"
						align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>用户名</th>
							<th>EMAIL</th>
							<th>年龄</th>
							<th>所属部门</th>
							<th>角色</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.listData">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" /></td>
									<td><s:property value="id" /></td>
									<td><s:property value="name" /></td>
									<td><s:property value="email" /></td>
									<td><s:property value="age" /></td>
									<td><s:property value="dept.name" /></td>
									<td><s:property value="roleNames" /></td>
									<td><s:a namespace="/" action="employee_input">
											<s:param name="employee.id" value="id" />编辑
									</s:a> <s:a namespace="/" action="employee_delete">
											<s:param name="employee.id" value="id" />删除
									</s:a></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
					<!-- 分页条信息，用包含指令 -->
					<%@include file="/WEB-INF/views/common/common_page.jsp" %>
				
			</div>
		</div>
	</form>
</body>
</html>

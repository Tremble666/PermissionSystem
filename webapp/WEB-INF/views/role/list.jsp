<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript" src="/js/artDialog/artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/common_page.js"></script>
<title>PSS-角色管理</title>
<style>
	.alt td{ background:black !important;}
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
	<s:form id="searchForm" action="role" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<!-- <input type="button" value="查询" class="ui_input_btn01 page_btn"/> -->
							<input type="button" value="新增" class="ui_input_btn01 btn_input"
								data-url='<s:url namespace="/" action="role_input"/>' />
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
							<th>角色名称</th>
							<th>角色代码</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.listData">
								<tr>
									<td></td>
									<td><s:property value="id"/></td>
									<td><s:property value="name"/></td>
									<td><s:property value="sn"/></td>
									<td>
										<s:a action="role_input"><s:param name="role.id" value="id"/>编辑</s:a>
										<s:a action="role_delete"><s:param name="role.id" value="id"/>删除</s:a>
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

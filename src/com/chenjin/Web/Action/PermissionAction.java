package com.chenjin.Web.Action;

import lombok.Getter;
import lombok.Setter;

import com.chenjin.domain.Employee;
import com.chenjin.domain.Permission;
import com.chenjin.query.EmployeeQueryObject;
import com.chenjin.query.IQuery;
import com.chenjin.query.QueryObject;
import com.chenjin.service.IDepartmentService;
import com.chenjin.service.IEmployeeService;
import com.chenjin.service.IPermissionService;
import com.chenjin.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class PermissionAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Setter
	private IPermissionService permissionService;

	@Getter
	private Permission permission = new Permission();
	
	@Getter
	private IQuery qo = new QueryObject();
	
	@RequiredPermission(value="权限列表")
	public String execute() throws Exception {
		putContext("pageResult", permissionService.advancedPageQuery(qo));
		return LIST;
	}

	// 删除
	@RequiredPermission(value="权限删除")
	public String delete() throws Exception {
		permissionService.delete(permission.getId());
		return SUCCESS;
	}
	
	@RequiredPermission("权限加载")
	public String reload() throws Exception {
		permissionService.reload();
		return LIST;
	}
	
}

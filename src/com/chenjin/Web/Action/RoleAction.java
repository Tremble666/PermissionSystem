package com.chenjin.Web.Action;

import lombok.Getter;
import lombok.Setter;

import com.chenjin.domain.Role;
import com.chenjin.query.QueryObject;
import com.chenjin.query.IQuery;
import com.chenjin.service.IPermissionService;
import com.chenjin.service.IRoleService;
import com.chenjin.util.RequiredPermission;

public class RoleAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Setter
	private IRoleService roleService;
	
	@Setter
	private IPermissionService permissionService;
	
	@Getter
	private Role role = new Role();
	
	@Getter
	private IQuery qo = new QueryObject();
	
	@RequiredPermission(value="角色列表")
	public String execute() throws Exception {
		putContext("pageResult", roleService.advancedPageQuery(qo));
		return LIST;
	}

	// 进入录入界面
	@RequiredPermission(value="角色编辑")
	public String input() throws Exception {
		putContext("permissions", permissionService.list());
		if (role.getId() != null) {
			role = roleService.get(role.getId());
		}
		return INPUT;
	}

	// 新增或编辑界面
	@RequiredPermission(value="角色保存或更新")
	public String saveOrUpdate() throws Exception {
		if (role.getId() == null) {
			roleService.save(role);
		} else {
			roleService.update(role);
		}
		return SUCCESS;
	}

	// 删除
	@RequiredPermission("角色删除")
	public String delete() throws Exception {
		roleService.delete(role.getId());
		return SUCCESS;
	}
	
	//为防止编辑员工出现密码丢失
	public void prepareSaveOrUpdate() throws Exception {
		if(role.getId() != null){
			role = roleService.get(role.getId());
		}
		//因为使用的是list，且使用的是二次参数拦截器，因此在第一个参数注入后要清空集合，否则会重复添加
		role.getPermissions().clear();
	}
}

package com.chenjin.Web.Action;

import lombok.Getter;
import lombok.Setter;

import com.chenjin.domain.Employee;
import com.chenjin.query.EmployeeQueryObject;
import com.chenjin.query.IQuery;
import com.chenjin.service.IDepartmentService;
import com.chenjin.service.IEmployeeService;
import com.chenjin.service.IRoleService;
import com.chenjin.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class EmployeeAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Setter
	private IEmployeeService empService;
	
	@Setter
	private IDepartmentService deptService;
	
	@Setter
	private IRoleService roleService;

	@Getter
	private Employee employee = new Employee();
	
	@Getter
	private IQuery qo = new EmployeeQueryObject();
	
	
	@RequiredPermission(value="员工列表")
	public String execute() throws Exception {
		//System.out.println(qo);
		//putContext("emps", empService.list());
		putContext("pageResult", empService.advancedPageQuery(qo));
		putContext("depts", deptService.list());
		return LIST;
	}

	// 进入录入界面
	@RequiredPermission(value="员工编辑")
	public String input() throws Exception {
		putContext("depts", deptService.list());
		putContext("roles", roleService.list());
		if (employee.getId() != null) {
			employee = empService.get(employee.getId());
		}
		return INPUT;
	}

	// 新增或编辑界面
	@RequiredPermission(value="员工保存或更新")
	public String saveOrUpdate() throws Exception {
		if (employee.getId() == null) {
			empService.save(employee);
		} else {
			empService.update(employee);
		}
		return SUCCESS;
	}

	// 删除
	@RequiredPermission("员工删除")
	public String delete() throws Exception {
		empService.delete(employee.getId());
		return SUCCESS;
	}
	
	//为防止编辑员工出现密码丢失
	public void prepareSaveOrUpdate() throws Exception {
		if(employee.getId() != null){
			employee = empService.get(employee.getId());
			employee.setDept(null);//打破与部门的关系，防止编辑出现修改部门导致的异常
		}
		//防止角色的重复添加，因为使用的是二次参数拦截器
		employee.getRoles().clear();
	}
}

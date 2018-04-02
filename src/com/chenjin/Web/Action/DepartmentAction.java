package com.chenjin.Web.Action;

import lombok.Getter;
import lombok.Setter;

import com.chenjin.domain.Department;
import com.chenjin.query.EmployeeQueryObject;
import com.chenjin.query.IQuery;
import com.chenjin.query.QueryObject;
import com.chenjin.service.IDepartmentService;
import com.chenjin.service.IEmployeeService;
import com.chenjin.service.Impl.DepartmentService;
import com.chenjin.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DepartmentAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IDepartmentService deptService;
	
	@Getter
	private Department department = new Department();
	
	@Getter
	private IQuery qo = new QueryObject();

	// 进入列表界面
	@RequiredPermission(value="部门列表")
	public String execute() throws Exception {
		putContext("departments", deptService.list());
		putContext("pageResult", deptService.advancedPageQuery(qo));
		return LIST;
	}

	// 进入录入界面
	@RequiredPermission(value="部门编辑")
	public String input() throws Exception {
		System.out.println(department);
		if(department.getId() != null){
			department = deptService.get(department.getId());
		}
		return INPUT;
	}
	
	//新增或编辑界面
	@RequiredPermission(value="部门保存或更新")
	public String saveOrUpdate() throws Exception {
		if(department.getId() == null){
			deptService.save(department);
		}else{
			deptService.update(department);
		}
		return SUCCESS;
	}
	
	//删除
	@RequiredPermission(value="部门删除")
	public String delete() throws Exception {
		deptService.delete(department.getId());
		return SUCCESS;
	}
}

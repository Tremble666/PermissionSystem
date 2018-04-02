package com.chenjin.service.Impl;

import java.util.List;

import lombok.Setter;

import com.chenjin.dao.IDepartmentDAO;
import com.chenjin.dao.IEmployeeDAO;
import com.chenjin.domain.Department;
import com.chenjin.domain.Employee;
import com.chenjin.query.IQuery;
import com.chenjin.query.PageResult;
import com.chenjin.service.IDepartmentService;
import com.chenjin.service.IEmployeeService;

public class DepartmentService implements IDepartmentService{
	@Setter
	private IDepartmentDAO deptDAO;
	
	public void save(Department e) {
		deptDAO.save(e);
	}

	public void update(Department e) {
		deptDAO.update(e);
	}

	public void delete(Long id) {
		deptDAO.delete(id);
	}

	public Department get(Long id) {
		return deptDAO.get(id);
	}

	public List<Department> list() {
		return deptDAO.list();
	}

	@Override
	public PageResult advancedPageQuery(IQuery qo) {
		return deptDAO.advancedPageQuery(qo);
	}
}

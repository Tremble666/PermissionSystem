package com.chenjin.service;

import java.util.List;

import com.chenjin.domain.Department;
import com.chenjin.domain.Employee;
import com.chenjin.query.IQuery;
import com.chenjin.query.PageResult;

public interface IDepartmentService {
	void save(Department e);
	void update(Department e);
	void delete(Long id);
	
	Department get(Long id);
	List<Department> list();
	
	//高级查询+分页查询
	PageResult advancedPageQuery(IQuery qo);
}

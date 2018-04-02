package com.chenjin.service;

import java.util.List;

import com.chenjin.domain.Employee;
import com.chenjin.query.IQuery;
import com.chenjin.query.PageResult;

public interface IEmployeeService {
	void save(Employee e);
	void update(Employee e);
	void delete(Long id);
	
	Employee get(Long id);
	List<Employee> list();
	
	PageResult advancedPageQuery(IQuery qo);
	
	List<Employee> queryForList(String condition,Object [] params,int currentPage,int pageSize);
	//用户的登陆业务
	void login(String userName, String password);
	
}

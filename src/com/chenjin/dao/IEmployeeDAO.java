package com.chenjin.dao;

import java.util.List;

import com.chenjin.domain.Employee;
import com.chenjin.query.IQuery;
import com.chenjin.query.PageResult;

public interface IEmployeeDAO extends IGenericDAO<Employee>{
	//用户的登陆信息检测
	Employee checkLogin(String userName, String password);
}

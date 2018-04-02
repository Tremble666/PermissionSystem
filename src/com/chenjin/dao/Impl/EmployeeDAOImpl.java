package com.chenjin.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com._520it.pss.util.MD5;
import com.chenjin.dao.IEmployeeDAO;
import com.chenjin.domain.Employee;
import com.chenjin.query.EmployeeQueryObject;
import com.chenjin.query.IQuery;
import com.chenjin.query.PageResult;

public class EmployeeDAOImpl extends GenericDAOImpl<Employee> implements
		IEmployeeDAO {
	//检查登陆
	public Employee checkLogin(String userName, String password) {
		password = MD5.encode(password);
		Employee e = queryForObject("obj.name = ? and obj.password = ?", userName,password);
		System.out.println(e);
		return e;
	}
}

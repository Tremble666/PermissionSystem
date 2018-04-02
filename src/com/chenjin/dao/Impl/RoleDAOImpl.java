package com.chenjin.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.chenjin.dao.IEmployeeDAO;
import com.chenjin.dao.IRoleDAO;
import com.chenjin.domain.Employee;
import com.chenjin.domain.Role;
import com.chenjin.query.EmployeeQueryObject;
import com.chenjin.query.IQuery;
import com.chenjin.query.PageResult;

public class RoleDAOImpl extends GenericDAOImpl<Role> implements
		IRoleDAO {
}

package com.chenjin.service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Setter;

import com._520it.pss.util.MD5;
import com.chenjin.dao.IEmployeeDAO;
import com.chenjin.domain.Employee;
import com.chenjin.domain.Permission;
import com.chenjin.domain.Role;
import com.chenjin.query.IQuery;
import com.chenjin.query.PageResult;
import com.chenjin.service.IEmployeeService;
import com.chenjin.util.UserContext;

public class EmployeeService implements IEmployeeService{
	@Setter
	private IEmployeeDAO empDAO;
	
	public void save(Employee e) {
		//对密码进行加密
		e.setPassword(MD5.encode(e.getPassword()));
		empDAO.save(e);
	}

	public void update(Employee e) {
		empDAO.update(e);
	}

	public void delete(Long id) {
		empDAO.delete(id);
	}

	public Employee get(Long id) {
		return empDAO.get(id);
	}

	public List<Employee> list() {
		return empDAO.list();
	}

	@Override
	public PageResult advancedPageQuery(IQuery qo) {
		return empDAO.advancedPageQuery(qo);
	}

	@Override
	public List<Employee> queryForList(String condition, Object[] params,
			int currentPage, int pageSize) {
		return empDAO.queryForList(condition, params, currentPage, pageSize);
	}

	@Override
	public void login(String userName, String password) {
		//根据账户密码拿到用户对象
		Employee current = empDAO.checkLogin(userName,password);
		//System.out.println(current);
		
		if(current == null){
			throw new RuntimeException("亲，你的账号或密码有误!");
		}
		
		//将用户保存到session中
		UserContext.setEmployeee(current);
		
		//如果用户不是超管，将用户的所有权限保存到session中
		if(!current.getAdmin()){
			//用一个集合存取用户的所有权限表达式
			Set<String> permissionSet = new HashSet<>();
			List<Role> roles = current.getRoles();
			for (Role role : roles) {
				List<Permission> permissions = role.getPermissions();
				for (Permission permission : permissions) {
					permissionSet.add(permission.getExpression());
				}
			}
			//用户的所有权限保存到session中
			UserContext.setPermissions(permissionSet);
		}
	}
}

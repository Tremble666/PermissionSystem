package com.chenjin.service;

import java.util.List;

import com.chenjin.domain.Employee;
import com.chenjin.domain.Permission;
import com.chenjin.query.IQuery;
import com.chenjin.query.PageResult;

public interface IPermissionService {
	void delete(Long id);
	
	List<Permission> list();
	
	PageResult advancedPageQuery(IQuery qo);
	
	/*
	 * 加载所有的权限
	 */
	void reload();
}

package com.chenjin.service;

import java.util.List;

import com.chenjin.domain.Role;
import com.chenjin.query.IQuery;
import com.chenjin.query.PageResult;

public interface IRoleService {
	void save(Role e);
	void update(Role e);
	void delete(Long id);
	
	Role get(Long id);
	List<Role> list();
	
	PageResult advancedPageQuery(IQuery qo);
	
	List<Role> queryForList(String condition,Object [] params,int currentPage,int pageSize);

}

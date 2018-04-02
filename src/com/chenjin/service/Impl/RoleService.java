package com.chenjin.service.Impl;

import java.util.List;

import lombok.Setter;

import com.chenjin.dao.IRoleDAO;
import com.chenjin.domain.Role;
import com.chenjin.query.IQuery;
import com.chenjin.query.PageResult;
import com.chenjin.service.IRoleService;

public class RoleService implements IRoleService{
	@Setter
	private IRoleDAO roleDAO;
	
	public void save(Role e) {
		roleDAO.save(e);
	}

	public void update(Role e) {
		roleDAO.update(e);
	}

	public void delete(Long id) {
		roleDAO.delete(id);
	}

	public Role get(Long id) {
		return roleDAO.get(id);
	}

	public List<Role> list() {
		return roleDAO.list();
	}

	public PageResult advancedPageQuery(IQuery qo) {
		return roleDAO.advancedPageQuery(qo);
	}

	public List<Role> queryForList(String condition, Object[] params,
			int currentPage, int pageSize) {
		return roleDAO.queryForList(condition, params, currentPage, pageSize);
	}
}

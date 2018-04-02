package com.chenjin.dao;

import java.util.List;

import com.chenjin.domain.Department;
import com.chenjin.query.IQuery;
import com.chenjin.query.PageResult;

public interface IGenericDAO<T> {
	void save(T obj);
	void update(T obj);
	void delete(Long id);
	
	T get(Long id);
	List<T> list();
	
	//高级查询+分页查询
	PageResult advancedPageQuery(IQuery qo);
	
	//带分页的高级查询方法，与queryobject对象无关，返回结果为list
	List<T> queryForList(String condition,Object [] params,int currentPage,int pageSize);
	
	//不带分页的高级查询方法，与queryobject对象无关，返回结果为list
	List<T> queryForList(String condition,Object... params);
	
	//不带分页的高级查询方法，与queryobject对象无关，返回结果为Object
	T queryForObject(String condition,Object... params);

}

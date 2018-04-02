package com.chenjin.query;

import java.util.List;

//抽取查询对象规范
public interface IQuery {
	//返回拼接好查询条件的sql
	String getQuery(boolean isOrded);
	
	//封装拼接好sql的对应的占位符参数
	List<Object> getParams();

	Integer getCurrentPage();

	Integer getPageSize();
	
}

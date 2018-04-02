package com.chenjin.dao.Impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.chenjin.dao.IGenericDAO;
import com.chenjin.query.IQuery;
import com.chenjin.query.PageResult;

public class GenericDAOImpl<T> implements IGenericDAO<T> {
	// 属性注入sessionFactory
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//哪一个domain的字节码
	private Class<T> targetType;
	
	public GenericDAOImpl() {
		//获取DAO实现类的带有泛型信息的父类
		ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
		targetType = (Class<T>) pType.getActualTypeArguments()[0];
	}

	public void save(T obj) {
		Session session = sessionFactory.getCurrentSession();
		session.save(obj);
	}

	public void update(T obj) {
		Session session = sessionFactory.getCurrentSession();
		session.update(obj);
	}

	public void delete(Long id) {
		Session session = sessionFactory.getCurrentSession();
		T obj = (T) session.get(targetType, id);
		session.delete(obj);
	}

	public T get(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.get(targetType, id);
	}

	public List<T> list() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(targetType).list();
	}
	
	//高级查询+分页查询
	public PageResult advancedPageQuery(IQuery qo) {
		int currentPage = qo.getCurrentPage();
		int pageSize = qo.getPageSize();

		Session session = sessionFactory.getCurrentSession();

		// 查询结果总数
		String countHql = "select count(obj) from "+targetType.getSimpleName()+" obj"
				+ qo.getQuery(false);
		Query query = session.createQuery(countHql);
		// 设置占位符参数
		setPlaceParamters(qo, query);

		int totalCount = ((Long) query.uniqueResult()).intValue();
		if (totalCount == 0) {
			return PageResult.empty(pageSize);
		}

		// ---------------------------------------------------------]
		// 查询结果集
		String resultHql = "select obj from "+targetType.getSimpleName()+" obj" + qo.getQuery(false);
		query = session.createQuery(resultHql);
		// 设置占位符参数
		setPlaceParamters(qo, query);
		if(currentPage>0 && pageSize>0){
			query.setFirstResult((currentPage-1)*pageSize);
			query.setMaxResults(pageSize);
		}
		List res = query.list();
		return new PageResult(res, totalCount, currentPage, pageSize);
	}

	// 设置占位符参数的方法
	private void setPlaceParamters(IQuery qo, Query query) {
		for (int i = 0; i < qo.getParams().size(); i++) {
			query.setParameter(i, qo.getParams().get(i));
		}
	}

	public List<T> queryForList(String condition, Object[] params,
			int currentPage, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder hql = new StringBuilder(80);
		hql.append("select obj from ").append(targetType.getSimpleName()).append(" obj");
		if(params != null && params.length>0){
			hql.append(" where ").append(condition);
		}
		Query query = session.createQuery(hql.toString());
		//设置占位符
		if(params != null){
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		//设置分页
		if(currentPage>0 && pageSize>0){
			query.setFirstResult((currentPage - 1) * pageSize);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}

	public List<T> queryForList(String condition, Object... params) {
		return queryForList(condition, params, -1, -1);
	}

	public T queryForObject(String condition, Object... params) {
		List<T> list = queryForList(condition, params);
		return list.size()==1 ? list.get(0) : null;
	}
	
}

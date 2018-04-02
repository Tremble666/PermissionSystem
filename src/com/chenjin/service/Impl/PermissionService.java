package com.chenjin.service.Impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.ApplicationAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import lombok.Setter;

import com.chenjin.Web.Action.BaseAction;
import com.chenjin.dao.IDepartmentDAO;
import com.chenjin.dao.IEmployeeDAO;
import com.chenjin.dao.IPermissionDAO;
import com.chenjin.domain.Department;
import com.chenjin.domain.Employee;
import com.chenjin.domain.Permission;
import com.chenjin.query.IQuery;
import com.chenjin.query.PageResult;
import com.chenjin.service.IDepartmentService;
import com.chenjin.service.IEmployeeService;
import com.chenjin.service.IPermissionService;
import com.chenjin.util.PermissionUtil;
import com.chenjin.util.RequiredPermission;

public class PermissionService implements IPermissionService,
		ApplicationContextAware {
	private ApplicationContext ctx;

	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}

	@Setter
	private IPermissionDAO permissionDAO;

	public void delete(Long id) {
		permissionDAO.delete(id);
	}

	public List<Permission> list() {
		return permissionDAO.list();
	}

	public PageResult advancedPageQuery(IQuery qo) {
		return permissionDAO.advancedPageQuery(qo);
	}

	// 加载所有的权限
	public void reload() {
		// 查询数据库中所有的权限表达式,防止重复加载
		List<Permission> ps = permissionDAO.list();
		Set<String> expressions = new HashSet<>();
		for (Permission p : ps) {
			expressions.add(p.getExpression());
		}

		// 扫描所有baseaction类的子类
		Map<String, BaseAction> beansMap = ctx.getBeansOfType(BaseAction.class);
		Collection<BaseAction> values = beansMap.values();
		// 迭代每一个action类
		for (BaseAction action : values) {
			Method[] ms = action.getClass().getDeclaredMethods();
			// 迭代每一个action类中的方法，不包含父类
			for (Method m : ms) {
				// 判断上面是否有requiredPermission标签
				RequiredPermission rp = m
						.getAnnotation(RequiredPermission.class);
				String expression = PermissionUtil.buildExpression(m);
				//判断数据库中是否已经存在该表达式，如果已经存在，就不再保存到数据库，防止重复添加
				if (!expressions.contains(expression)) {
					if (rp != null) {
						String name = rp.value();
						Permission permission = new Permission();
						permission.setName(name);
						permission.setExpression(expression);
						// 将权限保存到数据库
						permissionDAO.save(permission);
					}
				}
			}
		}
	}
}

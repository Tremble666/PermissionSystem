package com.chenjin.Web.Interceptor;

import java.lang.reflect.Method;
import java.util.Set;

import com.chenjin.domain.Employee;
import com.chenjin.domain.Permission;
import com.chenjin.util.PermissionUtil;
import com.chenjin.util.RequiredPermission;
import com.chenjin.util.UserContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

//权限检查拦截器
public class securityInterceptor extends AbstractInterceptor{
	
	public String intercept(ActionInvocation ai) throws Exception {
		Employee emp = (Employee) UserContext.getCurrentEmployee();
		//检查是否是超级管理员,是则直接放行
		if(emp!=null && emp.getAdmin()){
			return ai.invoke();
		}
		//判断当前请求的方法上是否有权限检查的标签，如果没有，说明不需要做权限检查
		String methodName = ai.getProxy().getMethod();
		System.out.println("methodName:"+methodName);
		Method method = ai.getProxy().getAction().getClass().getDeclaredMethod(methodName);
		RequiredPermission rp = method.getAnnotation(RequiredPermission.class);
		if(rp == null){
			return ai.invoke();
		}
		//构建当前action方法的权限表达式
		String exp = PermissionUtil.buildExpression(method);
		//判断此时session中用户的权限中是否包括该权限，如果包括，则放行
		Set<String> pers = (Set<String>) UserContext.getCurrentPermissions();
		System.out.println("pers:"+pers);
		if(pers != null && pers.size()>0 && pers.contains(exp)){
			return ai.invoke();
		}
		return "nopermission";
	}
}

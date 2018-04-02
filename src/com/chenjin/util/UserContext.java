package com.chenjin.util;

import java.util.Set;

import com.chenjin.domain.Employee;
import com.chenjin.domain.Permission;
import com.opensymphony.xwork2.ActionContext;

/*
 * 用户上下文
 */
public class UserContext {
	public static final String USER_IN_SESSION = "USER_IN_SESSION";
	public static final String PERMISSIONS_IN_SESSION = "PERMISSIONS_IN_SESSION";
	
	/*
	 * 将用户对象保存到session中或者从session中移除
	 */
	public static void setEmployeee(Employee current){
		if(current!=null){
			ActionContext.getContext().getSession().put(USER_IN_SESSION, current);
		}else{
			ActionContext.getContext().getSession().clear();
		}
	}
	
	/*
	 * 将用户对象的权限保存到session中
	 */
	public static void setPermissions(Set<String> permissions){
		ActionContext.getContext().getSession().put("PERMISSIONS_IN_SESSION", permissions);
	}
	
	/*
	 * 获取当前登录用户对象
	 */
	public static Employee getCurrentEmployee(){
		return (Employee) ActionContext.getContext().getSession().get(USER_IN_SESSION);
	}
	
	/*
	 * 获取当前用户的所有权限表达式
	 * 
	 */
	public static Set<String> getCurrentPermissions(){
		return  (Set<String>) ActionContext.getContext().getSession().get(PERMISSIONS_IN_SESSION);
	}
}

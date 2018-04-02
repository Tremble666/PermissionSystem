package com.chenjin.Web.Interceptor;

import com.chenjin.domain.Employee;
import com.chenjin.util.UserContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class checkLoginInterceptor extends AbstractInterceptor{
	
	public String intercept(ActionInvocation ai) throws Exception {
		Object e = UserContext.getCurrentEmployee();
		if(e == null){
			return Action.LOGIN;
		}
		//放行
		return ai.invoke();
	}

}

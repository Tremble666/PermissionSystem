package com.chenjin.Web.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BaseAction extends ActionSupport implements Preparable{
	protected static final String LIST = "list";

	public void prepare() throws Exception {	
	}
	protected void putContext(String name,Object value) {
		ActionContext.getContext().put(name,value);
	}
	
}

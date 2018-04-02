package com.chenjin.Web.Action;

import com.chenjin.service.IEmployeeService;
import com.chenjin.service.Impl.EmployeeService;

import lombok.Getter;
import lombok.Setter;

public class LoginAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	@Setter
	private String username;
	
	@Setter
	private String password;
	
	@Setter
	private IEmployeeService empService;
	
	public String execute() throws Exception {
		try {
			//执行业务逻辑，判断是否有该用户，没有重登；有就将用户对象及角色保存到session中
			empService.login(username,password);
		} catch (RuntimeException e) {
			super.addActionError(e.getMessage());
			return LOGIN;
 		}
		System.out.println("success");
		return SUCCESS;
	}
}

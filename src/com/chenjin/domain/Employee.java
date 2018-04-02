package com.chenjin.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Employee extends BaseDomain{
	private String name;
	private Integer age;
	private Department dept;
	private String password;
	private String email;
	private Boolean admin = false;  //是否为超级管理员，默认为false
	//一个用户拥有多种角色，一种角色可以被多种用户拥有，多对多
	private List<Role> roles = new ArrayList<>();
	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", dept=" + dept.getId()
				+ ", password=" + password + ", email=" + email + ", admin="
				+ admin + "]";
	}
	
	//返回用户的所有角色名称
	public String getRoleNames(){
		if(admin){
			return "[超级管理员]";
		}
		if(roles.size() == 0 ){
			return "[暂无角色]";
		}
		StringBuilder sb = new StringBuilder(40);
		sb.append("[");
		for (Role role : roles) {
			sb.append(role.getName()).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
}

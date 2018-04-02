package com.chenjin.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Department extends BaseDomain{
	private String name;
	private String sn; //部门编号
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", sn=" + sn + "]";
	}
	
}

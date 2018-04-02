package com.chenjin.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Permission extends BaseDomain{
	private String name; //权限名称
	private String expression;  //权限表达式
}

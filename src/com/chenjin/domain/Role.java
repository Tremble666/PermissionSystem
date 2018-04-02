package com.chenjin.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter@Setter
public class Role extends BaseDomain{
	private String name; //名称
	private String sn;  //编码
	//一个角色拥有多种权限，一种权限可以被多种角色拥有，多对多
	private List<Permission> permissions = new ArrayList<Permission>();
}

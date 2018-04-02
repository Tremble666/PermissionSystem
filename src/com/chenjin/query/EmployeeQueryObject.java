package com.chenjin.query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
//封装商品查询信息对象
public class EmployeeQueryObject extends QueryObject{
	private Long deptId;
	private String keyword;
	
	//自己定制自己的查询条件及查询参数（覆盖父类的方法）
	protected void customizedQuery(){
		if(deptId != null && deptId!=-1){
			this.addQuery("obj.dept.id = ?", deptId);
		}
		if(haslength(keyword)){
			this.addQuery("obj.email like ? or name like ?","%"+keyword+"%","%"+keyword+"%");
		}
		setOrder("deptId",orderBy.ASC);
	}

	@Override
	public String toString() {
		return "EmployeeQueryObject [deptId=" + deptId + ", keyword=" + keyword
				+ ", currentPage=" + currentPage + ", pageSize=" + pageSize
				+ ", orderByMap=" + orderByMap + "]";
	}
}

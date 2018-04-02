package com.chenjin.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

//分页的结果集对象，封装了所有的分页对象
@Getter
public class PageResult {
	private List listData;//当前页的结果集:通过sql查询得来
	private Integer totalCount;//结果总数，通过sql查询
	private Integer currentPage=1;//当前页，用户设置
	private Integer pageSize=5;//每页显示多少条数据，用户设置
	private Integer beginPage=1;//首页
	private Integer prevPage;//上页，计算得到
	private Integer nextPage;//下页，计算得到
	private Integer totalPage;//末页/总页数，计算得到
	
	public static PageResult empty(int pageSize){
		return new PageResult(Collections.emptyList(),0,1,pageSize);
	}
	
	public PageResult(List listData, Integer totalCount, Integer currentPage,
			Integer pageSize) {
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize+1;
		this.prevPage = this.currentPage - 1 >= 1 ? this.currentPage -1 : 1;
		this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1 : this.totalPage;

	}
	
}

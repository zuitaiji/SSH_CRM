package com.ithc.util;

import java.util.List;

public class PageBean<T> {
	
	// 查询数据的集合
	private List<T> beanList;
	
	//共多少条记录
	private Integer totalCount;
	
	//总页数
	private Integer totalPage;
	
	//页面数
	private Integer pageCode;
	
	//每页显示的条数
	private Integer pageSize; 

	public Integer getPageSize() {
		
		return pageSize;
	}


	public void setPageSize(Integer pageSize) {
		
		this.pageSize = pageSize;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	public Integer getTotalCount() {
		
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		
		// 总计数/每页显示的数量 = 多少页(整数)
		int total = totalCount/this.pageSize;
		if(totalCount % pageSize == 0){
			this.totalPage = total;
		}else{
			this.totalPage = total+1;
		}
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getPageCode() {
		return pageCode;
	}

	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}
}

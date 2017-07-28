package com.util;

import java.util.List;

public class PageBean<T> {
	
	private int currentPage = 1; //当前页，默认显示第一页
	
	private int pageCount = 10; //每页显示行数，默认显示5行
	
	private int totalCount; //总记录数
	
	private int totalPage; //总页数 
	
	private List<T> pageData; //分页查询到的数据
	
	/**
	 * 返回总页数
	 * @return
	 */
	public int getTotalPage(){
		//pageCount = (rowCount - 1) / pageSize + 1;
		totalPage = (totalCount - 1) / pageCount + 1;
		
		return totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	
	
}

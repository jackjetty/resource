package com.rising.mobilepayment.commom;

public class PageObject {
	//有多少页
	private Integer pageSize;
	// 当前显示的是第几页
	private Integer pageIndex;
	//一页显示多少条记录
	private Integer listSize;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getListSize() {
		return listSize;
	}

	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}
	
	
}

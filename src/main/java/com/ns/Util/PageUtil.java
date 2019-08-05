package com.ns.Util;

public class PageUtil {

	private int start;
	private int rows;
	private String sort;
	private String order;
	private int page;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "Page [start=" + start + ", rows=" + rows + ", sort=" + sort + ", order=" + order + ", page=" + page
				+ "]";
	}
	
	
	
	
}

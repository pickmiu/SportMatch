package com.johntang.springboot.util;

import java.util.List;

/**
 * @Description 返回给前端的分页信息json
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

public class BackFrontPage {
	
	private Integer pageSize;
	private Integer currentPageNum;
	private Integer totalPageNum;
	private List<?> currentPageList;
	
	public BackFrontPage(Integer pageSize,Integer totalPageNum,Integer currentPageNum,List<?> currentPageList) {
		this.setPageSize(pageSize);
		this.currentPageList=currentPageList;
		this.currentPageNum=currentPageNum;
		this.totalPageNum=totalPageNum;
	}

	public List<?> getCurrentPageList() {
		return currentPageList;
	}
	public void setCurrentPageList(List<Object> currentPageList) {
		this.currentPageList = currentPageList;
	}
	public Integer getCurrentPageNum() {
		return currentPageNum;
	}
	public void setCurrentPageNum(Integer currentPageNum) {
		this.currentPageNum = currentPageNum;
	}
	public Integer getTotalPageNum() {
		return totalPageNum;
	}
	public void setTotalPageNum(Integer totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	
}

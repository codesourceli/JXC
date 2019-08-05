package com.ns.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 报溢单
 * @author java1234_小锋 老师
 *
 */
public class OverflowList {

	private Integer id; // 编号
	
	private String overflowNumber; // 报溢单号
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date overflowDate; // 报溢日期
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bOverflowDate; // 起始时间 搜索用到
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date eOverflowDate; // 结束时间 搜索用到
	
	private User user; // 操作用户
	
	private String remarks; // 备注

	
	private List<OverflowListGoods> overflowListGoods;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getOverflowNumber() {
		return overflowNumber;
	}

	public void setOverflowNumber(String overflowNumber) {
		this.overflowNumber = overflowNumber;
	}

	

	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getOverflowDate() {
		return overflowDate;
	}

	public void setOverflowDate(Date overflowDate) {
		this.overflowDate = overflowDate;
	}

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getbOverflowDate() {
		return bOverflowDate;
	}

	public void setbOverflowDate(Date bOverflowDate) {
		this.bOverflowDate = bOverflowDate;
	}

	public Date geteOverflowDate() {
		return eOverflowDate;
	}

	public void seteOverflowDate(Date eOverflowDate) {
		this.eOverflowDate = eOverflowDate;
	}

	public List<OverflowListGoods> getOverflowListGoods() {
		return overflowListGoods;
	}

	public void setOverflowListGoods(List<OverflowListGoods> overflowListGoods) {
		this.overflowListGoods = overflowListGoods;
	}

	
	
	@Override
	public String toString() {
		return "OverflowList [id=" + id + ", overflowNumber=" + overflowNumber + ", overflowDate=" + overflowDate
				+ ", bOverflowDate=" + bOverflowDate + ", eOverflowDate=" + eOverflowDate + ", user=" + user
				+ ", remarks=" + remarks + "]";
	}

	
	
	
}

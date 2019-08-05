package com.ns.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 进货单
 * @author java1234_小锋 老师
 *
 */
public class PurchaseList {

	private Integer id; // 编号
	
	private String purchaseNumber; // 进货单号
	
	private Supplier supplier; // 供应商
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date purchaseDate; // 进货日期
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bPurchaseDate; // 起始时间 搜索用到
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ePurchaseDate; // 结束时间 搜索用到
	
	private float amountPayable; // 应付金额
	
	private float amountPaid; // 实付金额
	
	private Integer state; // 交易状态 1 已付  2 未付
	
	private User user; // 操作用户
	
	private String remarks; // 备注
	
	private List<PurchaseListGoods> purchaseListGoodsList=null; // 采购单商品集合
									 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public float getAmountPayable() {
		return amountPayable;
	}

	public void setAmountPayable(float amountPayable) {
		this.amountPayable = amountPayable;
	}

	public float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(float amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public Date getbPurchaseDate() {
		return bPurchaseDate;
	}

	public void setbPurchaseDate(Date bPurchaseDate) {
		this.bPurchaseDate = bPurchaseDate;
	}

	public Date getePurchaseDate() {
		return ePurchaseDate;
	}

	public void setePurchaseDate(Date ePurchaseDate) {
		this.ePurchaseDate = ePurchaseDate;
	}
	
	
	public List<PurchaseListGoods> getPurchaseListGoodsList() {
		return purchaseListGoodsList;
	}

	public void setPurchaseListGoodsList(List<PurchaseListGoods> purchaseListGoodsList) {
		this.purchaseListGoodsList = purchaseListGoodsList;
	}

	@Override
	public String toString() {
		return "PurchaseList [id=" + id + ", purchaseNumber=" + purchaseNumber + ", supplier=" + supplier
				+ ", purchaseDate=" + purchaseDate + ", bPurchaseDate=" + bPurchaseDate + ", ePurchaseDate="
				+ ePurchaseDate + ", amountPayable=" + amountPayable + ", amountPaid=" + amountPaid + ", state=" + state
				+ ", user=" + user + ", remarks=" + remarks + "]";
	}

	
	
	
	
}

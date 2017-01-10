package com.yun.yweb.income.dto;

import java.io.Serializable;
import java.util.Date;



public class Income implements Serializable {
	private static final long serialVersionUID = 1L;

	private String incomeId;

	private String cashType;

	private Date createTime;

	private String fromUser;

	private String incomeType;

	private String orderCode;

	private double orderTotalPrice;

	private String orderType;

	private String toUser;

	public Income() {
	}

	public String getIncomeId() {
		return this.incomeId;
	}

	public void setIncomeId(String incomeId) {
		this.incomeId = incomeId;
	}

	public String getCashType() {
		return this.cashType;
	}

	public void setCashType(String cashType) {
		this.cashType = cashType;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFromUser() {
		return this.fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getIncomeType() {
		return this.incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}

	public double getOrderTotalPrice() {
		return this.orderTotalPrice;
	}

	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getToUser() {
		return this.toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

}
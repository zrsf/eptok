package com.yun.yweb.userstock.dto;

import java.io.Serializable;
import java.util.Date;



public class UserStock implements Serializable {
	private static final long serialVersionUID = 1L;

	private String stockId;

	private Date createTime;

	private double incomeCash;

	private Date nextIncomeTime;

	private String state;

	private String stockCode;

	private String userCode;
	
	private int incomeCs;

	public UserStock() {
	}

	public String getStockId() {
		return this.stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public double getIncomeCash() {
		return this.incomeCash;
	}

	public void setIncomeCash(double incomeCash) {
		this.incomeCash = incomeCash;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStockCode() {
		return this.stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Date getNextIncomeTime() {
		return nextIncomeTime;
	}

	public void setNextIncomeTime(Date nextIncomeTime) {
		this.nextIncomeTime = nextIncomeTime;
	}

	public int getIncomeCs() {
		return incomeCs;
	}

	public void setIncomeCs(int incomeCs) {
		this.incomeCs = incomeCs;
	}

}
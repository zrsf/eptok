package com.yun.yweb.account.dto;

import java.io.Serializable;
import java.util.Date;



public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	private String accountId;

	private double activateCash;

	private double balanceCash;

	private String checkValue;

	private Date createTime;

	private int inStock;

	private String operator;

	private int outStock;

	private int recommendCount;

	private String state;

	private Date stateChangeTime;

	private double teamCash;

	private int teamCount;

	private String userCode;

	public Account() {
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public double getActivateCash() {
		return this.activateCash;
	}

	public void setActivateCash(double activateCash) {
		this.activateCash = activateCash;
	}

	public double getBalanceCash() {
		return this.balanceCash;
	}

	public void setBalanceCash(double balanceCash) {
		this.balanceCash = balanceCash;
	}

	public String getCheckValue() {
		return this.checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getInStock() {
		return this.inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getOutStock() {
		return this.outStock;
	}

	public void setOutStock(int outStock) {
		this.outStock = outStock;
	}

	public int getRecommendCount() {
		return this.recommendCount;
	}

	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getStateChangeTime() {
		return this.stateChangeTime;
	}

	public void setStateChangeTime(Date stateChangeTime) {
		this.stateChangeTime = stateChangeTime;
	}

	public double getTeamCash() {
		return this.teamCash;
	}

	public void setTeamCash(double teamCash) {
		this.teamCash = teamCash;
	}

	public int getTeamCount() {
		return this.teamCount;
	}

	public void setTeamCount(int teamCount) {
		this.teamCount = teamCount;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
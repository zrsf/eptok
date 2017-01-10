package com.yun.yweb.userrela.dto;

import java.io.Serializable;
import java.util.Date;


public class UserRela implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userRelaId;

	private String ancestor;

	private Date auditTime;

	private Date createTime;

	private String descendant;

	private double incomeCash;

	private Date nextIncomeTime;

	private int lvl;

	private String state;
	
	private String userRelaCode;

	public UserRela() {
	}

	public String getUserRelaId() {
		return this.userRelaId;
	}

	public void setUserRelaId(String userRelaId) {
		this.userRelaId = userRelaId;
	}

	public String getAncestor() {
		return this.ancestor;
	}

	public void setAncestor(String ancestor) {
		this.ancestor = ancestor;
	}

	public Date getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescendant() {
		return this.descendant;
	}

	public void setDescendant(String descendant) {
		this.descendant = descendant;
	}

	public double getIncomeCash() {
		return this.incomeCash;
	}

	public void setIncomeCash(double incomeCash) {
		this.incomeCash = incomeCash;
	}

	public int getLvl() {
		return this.lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getNextIncomeTime() {
		return nextIncomeTime;
	}

	public void setNextIncomeTime(Date nextIncomeTime) {
		this.nextIncomeTime = nextIncomeTime;
	}

	public String getUserRelaCode() {
		return userRelaCode;
	}

	public void setUserRelaCode(String userRelaCode) {
		this.userRelaCode = userRelaCode;
	}

}
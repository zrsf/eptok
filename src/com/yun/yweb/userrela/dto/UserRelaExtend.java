package com.yun.yweb.userrela.dto;

import java.util.Date;

public class UserRelaExtend extends UserRela {
	private static final long serialVersionUID = 1L;

	private boolean isContainItself;
	
	private Date maxNextIncomeTime;
	
	private int maxLvl;

	public UserRelaExtend() {
	}

	public boolean isContainItself() {
		return isContainItself;
	}

	public void setContainItself(boolean isContainItself) {
		this.isContainItself = isContainItself;
	}

	public Date getMaxNextIncomeTime() {
		return maxNextIncomeTime;
	}

	public void setMaxNextIncomeTime(Date maxNextIncomeTime) {
		this.maxNextIncomeTime = maxNextIncomeTime;
	}

	public int getMaxLvl() {
		return maxLvl;
	}

	public void setMaxLvl(int maxLvl) {
		this.maxLvl = maxLvl;
	}

	
}
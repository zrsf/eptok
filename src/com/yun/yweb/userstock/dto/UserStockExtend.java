package com.yun.yweb.userstock.dto;

import java.util.Date;



public class UserStockExtend extends UserStock {
	private static final long serialVersionUID = 1L;

	private Date maxNextIncomeTime;

	private int maxIncomeCs;

	public UserStockExtend() {
	}

	public Date getMaxNextIncomeTime() {
		return maxNextIncomeTime;
	}

	public void setMaxNextIncomeTime(Date maxNextIncomeTime) {
		this.maxNextIncomeTime = maxNextIncomeTime;
	}

	public int getMaxIncomeCs() {
		return maxIncomeCs;
	}

	public void setMaxIncomeCs(int maxIncomeCs) {
		this.maxIncomeCs = maxIncomeCs;
	}


}
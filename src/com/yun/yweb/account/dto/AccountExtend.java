package com.yun.yweb.account.dto;

public class AccountExtend extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double changeActivate;

	private double changeBalance;

	private int changeInStock;

	private int changeOutStock;

	private int changeRecommendCount;

	private double changeTeam;

	private int changeTeamCount;


	public AccountExtend() {
	}


	public double getChangeActivate() {
		return changeActivate;
	}


	public void setChangeActivate(double changeActivate) {
		this.changeActivate = changeActivate;
	}


	public double getChangeBalance() {
		return changeBalance;
	}


	public void setChangeBalance(double changeBalance) {
		this.changeBalance = changeBalance;
	}


	public int getChangeInStock() {
		return changeInStock;
	}


	public void setChangeInStock(int changeInStock) {
		this.changeInStock = changeInStock;
	}


	public int getChangeOutStock() {
		return changeOutStock;
	}


	public void setChangeOutStock(int changeOutStock) {
		this.changeOutStock = changeOutStock;
	}


	public int getChangeRecommendCount() {
		return changeRecommendCount;
	}


	public void setChangeRecommendCount(int changeRecommendCount) {
		this.changeRecommendCount = changeRecommendCount;
	}


	public double getChangeTeam() {
		return changeTeam;
	}


	public void setChangeTeam(double changeTeam) {
		this.changeTeam = changeTeam;
	}


	public int getChangeTeamCount() {
		return changeTeamCount;
	}


	public void setChangeTeamCount(int changeTeamCount) {
		this.changeTeamCount = changeTeamCount;
	}

}
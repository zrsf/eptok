package com.yun.yweb.netfeerecord.dto;

import java.io.Serializable;
import java.util.Date;



public class NetfeeRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	private String recordId;

	private double chargeCash;

	private Date createTime;

	private Date nextChargeTime;

	private String state;

	private String userCode;
	
	private String recordCode;

	public NetfeeRecord() {
	}

	public String getRecordId() {
		return this.recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public double getChargeCash() {
		return this.chargeCash;
	}

	public void setChargeCash(double chargeCash) {
		this.chargeCash = chargeCash;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Date getNextChargeTime() {
		return nextChargeTime;
	}

	public void setNextChargeTime(Date nextChargeTime) {
		this.nextChargeTime = nextChargeTime;
	}

	public String getRecordCode() {
		return recordCode;
	}

	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
	}

}
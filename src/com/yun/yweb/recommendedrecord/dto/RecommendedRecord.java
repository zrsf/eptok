package com.yun.yweb.recommendedrecord.dto;

import java.io.Serializable;
import java.util.Date;

public class RecommendedRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	private String recommendedId;

	private Date createTime;

	private String reference;

	private String state;

	private String userCode;
	
	private String recommendedCode;

	public RecommendedRecord() {
	}

	public String getRecommendedId() {
		return this.recommendedId;
	}

	public void setRecommendedId(String recommendedId) {
		this.recommendedId = recommendedId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
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

	public String getRecommendedCode() {
		return recommendedCode;
	}

	public void setRecommendedCode(String recommendedCode) {
		this.recommendedCode = recommendedCode;
	}

}
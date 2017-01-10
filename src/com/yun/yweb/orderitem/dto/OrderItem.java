package com.yun.yweb.orderitem.dto;

import java.io.Serializable;
import java.util.Date;

public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private String itemId;

	private String cashType;

	private Date createTime;

	private String fromUser;

	private String itemCode;

	private double itemPrice;

	private String itemStatus;

	private String itemType;

	private String orderCode;

	private String remark;

	private Date successTime;

	private String toUser;
	
	private String sourceCode;
	
	private Date ysTime;
	

	public OrderItem() {
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public double getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemStatus() {
		return this.itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getOrderCode() {
		return this.orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getSuccessTime() {
		return this.successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}

	public String getToUser() {
		return this.toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public Date getYsTime() {
		return ysTime;
	}

	public void setYsTime(Date ysTime) {
		this.ysTime = ysTime;
	}

}
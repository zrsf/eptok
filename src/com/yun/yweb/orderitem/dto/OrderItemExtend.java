package com.yun.yweb.orderitem.dto;

public class OrderItemExtend extends OrderItem {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oldItemStatus;


	public OrderItemExtend() {
	}


	public String getOldItemStatus() {
		return oldItemStatus;
	}


	public void setOldItemStatus(String oldItemStatus) {
		this.oldItemStatus = oldItemStatus;
	}

}
package com.yun.yweb.orderitem.service;

import com.yun.yweb.exception.AppException;
import com.yun.yweb.orderitem.dto.OrderItem;

/**
 * @ClassName: OrderItemService
 * @Description: 订单项service接口
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
public interface OrderItemService {
	/**
	 * @Description TODO(添加OrderItem)
	 * @param OrderItem orderItem
	 * @throws AppException
	 */
	public void addOrderItem(OrderItem orderItem) throws AppException;
	


}

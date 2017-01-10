package com.yun.yweb.orderitem.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.yun.framework.mybatis.dao.MybatisDaoUtil;
import com.yun.yweb.exception.AppException;
import com.yun.yweb.orderitem.dto.OrderItem;
import com.yun.yweb.orderitem.service.OrderItemService;

/**
 * @ClassName: OrderItemServiceImpl
 * @Description: 订单项service实现
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
@Service("orderItemServiceImpl")
public class OrderItemServiceImpl implements OrderItemService{
	
	protected static final Log logger = LogFactory.getLog(OrderItemServiceImpl.class);
	
	private MybatisDaoUtil<Map> dao = new MybatisDaoUtil<Map>(Map.class);

	@Override
	public void addOrderItem(OrderItem orderItem) throws AppException {
		try {
			dao.addEntity("addOrderItem", orderItem);
		}catch (Exception de) {
			logger.error("[error:800000]orderItemServiceImpl：添加订单项出错。" + de.getMessage());
			throw new AppException("800000","添加订单项出错");
		}
		
	}


	
	

}

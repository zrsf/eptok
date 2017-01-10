package com.yun.yweb.orders.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.yun.framework.mybatis.dao.MybatisDaoUtil;
import com.yun.yweb.account.dto.Account;
import com.yun.yweb.exception.AppException;
import com.yun.yweb.orders.service.OrdersService;
import com.yun.yweb.user.dto.User;

/**
 * @ClassName: AccountServiceImpl
 * @Description: 账户service实现
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
@Service("ordersServiceImpl")
public class OrdersServiceImpl implements OrdersService{
	
	protected static final Log logger = LogFactory.getLog(OrdersServiceImpl.class);
	
	private MybatisDaoUtil<Map> dao = new MybatisDaoUtil<Map>(Map.class);
	
	public Account queryAccount(Account bean) throws AppException {
		User u = null;
		try {
			return (Account)dao.getEntity("queryAccount", bean);
		}catch (Exception de) {
			logger.error("[error:200000]userService：查询账户信息出错。" + de.getMessage());
			throw new AppException("200000","查询账户信息出错");
		}
	}

}

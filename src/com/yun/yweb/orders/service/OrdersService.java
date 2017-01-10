package com.yun.yweb.orders.service;

import com.yun.yweb.account.dto.Account;
import com.yun.yweb.exception.AppException;

/**
 * @ClassName: OrdersService
 * @Description: 订单service接口
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
public interface OrdersService {
	/**
	 * @Description TODO(查询账户)
	 * @param Account account
	 * @return Account
	 * @throws AppException
	 */
	public Account queryAccount(Account account) throws AppException;

}

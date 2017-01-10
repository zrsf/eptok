package com.yun.yweb.userstock.service;

import com.yun.yweb.exception.AppException;
import com.yun.yweb.userstock.dto.UserStock;

/**
 * @ClassName: UserStockService
 * @Description: 点股service接口
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
public interface UserStockService {
	/**
	 * @Description TODO(添加点股)
	 * @param UserStock userStock
	 * @throws AppException
	 */
	public void addUserStock(UserStock userStock) throws AppException;
	


}

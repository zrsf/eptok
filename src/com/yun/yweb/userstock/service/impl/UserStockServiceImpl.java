package com.yun.yweb.userstock.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.yun.framework.mybatis.dao.MybatisDaoUtil;
import com.yun.yweb.exception.AppException;
import com.yun.yweb.userstock.dto.UserStock;
import com.yun.yweb.userstock.service.UserStockService;

/**
 * @ClassName: UserStockServiceImpl
 * @Description: 点股service实现
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
@Service("userStockServiceImpl")
public class UserStockServiceImpl implements UserStockService{
	
	protected static final Log logger = LogFactory.getLog(UserStockServiceImpl.class);
	
	private MybatisDaoUtil<Map> dao = new MybatisDaoUtil<Map>(Map.class);

	@Override
	public void addUserStock(UserStock userStock) throws AppException {
		try {
			dao.addEntity("addUserStock", userStock);
		}catch (Exception de) {
			logger.error("[error:400000]userStockServiceImpl：添加点股出错。" + de.getMessage());
			throw new AppException("400000","添加点股出错");
		}
		
	}

	
	

}

package com.yun.yweb.userrela.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.yun.framework.mybatis.dao.MybatisDaoUtil;
import com.yun.yweb.exception.AppException;
import com.yun.yweb.userrela.dto.UserRela;
import com.yun.yweb.userrela.service.UserRelaService;

/**
 * @ClassName: NetfeeRecordServiceImpl
 * @Description: 用户关系service实现
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
@Service("userRelaServiceImpl")
public class UserRelaServiceImpl implements UserRelaService{
	
	protected static final Log logger = LogFactory.getLog(UserRelaServiceImpl.class);
	
	private MybatisDaoUtil<Map> dao = new MybatisDaoUtil<Map>(Map.class);

	@Override
	public void addUserRela(UserRela userRela) throws AppException {
		try {
			dao.addEntity("addUserRela", userRela);
		}catch (Exception de) {
			logger.error("[error:700000]netfeeRecordServiceImpl：添加用户关系出错。" + de.getMessage());
			throw new AppException("700000","添加用户关系出错");
		}
		
	}


	
	

}

package com.yun.yweb.netfeerecord.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.yun.framework.mybatis.dao.MybatisDaoUtil;
import com.yun.yweb.exception.AppException;
import com.yun.yweb.netfeerecord.dto.NetfeeRecord;
import com.yun.yweb.netfeerecord.service.NetfeeRecordService;

/**
 * @ClassName: NetfeeRecordServiceImpl
 * @Description: 网络费收费记录service实现
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
@Service("netfeeRecordServiceImpl")
public class NetfeeRecordServiceImpl implements NetfeeRecordService{
	
	protected static final Log logger = LogFactory.getLog(NetfeeRecordServiceImpl.class);
	
	private MybatisDaoUtil<Map> dao = new MybatisDaoUtil<Map>(Map.class);

	@Override
	public void addNetfeeRecord(NetfeeRecord netfeeRecord) throws AppException {
		try {
			dao.addEntity("addNetfeeRecord", netfeeRecord);
		}catch (Exception de) {
			logger.error("[error:600000]netfeeRecordServiceImpl：添加网络费收费记录出错。" + de.getMessage());
			throw new AppException("600000","添加网络费收费记录出错");
		}
		
	}


	
	

}

package com.yun.yweb.recommendedrecord.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.yun.framework.mybatis.dao.MybatisDaoUtil;
import com.yun.yweb.exception.AppException;
import com.yun.yweb.recommendedrecord.dto.RecommendedRecord;
import com.yun.yweb.recommendedrecord.service.RecommendedRecordService;

/**
 * @ClassName: RecommendedRecordServiceImpl
 * @Description: 推荐记录service实现
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
@Service("recommendedRecordServiceImpl")
public class RecommendedRecordServiceImpl implements RecommendedRecordService{
	
	protected static final Log logger = LogFactory.getLog(RecommendedRecordServiceImpl.class);
	
	private MybatisDaoUtil<Map> dao = new MybatisDaoUtil<Map>(Map.class);

	@Override
	public void addRecommendedRecord(RecommendedRecord recommendedRecord) throws AppException {
		try {
			dao.addEntity("addRecommendedRecord", recommendedRecord);
		}catch (Exception de) {
			logger.error("[error:900000]orderItemServiceImpl：添加推荐记录出错。" + de.getMessage());
			throw new AppException("900000","添加推荐记录出错");
		}
		
	}


	
	

}

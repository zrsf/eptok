package com.yun.yweb.recommendedrecord.service;

import com.yun.yweb.exception.AppException;
import com.yun.yweb.recommendedrecord.dto.RecommendedRecord;

/**
 * @ClassName: RecommendedRecordService
 * @Description: 推荐记录service接口
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
public interface RecommendedRecordService {
	/**
	 * @Description TODO(添加RecommendedRecord)
	 * @param RecommendedRecord recommendedRecord
	 * @throws AppException
	 */
	public void addRecommendedRecord(RecommendedRecord recommendedRecord) throws AppException;
	


}

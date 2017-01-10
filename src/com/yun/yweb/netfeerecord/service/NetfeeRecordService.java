package com.yun.yweb.netfeerecord.service;

import com.yun.yweb.exception.AppException;
import com.yun.yweb.netfeerecord.dto.NetfeeRecord;

/**
 * @ClassName: NetfeeRecordService
 * @Description: 网络费收费记录service接口
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
public interface NetfeeRecordService {
	/**
	 * @Description TODO(添加网络费收费记录)
	 * @param UserStock userStock
	 * @throws AppException
	 */
	public void addNetfeeRecord(NetfeeRecord netfeeRecord) throws AppException;
	


}

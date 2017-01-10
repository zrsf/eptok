package com.yun.yweb.income.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.yun.framework.mybatis.dao.MybatisDaoUtil;
import com.yun.yweb.exception.AppException;
import com.yun.yweb.income.dto.Income;
import com.yun.yweb.income.service.IncomeService;

/**
 * @ClassName: IncomeServiceImpl
 * @Description: 收益支出service实现
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
@Service("incomeServiceImpl")
public class IncomeServiceImpl implements IncomeService{
	
	protected static final Log logger = LogFactory.getLog(IncomeServiceImpl.class);
	
	private MybatisDaoUtil<Map> dao = new MybatisDaoUtil<Map>(Map.class);

	@Override
	public void addIncome(Income income) throws AppException {
		try {
			dao.addEntity("addIncome", income);
		}catch (Exception de) {
			logger.error("[error:500000]incomeServiceImpl：添加收益支出记录出错。" + de.getMessage());
			throw new AppException("500000","添加收益支出记录出错");
		}
		
	}

	
	

}

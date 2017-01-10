package com.yun.yweb.income.service;

import com.yun.yweb.exception.AppException;
import com.yun.yweb.income.dto.Income;

/**
 * @ClassName: IncomeService
 * @Description: 收益支出service接口
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
public interface IncomeService {
	/**
	 * @Description TODO(添加收益支出记录)
	 * @param Income income
	 * @throws AppException
	 */
	public void addIncome(Income income) throws AppException;
	


}

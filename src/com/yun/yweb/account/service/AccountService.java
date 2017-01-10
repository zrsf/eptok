package com.yun.yweb.account.service;

import com.yun.yweb.account.dto.Account;
import com.yun.yweb.account.dto.AccountExtend;
import com.yun.yweb.exception.AppException;

/**
 * @ClassName: AccountService
 * @Description: 账户service接口
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
public interface AccountService {
	/**
	 * @Description TODO(查询账户)
	 * @param Account account
	 * @return Account
	 * @throws AppException
	 */
	public Account queryAccount(Account account) throws AppException;
	
	/**
	 * @Description TODO(更新账户)
	 * @param Account account
	 * @return Account
	 * @throws AppException
	 */
	public void updateAccount(AccountExtend account) throws AppException;

}

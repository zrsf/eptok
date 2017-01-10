package com.yun.yweb.user.service;

import com.yun.yweb.exception.AppException;
import com.yun.yweb.user.dto.User;
import com.yun.yweb.user.dto.UserTemp;

import hikefa.core.web.page.Pager;
import hikefa.core.web.page.Paginater;

/**
 * @ClassName: UserService
 * @Description: 用户service接口
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
public interface UserService {
	/**
	 * @Description TODO(查询用户)
	 * @param String userName, String password
	 * @return User
	 * @throws AppException
	 */
	public User queryUser(User user) throws AppException;
	
	public Paginater findSplitPage(User bean, Pager pager) throws AppException;

	public void addUserTemp(UserTemp user) throws AppException;

	public Paginater findUserTempPage(UserTemp queryUser, Pager pager) throws AppException;

	public void updateUserTemp(UserTemp updateBean) throws AppException;

	public UserTemp queryUserTemp(UserTemp queryUser) throws AppException;

	public void approveUserTemp(UserTemp approveUser) throws AppException;
	
	public void settleAccount() throws AppException;

}

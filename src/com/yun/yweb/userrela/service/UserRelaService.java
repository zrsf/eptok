package com.yun.yweb.userrela.service;

import com.yun.yweb.exception.AppException;
import com.yun.yweb.userrela.dto.UserRela;

/**
 * @ClassName: UserRelaService
 * @Description: 用户关系service接口
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
public interface UserRelaService {
	/**
	 * @Description TODO(添加UserRela)
	 * @param UserRela userRela
	 * @throws AppException
	 */
	public void addUserRela(UserRela userRela) throws AppException;
	


}

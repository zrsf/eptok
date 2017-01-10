package com.yun.yweb.account.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.yun.framework.mybatis.dao.MybatisDaoUtil;
import com.yun.yweb.account.dto.Account;
import com.yun.yweb.account.dto.AccountExtend;
import com.yun.yweb.account.service.AccountService;
import com.yun.yweb.common.CommonCode;
import com.yun.yweb.common.CommonUtil;
import com.yun.yweb.exception.AppException;
import com.yun.yweb.user.dto.User;

/**
 * @ClassName: AccountServiceImpl
 * @Description: 账户service实现
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService{
	
	protected static final Log logger = LogFactory.getLog(AccountServiceImpl.class);
	
	private MybatisDaoUtil<Map> dao = new MybatisDaoUtil<Map>(Map.class);
	
	public Account queryAccount(Account bean) throws AppException {
		User u = null;
		try {
			return (Account)dao.getEntity("queryAccount", bean);
		}catch (Exception de) {
			logger.error("[error:300000]userService：查询账户信息出错。" + de.getMessage());
			throw new AppException("300000","查询账户信息出错");
		}
	}

	@Override
	public synchronized void updateAccount(AccountExtend updateAccount) throws AppException {
		try {
			Account queryBean = new Account();
			queryBean.setUserCode(updateAccount.getUserCode());
			queryBean.setAccountId(updateAccount.getAccountId());
			Account oldBean = queryAccount(queryBean);
			if(CommonCode.ACCOUNT_STATE_00.equals(oldBean.getState())) {
				if(oldBean.getCheckValue().equals(CommonUtil.getCheckValue(oldBean))) {
					oldBean.setActivateCash(oldBean.getActivateCash() + updateAccount.getChangeActivate());
					oldBean.setBalanceCash(oldBean.getBalanceCash() + updateAccount.getChangeBalance());
					oldBean.setInStock(oldBean.getInStock() + updateAccount.getChangeInStock());
					oldBean.setOutStock(oldBean.getOutStock() + updateAccount.getChangeOutStock());
					oldBean.setRecommendCount(oldBean.getRecommendCount() + updateAccount.getChangeRecommendCount());
					oldBean.setTeamCash(oldBean.getTeamCash() + updateAccount.getChangeTeam());
					oldBean.setTeamCount(oldBean.getTeamCount() + updateAccount.getChangeTeamCount());
					if(updateAccount.getState() != null &&
							!"".equals(updateAccount.getState().trim())) {
						oldBean.setState(updateAccount.getState());
					}
					oldBean.setCheckValue(CommonUtil.getCheckValue(oldBean));
					dao.updateEntity("updateAccount", oldBean);
				}else {
					throw new AppException("300001","该账户存在异常");
				}
			}else {
				throw new AppException("300002","该账户已被" + CommonCode.ACCOUNT_STATE_MAP.get(oldBean.getState()));
			}
		}catch (AppException e) {
			logger.error("[error:300003]userService：更新账户信息出错:" + e.getMessage());
			throw e;
		}catch (Exception de) {
			logger.error("[error:300004]userService：更新账户信息出错。" + de.getMessage());
			throw new AppException("300004","更新账户信息出错");
		}
		
	}

}

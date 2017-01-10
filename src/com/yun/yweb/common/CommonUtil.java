package com.yun.yweb.common;

import org.apache.log4j.Logger;

import com.yun.yweb.account.dto.Account;
import com.yun.yweb.exception.AppException;

import hikefa.core.util.NumberUtil;
import hikefa.core.util.security.JDigest;

public class CommonUtil {
	private static Logger logger = Logger.getLogger(CommonUtil.class);
	
	public static String getCheckValue(Account account) throws AppException{
		StringBuffer sValue = new StringBuffer();
		sValue.append(account.getAccountId());
		sValue.append("|" + account.getUserCode());
		sValue.append("|" + account.getTeamCount());
		sValue.append("|" + account.getRecommendCount());
		sValue.append("|" + account.getTeamCash());
		sValue.append("|" + account.getOutStock());
		sValue.append("|" + account.getInStock());
		sValue.append("|" + account.getState());
		sValue.append("|" + NumberUtil.getFormatStr(account.getBalanceCash(), 2));
		sValue.append("|" + NumberUtil.getFormatStr(account.getActivateCash(), 2));
		sValue.append("|" + NumberUtil.getFormatStr(account.getTeamCash(), 2));
		sValue.append("|" + NumberUtil.getFormatStr(0.0, 2));
		sValue.append("|" + NumberUtil.getFormatStr(0.0, 2));
		sValue.append("|" + NumberUtil.getFormatStr(0.0, 2));
		sValue.append("|" + NumberUtil.getFormatStr(0.0, 2));
		sValue.append("|" + NumberUtil.getFormatStr(0.0, 2));
		sValue.append("|" + NumberUtil.getFormatStr(0.0, 2));
		sValue.append("|" + NumberUtil.getFormatStr(0.0, 2));
		sValue.append("|" + NumberUtil.getFormatStr(0.0, 2));
		String checkValue = null;
		try {
			checkValue = JDigest.digestMD5_Base64(sValue.toString().getBytes());
			return checkValue;
		} catch (Exception e) {
			logger.error("md5 fail:" + e.getMessage());
			throw new AppException("112233", "md5加密失败");
		}

	}
	
	private static int startIndex = 0;

	public static synchronized String getIndex() {
		if (startIndex >= 9999) {
			startIndex = 0;
		}
		startIndex++;
		return Utils.fill(startIndex + "", '0', 4);
	}

}

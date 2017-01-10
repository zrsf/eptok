package com.yun.yweb.common;

import java.util.HashMap;
import java.util.Map;

public class CommonCode {

	/**
	 * 用户状态
	 */
	public static String USER_STATE_00 = "00";
	public static String USER_STATE_01 = "01";
	public static String USER_STATE_02 = "02";
	public static Map<String, String> STATE_MAP = new HashMap<String, String>();
	static{
		STATE_MAP.put(USER_STATE_00, "正常");
		STATE_MAP.put(USER_STATE_01, "冻结");
		STATE_MAP.put(USER_STATE_02, "锁定");
	}
	
	
	/**
	 * 临时用户状态
	 */
	public static String USERTEMP_STATE_00 = "00";
	public static String USERTEMP_STATE_01 = "01";
	public static String USERTEMP_STATE_02 = "02";
	public static Map<String, String> USERTEMP_STATE_MAP = new HashMap<String, String>();
	static{
		USERTEMP_STATE_MAP.put(USERTEMP_STATE_00, "待审核");
		USERTEMP_STATE_MAP.put(USERTEMP_STATE_01, "已审核通过");
		USERTEMP_STATE_MAP.put(USERTEMP_STATE_02, "审核未通过");
	}
	
	/**
	 * 临时用户状态
	 */
	public static String USER_TYPE_0 = "0";
	public static String USER_TYPE_1 = "1";
	public static Map<String, String> USER_TYPE_MAP = new HashMap<String, String>();
	static{
		USER_TYPE_MAP.put(USER_TYPE_0, "前台注册");
		USER_TYPE_MAP.put(USER_TYPE_1, "后台注册");
	}
	
	/**
	 * 订单状态
	 */
	public static String ORDER_STATUS_01 = "01";
	public static String ORDER_STATUS_02 = "02";
	public static String ORDER_STATUS_03 = "03";
	public static Map<String, String> ORDER_STATUS_MAP = new HashMap<String, String>();
	static{
		ORDER_STATUS_MAP.put(ORDER_STATUS_01, "处理中");
		ORDER_STATUS_MAP.put(ORDER_STATUS_02, "成功");
		ORDER_STATUS_MAP.put(ORDER_STATUS_03, "失败");
	}
	
	/**
	 * 订单类型
	 */
	public static String ORDER_TYPE_01 = "01";
	public static String ORDER_TYPE_02 = "02";
	public static String ORDER_TYPE_03 = "03";
	public static String ORDER_TYPE_04 = "04";
	public static String ORDER_TYPE_05 = "05";
	public static String ORDER_TYPE_06 = "06";
	public static String ORDER_TYPE_07 = "07";
	public static String ORDER_TYPE_08 = "08";
	public static String ORDER_TYPE_09 = "09";
	public static String ORDER_TYPE_10 = "10";
	
	public static Map<String, String> ORDER_TYPE_MAP = new HashMap<String, String>();
	static{
		ORDER_TYPE_MAP.put(ORDER_TYPE_01, "推广消费");
		ORDER_TYPE_MAP.put(ORDER_TYPE_02, "会员注册");
		ORDER_TYPE_MAP.put(ORDER_TYPE_03, "复投消费");
		ORDER_TYPE_MAP.put(ORDER_TYPE_04, "余额转激活币");
		ORDER_TYPE_MAP.put(ORDER_TYPE_05, "团队分红转激活币");
		ORDER_TYPE_MAP.put(ORDER_TYPE_06, "提现");
		ORDER_TYPE_MAP.put(ORDER_TYPE_07, "分红");
		ORDER_TYPE_MAP.put(ORDER_TYPE_08, "团队奖");
		ORDER_TYPE_MAP.put(ORDER_TYPE_09, "直销奖");
		ORDER_TYPE_MAP.put(ORDER_TYPE_10, "网络费");
	}
	
	/**
	 * 货币类型
	 */
	public static String CASH_TYPE_01 = "01";
	public static String CASH_TYPE_02 = "02";
	public static String CASH_TYPE_03 = "03";
	public static Map<String, String> CASH_TYPE_MAP = new HashMap<String, String>();
	static{
		CASH_TYPE_MAP.put(CASH_TYPE_01, "余额");
		CASH_TYPE_MAP.put(CASH_TYPE_02, "激活币");
		CASH_TYPE_MAP.put(CASH_TYPE_03, "团队分红");
	}
	
	/**
	 * 账户状态
	 */
	public static String ACCOUNT_STATE_00 = "00";
	public static String ACCOUNT_STATE_01 = "01";
	public static String ACCOUNT_STATE_02 = "02";
	public static String ACCOUNT_STATE_03 = "03";
	public static String ACCOUNT_STATE_04 = "04";
	public static Map<String, String> ACCOUNT_STATE_MAP = new HashMap<String, String>();
	static{
		ACCOUNT_STATE_MAP.put(ACCOUNT_STATE_00, "正常");
		ACCOUNT_STATE_MAP.put(ACCOUNT_STATE_01, "冻结	");
		ACCOUNT_STATE_MAP.put(ACCOUNT_STATE_02, "注销登记");
		ACCOUNT_STATE_MAP.put(ACCOUNT_STATE_03, "注销登记");
		ACCOUNT_STATE_MAP.put(ACCOUNT_STATE_04, "md5校验失败");
	}
	
	/**
	 * 点股状态
	 */
	public static String STOCK_STATE_01 = "01";
	public static String STOCK_STATE_02 = "02";
	public static Map<String, String> STOCK_STATE_MAP = new HashMap<String, String>();
	static{
		STOCK_STATE_MAP.put(STOCK_STATE_01, "已投入");
		STOCK_STATE_MAP.put(STOCK_STATE_02, "已出局");
	}
	
	/**
	 * 收益类型
	 */
	public static String INCOME_TYPE_01 = "01";
	public static String INCOME_TYPE_02 = "02";
	public static Map<String, String> INCOME_TYPE_MAP = new HashMap<String, String>();
	static{
		INCOME_TYPE_MAP.put(INCOME_TYPE_01, "收入");
		INCOME_TYPE_MAP.put(INCOME_TYPE_02, "支出");
	}
	
	/**
	 * 网络费收费状态
	 */
	public static String NETFEERECORD_STATE_01 = "01";
	public static String NETFEERECORD_STATE_02 = "02";
	public static Map<String, String> NETFEERECORD_STATE_MAP = new HashMap<String, String>();
	static{
		NETFEERECORD_STATE_MAP.put(NETFEERECORD_STATE_01, "正常");
		NETFEERECORD_STATE_MAP.put(NETFEERECORD_STATE_02, "停止");
	}
	
	/**
	 * 用户关系状态
	 */
	public static String USERRELA_STATE_01 = "01";
	public static String USERRELA_STATE_02 = "02";
	public static Map<String, String> USERRELA_STATE_MAP = new HashMap<String, String>();
	static{
		USERRELA_STATE_MAP.put(USERRELA_STATE_01, "正常");
		USERRELA_STATE_MAP.put(USERRELA_STATE_02, "不作分红");
	}
	
	/**
	 * 推荐记录状态
	 */
	public static String RECOMMENDEDRECORD_STATE_00 = "00";
	public static String RECOMMENDEDRECORD_STATE_01 = "01";
	public static Map<String, String> RECOMMENDEDRECORD_STATE_MAP = new HashMap<String, String>();
	static{
		RECOMMENDEDRECORD_STATE_MAP.put(RECOMMENDEDRECORD_STATE_00, "待收直推奖");
		RECOMMENDEDRECORD_STATE_MAP.put(RECOMMENDEDRECORD_STATE_01, "已收直推奖");
	}
	
	/**
	 * 订单项类型
	 */
	public static String ORDERITEM_ITEMTYPE_01 = "01";
	public static String ORDERITEM_ITEMTYPE_02 = "02";
	public static String ORDERITEM_ITEMTYPE_03 = "03";
	public static Map<String, String> ORDERITEM_ITEMTYPE_MAP = new HashMap<String, String>();
	static{
		ORDERITEM_ITEMTYPE_MAP.put(ORDERITEM_ITEMTYPE_01, "直推奖");
		ORDERITEM_ITEMTYPE_MAP.put(ORDERITEM_ITEMTYPE_02, "团队分红");
		ORDERITEM_ITEMTYPE_MAP.put(ORDERITEM_ITEMTYPE_03, "点股分红");
	}
	
	/**
	 * 订单项货币类型
	 */
	public static String ORDERITEM_CASHTYPE_01 = "01";
	public static String ORDERITEM_CASHTYPE_02 = "02";
	public static String ORDERITEM_CASHTYPE_03 = "03";
	public static Map<String, String> ORDERITEM_CASHTYPE_MAP = new HashMap<String, String>();
	static{
		ORDERITEM_CASHTYPE_MAP.put(ORDERITEM_CASHTYPE_01, "余额");
		ORDERITEM_CASHTYPE_MAP.put(ORDERITEM_CASHTYPE_02, "激活币");
		ORDERITEM_CASHTYPE_MAP.put(ORDERITEM_CASHTYPE_03, "团队分红");
	}
	
	/**
	 * 订单项状态
	 */
	public static String ORDERITEM_ITEMSTATUS_01 = "01";
	public static String ORDERITEM_ITEMSTATUS_02 = "02";
	public static String ORDERITEM_ITEMSTATUS_03 = "03";
	public static Map<String, String> ORDERITEM_ITEMSTATUS_MAP = new HashMap<String, String>();
	static{
		ORDERITEM_ITEMSTATUS_MAP.put(ORDERITEM_ITEMSTATUS_01, "处理中");
		ORDERITEM_ITEMSTATUS_MAP.put(ORDERITEM_ITEMSTATUS_02, "成功");
		ORDERITEM_ITEMSTATUS_MAP.put(ORDERITEM_ITEMSTATUS_03, "失败");
	}
	
	/**
	 * 点股状态
	 */
	public static String USERSTOCK_STATE_01 = "01";
	public static String USERSTOCK_STATE_02 = "02";
	public static Map<String, String> USERSTOCK_STATE_MAP = new HashMap<String, String>();
	static{
		USERSTOCK_STATE_MAP.put(ORDERITEM_ITEMSTATUS_01, "已投入");
		USERSTOCK_STATE_MAP.put(ORDERITEM_ITEMSTATUS_02, "已出局");
	}
	
}

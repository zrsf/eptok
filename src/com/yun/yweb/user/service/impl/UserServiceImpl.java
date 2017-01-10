package com.yun.yweb.user.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yun.framework.mybatis.dao.MybatisDaoUtil;
import com.yun.yweb.account.dto.Account;
import com.yun.yweb.account.dto.AccountExtend;
import com.yun.yweb.account.service.AccountService;
import com.yun.yweb.common.CommonCode;
import com.yun.yweb.common.CommonUtil;
import com.yun.yweb.common.DateUtil;
import com.yun.yweb.common.GUIDGenerator;
import com.yun.yweb.exception.AppException;
import com.yun.yweb.income.dto.Income;
import com.yun.yweb.netfeerecord.dto.NetfeeRecord;
import com.yun.yweb.orderitem.dto.OrderItem;
import com.yun.yweb.orders.dto.Orders;
import com.yun.yweb.recommendedrecord.dto.RecommendedRecord;
import com.yun.yweb.user.dto.User;
import com.yun.yweb.user.dto.UserTemp;
import com.yun.yweb.user.service.UserService;
import com.yun.yweb.userrela.dto.UserRela;
import com.yun.yweb.userrela.dto.UserRelaExtend;
import com.yun.yweb.userstock.dto.UserStock;

import hikefa.core.web.page.Pager;
import hikefa.core.web.page.Paginater;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户service实现
 * @company 中润四方
 * @author yun
 * @date 2014-08-13
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService{
	
	protected static final Log logger = LogFactory.getLog(UserServiceImpl.class);
	
	private MybatisDaoUtil<Map> dao = new MybatisDaoUtil<Map>(Map.class);
	
	private GUIDGenerator gen = new GUIDGenerator();
	
	@Autowired
	private AccountService accountServiceImpl;
	
	public User queryUser(User user) throws AppException {
		User u = null;
		try {
			return (User)dao.getEntity("queryUser", user);
		}catch (Exception de) {
			logger.error("[error:100000]userService：查询用户信息出错。" + de.getMessage());
			throw new AppException("100000","查询用户信息出错");
		}
	}

	public Paginater findSplitPage(User bean, Pager pager) throws AppException {
		try {
			pager.setPageNumber(1);
			pager.setPageSize(10);
			return dao.getEntitysPage("findSplitPage", bean, pager);
		}catch (Exception de) {
			logger.error("[error:100001]userService：查询用户信息出错。" + de.getMessage());
			throw new AppException("100001","查询用户信息出错");
		}
	}

	@Override
	public void addUserTemp(UserTemp user) throws AppException {
		try {
			dao.addEntity("addUserTemp", user);
		}catch (Exception de) {
			logger.error("[error:100002]userService：注册出错。" + de.getMessage());
			throw new AppException("100002","注册出错");
		}
		
	}

	@Override
	public Paginater findUserTempPage(UserTemp queryUser, Pager pager) throws AppException {
		try {
			return dao.getEntitysPage("queryUserTemp", queryUser, pager);
		}catch (Exception de) {
			logger.error("[error:100003]userService：查询待审核推广链接出错。" + de.getMessage());
			throw new AppException("100003","查询待审核推广链接出错");
		}
	}

	@Override
	public void updateUserTemp(UserTemp updateBean) throws AppException {
		try {
			dao.updateEntity("updateUserTemp", updateBean);
		}catch (Exception de) {
			logger.error("[error:100004]userService：修改用户出错。" + de.getMessage());
			throw new AppException("100004","修改用户出错");
		}
		
	}

	@Override
	public UserTemp queryUserTemp(UserTemp queryUser) throws AppException {
		try {
			return (UserTemp)dao.getEntity("queryUserTemp", queryUser);
		}catch (Exception de) {
			logger.error("[error:100005]userService：查询临时用户出错。" + de.getMessage());
			throw new AppException("100005","查询临时出错");
		}
	}

	@Override
	public void approveUserTemp(UserTemp approveUser) throws AppException {
		try {
			//扣除账户余额 记录到订单，添加直推人数 ，记录到推荐记录
			Account queryAccount = new Account();
			queryAccount.setUserCode(approveUser.getApproveUser());
			Account operatorAccount = (Account)dao.getEntity("queryAccount", queryAccount);
			
			Orders order = new Orders();
			order.setFromUser(approveUser.getApproveUser());
			order.setCreateTime(approveUser.getApproveTime());
			order.setCashType(CommonCode.CASH_TYPE_02);
			order.setToUser(approveUser.getUserCode());
			String orderCode = DateUtil.getCurrentDateTime("yyyyMMddHHmmss");
			order.setOrderCode(orderCode + CommonUtil.getIndex());
			GUIDGenerator gen = new GUIDGenerator();
			order.setOrderId(gen.genId().toString());
			order.setOrderStatus(CommonCode.ORDER_STATUS_02);
			order.setOrderTotalPrice(300);
			order.setOrderType(CommonCode.ORDER_TYPE_01);
			order.setSuccessTime(approveUser.getApproveTime());
			order.setBalance(operatorAccount.getActivateCash());
			dao.addEntity("addOrders", order);
			
			AccountExtend account = new AccountExtend();
			account.setUserCode(approveUser.getApproveUser());
			account.setChangeActivate(-300);
			account.setChangeRecommendCount(1);
			accountServiceImpl.updateAccount(account);		
			
			RecommendedRecord rr = new RecommendedRecord();
			rr.setCreateTime(approveUser.getApproveTime());
			rr.setRecommendedId(gen.genId().toString());
			rr.setReference(approveUser.getUserCode());
			rr.setState(CommonCode.RECOMMENDEDRECORD_STATE_00);
			rr.setUserCode(approveUser.getApproveUser());
			String recommendedCode = DateUtil.getCurrentDateTime("yyyyMMddHHmmss");
			rr.setRecommendedCode(recommendedCode + CommonUtil.getIndex());
			dao.addEntity("addRecommendedRecord", rr);
			
			//把临时用户移到正式用户，创建账户
			User saveBean = new User(approveUser);
			saveBean.setState(CommonCode.USER_STATE_00);
			dao.addEntity("addUser", saveBean);
			
			Account saveAcount = new Account();
			saveAcount.setAccountId(gen.genId().toString());
			saveAcount.setUserCode(saveBean.getUserCode());
			saveAcount.setState(CommonCode.ACCOUNT_STATE_00);
			saveAcount.setCreateTime(saveBean.getCreateTime());
			saveAcount.setInStock(1);
			saveAcount.setCheckValue(CommonUtil.getCheckValue(saveAcount));
			dao.addEntity("addAccount", saveAcount);
			//在用户点股表中记录 收益200 
			UserStock us = new UserStock();
			us.setCreateTime(saveBean.getCreateTime());
			us.setIncomeCash(20);
			Calendar tempCalendar = Calendar.getInstance();
			tempCalendar.setTime(saveBean.getCreateTime());
			tempCalendar.add(Calendar.DATE, 1);
			us.setNextIncomeTime(tempCalendar.getTime());
			us.setState(CommonCode.STOCK_STATE_01);
			String stockCode = DateUtil.getCurrentDateTime("yyyyMMddHHmmss");
			us.setStockCode(stockCode + CommonUtil.getIndex());
			us.setStockId(gen.genId().toString());
			us.setUserCode(saveBean.getUserCode());
			us.setIncomeCs(0);
			dao.addEntity("addUserStock", us);
			
			Income ic = new Income();
			ic.setCashType(CommonCode.CASH_TYPE_02);
			ic.setCreateTime(saveBean.getCreateTime());
			ic.setFromUser(saveBean.getUserCode());
			ic.setIncomeId(gen.genId().toString());
			ic.setIncomeType(CommonCode.INCOME_TYPE_01);
			ic.setOrderCode(orderCode);
			ic.setOrderTotalPrice(200);
			ic.setOrderType(CommonCode.ORDER_TYPE_02);
			dao.addEntity("addIncome", ic);
			
			//在网络费记录表中添加记录
			NetfeeRecord nr = new NetfeeRecord();
			nr.setCreateTime(saveBean.getCreateTime());
			nr.setChargeCash(20);
			tempCalendar.setTime(saveBean.getCreateTime());
			tempCalendar.add(Calendar.DATE, 1);
			nr.setNextChargeTime(tempCalendar.getTime());
			nr.setState(CommonCode.NETFEERECORD_STATE_01);
			nr.setUserCode(saveBean.getUserCode());
			nr.setRecordId(gen.genId().toString());
			String recordCode = DateUtil.getCurrentDateTime("yyyyMMddHHmmss");
			nr.setRecordCode(recordCode + CommonUtil.getIndex());
			dao.addEntity("addNetfeeRecord", nr);
			//产生收益100 支出20给直接推荐用户 记录订单
			Income ic2 = new Income();
			ic2.setCashType(CommonCode.CASH_TYPE_02);
			ic2.setCreateTime(saveBean.getCreateTime());
			ic2.setFromUser(saveBean.getUserCode());
			ic2.setIncomeId(gen.genId().toString());
			ic2.setIncomeType(CommonCode.INCOME_TYPE_01);
			ic2.setOrderCode(orderCode);
			ic2.setOrderTotalPrice(100);
			ic2.setOrderType(CommonCode.ORDER_TYPE_02);
			dao.addEntity("addIncome", ic2);
			
			//在用户关系表建立关系为团队分红
			UserRela saveUr = new UserRela();
			saveUr.setAncestor("-1");
			saveUr.setDescendant(saveBean.getUserCode());
			saveUr.setAuditTime(saveBean.getCreateTime());
			saveUr.setCreateTime(saveBean.getCreateTime());
			saveUr.setLvl(0);
			saveUr.setState(CommonCode.USERRELA_STATE_02);
			saveUr.setUserRelaId(gen.genId().toString());
			tempCalendar.setTime(saveBean.getCreateTime());
			tempCalendar.add(Calendar.DATE, 1);
			saveUr.setNextIncomeTime(tempCalendar.getTime());
			saveUr.setIncomeCash(0);
			String userRelaCode = DateUtil.getCurrentDateTime("yyyyMMddHHmmss");
			saveUr.setUserRelaCode(userRelaCode + CommonUtil.getIndex());
			dao.addEntity("addUserRela", saveUr);
			
			UserRelaExtend queryURBean = new UserRelaExtend();
			queryURBean.setDescendant(approveUser.getApproveUser());
			queryURBean.setContainItself(false);
			List urList = dao.getEntitys("queryUserRela", queryURBean);
			UserRela temp = new UserRela();
			temp.setAncestor(approveUser.getApproveUser());
			temp.setLvl(0);
			urList.add(temp);
			for(int i = 0; i < urList.size(); i++) {
				UserRela tempUr = (UserRela)urList.get(0);
				UserRela ur = new UserRela();
				ur.setAncestor(tempUr.getAncestor());
				ur.setDescendant(saveBean.getUserCode());
				ur.setAuditTime(saveBean.getCreateTime());
				ur.setCreateTime(saveBean.getCreateTime());
				ur.setLvl(tempUr.getLvl() + 1);
				ur.setState(CommonCode.USERRELA_STATE_01);
				ur.setUserRelaId(gen.genId().toString());
				tempCalendar.setTime(saveBean.getCreateTime());
				tempCalendar.add(Calendar.DATE, 1);
				ur.setNextIncomeTime(tempCalendar.getTime());
				String urCode = DateUtil.getCurrentDateTime("yyyyMMddHHmmss");
				ur.setUserRelaCode(urCode + CommonUtil.getIndex());
				if(ur.getLvl() == 1) {
					ur.setIncomeCash(1);
				}else if(ur.getLvl() == 2) {
					ur.setIncomeCash(0.9);
				}else if(ur.getLvl() == 3) {
					ur.setIncomeCash(0.8);
				}else if(ur.getLvl() == 4) {
					ur.setIncomeCash(0.7);
				}else if(ur.getLvl() == 5) {
					ur.setIncomeCash(0.5);
				}else if(ur.getLvl() == 6) {
					ur.setIncomeCash(0.3);
				}else if(ur.getLvl() >= 7 && ur.getLvl() <= 9) {
					ur.setIncomeCash(0.1);
				}else {
					ur.setIncomeCash(0);
					ur.setState(CommonCode.USERRELA_STATE_02);
				}
				dao.addEntity("addUserRela", ur);
				//更新团队人数
				AccountExtend queryAccount2 = new AccountExtend();
				queryAccount2.setUserCode(tempUr.getAncestor());
				queryAccount2.setChangeTeamCount(1);
				accountServiceImpl.updateAccount(queryAccount2);
			}
			//更新用户临时表状态
			approveUser.setState(CommonCode.USERTEMP_STATE_01);
			dao.updateEntity("updateUserTemp", approveUser);
		}catch (Exception de) {
			logger.error("[error:100006]userService：激活用户出错。" + de.getMessage());
			throw new AppException("100006","激活用户出错");
		}
		
	}
	
	public void settleAccount() throws AppException {
		try {
			//结算直推奖
			/*RecommendedRecord queryRr = new RecommendedRecord();
			queryRr.setState(CommonCode.RECOMMENDEDRECORD_STATE_00);
			List<Map> rrList = dao.getEntitys("queryRecommendedCount", queryRr);
			for(Map rrMap : rrList) {
				int rs = Integer.parseInt(rrMap.get("count").toString());
				String userCode = rrMap.get("userCode").toString();
				String createTime = rrMap.get("createTime").toString();
				//记录订单
				Account queryAccount = new Account();
				queryAccount.setUserCode(userCode);
				Account operatorAccount = (Account)dao.getEntity("queryAccount", queryAccount);
				
				Orders order = new Orders();
				order.setCreateTime(hikefa.core.util.DateUtil.CalendarToDate());
				order.setCashType(CommonCode.CASH_TYPE_01);
				order.setToUser(userCode);
				String orderCode = DateUtil.getCurrentDateTime("yyyyMMddHHmmss") 
						+ CommonUtil.getIndex();
				order.setOrderCode(orderCode);
				GUIDGenerator gen = new GUIDGenerator();
				order.setOrderId(gen.genId().toString());
				order.setOrderStatus(CommonCode.ORDER_STATUS_02);
				order.setOrderTotalPrice(20*rs);
				order.setOrderType(CommonCode.ORDER_TYPE_09);
				order.setSuccessTime(order.getCreateTime());
				order.setRemark(createTime + "产生直推奖转入" + order.getOrderTotalPrice());
				order.setBalance(operatorAccount.getBalanceCash());
				dao.addEntity("addOrders", order);
				//给用户加钱
				AccountExtend account = new AccountExtend();
				account.setUserCode(userCode);
				account.setChangeBalance(order.getOrderTotalPrice());
				accountServiceImpl.updateAccount(account);
				
				//记录支出
				Income ic = new Income();
				ic.setCashType(CommonCode.CASH_TYPE_01);
				ic.setCreateTime(order.getCreateTime());
				ic.setToUser(userCode);
				ic.setIncomeId(gen.genId().toString());
				ic.setIncomeType(CommonCode.INCOME_TYPE_02);
				ic.setOrderCode(orderCode);
				ic.setOrderTotalPrice(order.getOrderTotalPrice());
				ic.setOrderType(CommonCode.ORDER_TYPE_01);
				dao.addEntity("addIncome", ic);
				//记录订单明细
				RecommendedRecord queryRrDetail = new RecommendedRecord();
				queryRrDetail.setState(CommonCode.RECOMMENDEDRECORD_STATE_00);
				queryRrDetail.setUserCode(userCode);
				queryRrDetail.setCreateTime(DateUtil.toDate(createTime, "yyyy-MM-dd"));
				List rrDetailList = dao.getEntitys("queryRecommendedRecord", queryRrDetail);
				for(int i = 0; i < rrDetailList.size(); i++) {
					RecommendedRecord detail = (RecommendedRecord)rrDetailList.get(i);
					OrderItem oi = new OrderItem();
					oi.setCreateTime(order.getCreateTime());
					String itemCode = DateUtil.getCurrentDateTime("yyyyMMddHHmmss") 
							+ CommonUtil.getIndex();
					oi.setItemCode(itemCode);
					oi.setItemId(gen.genId().toString());
					oi.setItemPrice(20);
					oi.setItemStatus(CommonCode.ORDERITEM_ITEMSTATUS_02);
					oi.setItemType(CommonCode.ORDERITEM_ITEMTYPE_01);
					oi.setCashType(CommonCode.ORDERITEM_CASHTYPE_01);
					oi.setOrderCode(orderCode);
					oi.setRemark("直推奖");
					oi.setSuccessTime(order.getCreateTime());
					oi.setToUser(userCode);
					oi.setSourceCode(detail.getRecommendedCode());
					dao.addEntity("addOrderItem", oi);
					//更新直推表记录状态
					detail.setState(CommonCode.RECOMMENDEDRECORD_STATE_01);
					dao.updateEntity("updateRecommendedCount", detail);
				}
			}*/
			//结算团队分红
			/*String bonusNumber = PropertiesUtils.getProperty("BONUS_NUMBER");
			UserRelaExtend queryUr = new UserRelaExtend();
			queryUr.setMaxLvl(Integer.parseInt(bonusNumber));
			queryUr.setMaxNextIncomeTime(new Date());
			queryUr.setState(CommonCode.USERRELA_STATE_01);
			List urList = dao.getEntitys("queryUserRela", queryUr);
			for(int i = 0; i < urList.size(); i++) {
				//记录订单项
				UserRela tempUr = (UserRela)urList.get(i);
				iteratorCountIncome(tempUr);
				//更新用户关系表
				dao.updateEntity("updateUserRela", tempUr);
			}
			
			//统计
			OrderItem queryOi = new OrderItem();
			queryOi.setItemStatus(CommonCode.ORDERITEM_ITEMSTATUS_01);
			queryOi.setItemType(CommonCode.ORDERITEM_ITEMTYPE_02);
			List<Map> oiList = dao.getEntitys("queryOrderItemCount", queryOi);
			for(Map tempMap : oiList) {
				//统计记录订单
				Account queryAccount = new Account();
				queryAccount.setUserCode(tempMap.get("toUser").toString());
				Account incomeAccount = (Account)dao.getEntity("queryAccount", queryAccount);
				
				Orders order = new Orders();
				order.setCreateTime(hikefa.core.util.DateUtil.CalendarToDate());
				order.setCashType(CommonCode.CASH_TYPE_03);
				order.setToUser(tempMap.get("toUser").toString());
				String orderCode = DateUtil.getCurrentDateTime("yyyyMMddHHmmss") 
						+ CommonUtil.getIndex();
				order.setOrderCode(orderCode);
				GUIDGenerator gen = new GUIDGenerator();
				order.setOrderId(gen.genId().toString());
				order.setOrderStatus(CommonCode.ORDER_STATUS_02);
				
				double countPrice = Double.parseDouble(tempMap.get("countPrice").toString());
				
				if(incomeAccount.getTeamCount() >= 20000 && incomeAccount.getRecommendCount() >= 100) {
					if(countPrice > 10000) {
						order.setOrderTotalPrice(10000);
					}else {
						order.setOrderTotalPrice(countPrice);
					}
				}else if(incomeAccount.getTeamCount() >= 18000 && incomeAccount.getRecommendCount() >= 90) {
					if(countPrice > 9000) {
						order.setOrderTotalPrice(9000);
					}else {
						order.setOrderTotalPrice(countPrice);
					}
				}else if(incomeAccount.getTeamCount() >= 16000 && incomeAccount.getRecommendCount() >= 80) {
					if(countPrice > 8000) {
						order.setOrderTotalPrice(8000);
					}else {
						order.setOrderTotalPrice(countPrice);
					}
				}else if(incomeAccount.getTeamCount() >= 14000 && incomeAccount.getRecommendCount() >= 70) {
					if(countPrice > 7000) {
						order.setOrderTotalPrice(7000);
					}else {
						order.setOrderTotalPrice(countPrice);
					}
				}else if(incomeAccount.getTeamCount() >= 12000 && incomeAccount.getRecommendCount() >= 60) {
					if(countPrice > 6000) {
						order.setOrderTotalPrice(6000);
					}else {
						order.setOrderTotalPrice(countPrice);
					}
				}else if(incomeAccount.getTeamCount() >= 10000 && incomeAccount.getRecommendCount() >= 50) {
					if(countPrice > 5000) {
						order.setOrderTotalPrice(5000);
					}else {
						order.setOrderTotalPrice(countPrice);
					}
				}else if(incomeAccount.getTeamCount() >= 8000 && incomeAccount.getRecommendCount() >= 40) {
					if(countPrice > 4000) {
						order.setOrderTotalPrice(4000);
					}else {
						order.setOrderTotalPrice(countPrice);
					}
				}else if(incomeAccount.getTeamCount() >= 6000 && incomeAccount.getRecommendCount() >= 30) {
					if(countPrice > 3000) {
						order.setOrderTotalPrice(3000);
					}else {
						order.setOrderTotalPrice(countPrice);
					}
				}else if(incomeAccount.getTeamCount() >= 4000 && incomeAccount.getRecommendCount() >= 20) {
					if(countPrice > 2000) {
						order.setOrderTotalPrice(2000);
					}else {
						order.setOrderTotalPrice(countPrice);
					}
				}else if(incomeAccount.getTeamCount() >= 2000 && incomeAccount.getRecommendCount() >= 10) {
					if(countPrice > 1000) {
						order.setOrderTotalPrice(1000);
					}else {
						order.setOrderTotalPrice(countPrice);
					}
				}else if(incomeAccount.getRecommendCount() >= 10){
					if(countPrice > 500) {
						order.setOrderTotalPrice(500);
					}else {
						order.setOrderTotalPrice(countPrice);
					}
				}else {
					if(countPrice > 100) {
						order.setOrderTotalPrice(100);
					}else {
						order.setOrderTotalPrice(countPrice);
					}
				}
				
				order.setOrderType(CommonCode.ORDER_TYPE_08);
				order.setSuccessTime(order.getCreateTime());
				order.setRemark(tempMap.get("ysTime") + "产生团队分红转入" + order.getOrderTotalPrice());
				order.setBalance(incomeAccount.getBalanceCash());
				dao.addEntity("addOrders", order);
				//把订单项修改为成功 记录批次号
				OrderItemExtend updateOie = new OrderItemExtend();
				updateOie.setToUser(tempMap.get("toUser").toString());
				updateOie.setOldItemStatus(CommonCode.ORDERITEM_ITEMSTATUS_01);
				updateOie.setYsTime(DateUtil.transferDate(tempMap.get("ysTime").toString(), "yyyy-MM-dd"));
				updateOie.setItemStatus(CommonCode.ORDERITEM_ITEMSTATUS_02);
				updateOie.setOrderCode(orderCode);
				updateOie.setItemType(CommonCode.ORDERITEM_ITEMTYPE_02);
				dao.updateEntity("updateOrderItem", updateOie);
				//给客户加钱
				AccountExtend account = new AccountExtend();
				account.setUserCode(tempMap.get("toUser").toString());
				account.setChangeTeam(order.getOrderTotalPrice());
				accountServiceImpl.updateAccount(account);
				//记录支出
				Income ic = new Income();
				ic.setCashType(CommonCode.CASH_TYPE_03);
				ic.setCreateTime(order.getCreateTime());
				ic.setToUser(tempMap.get("toUser").toString());
				ic.setIncomeId(gen.genId().toString());
				ic.setIncomeType(CommonCode.INCOME_TYPE_02);
				ic.setOrderCode(orderCode);
				ic.setOrderTotalPrice(order.getOrderTotalPrice());
				ic.setOrderType(CommonCode.ORDER_TYPE_08);
				dao.addEntity("addIncome", ic);
				
			}*/
			//点股收益
			/*UserStockExtend queryUs = new UserStockExtend();
			queryUs.setNextIncomeTime(new Date());
			queryUs.setMaxIncomeCs(15);
			queryUs.setState(CommonCode.USERSTOCK_STATE_01);
			List usList = dao.getEntitys("queryUserStock", queryUs);
			
			for(int i = 0; i < usList.size(); i++) {
				//记录订单项
				UserStock tempUs = (UserStock)usList.get(i);
				iteratorStockIncome(tempUs);
				//更新用户点股数
				if(CommonCode.USERSTOCK_STATE_02.equals(tempUs.getState())) {
					AccountExtend account = new AccountExtend();
					account.setUserCode(tempUs.getUserCode());
					account.setChangeInStock(-1);
					account.setChangeOutStock(1);
					accountServiceImpl.updateAccount(account);
				}
				//更新点股表
				dao.updateEntity("updateUserStock", tempUs);
			}
			
			//统计
			OrderItem queryOi = new OrderItem();
			queryOi.setItemStatus(CommonCode.ORDERITEM_ITEMSTATUS_01);
			queryOi.setItemType(CommonCode.ORDERITEM_ITEMTYPE_03);
			List<Map> oiList = dao.getEntitys("queryOrderItemCount", queryOi);
			for(Map tempMap : oiList) {
				//统计记录订单
				Account queryAccount = new Account();
				queryAccount.setUserCode(tempMap.get("toUser").toString());
				Account incomeAccount = (Account)dao.getEntity("queryAccount", queryAccount);
				
				Orders order = new Orders();
				order.setCreateTime(hikefa.core.util.DateUtil.CalendarToDate());
				order.setCashType(CommonCode.CASH_TYPE_01);
				order.setToUser(tempMap.get("toUser").toString());
				String orderCode = DateUtil.getCurrentDateTime("yyyyMMddHHmmss") 
						+ CommonUtil.getIndex();
				order.setOrderCode(orderCode);
				GUIDGenerator gen = new GUIDGenerator();
				order.setOrderId(gen.genId().toString());
				order.setOrderStatus(CommonCode.ORDER_STATUS_02);
				order.setOrderType(CommonCode.ORDER_TYPE_07);
				order.setSuccessTime(order.getCreateTime());
				
				double countPrice = Double.parseDouble(tempMap.get("countPrice").toString());
				order.setOrderTotalPrice(countPrice);
				order.setRemark(tempMap.get("ysTime") + "产生点股分红转入" + order.getOrderTotalPrice());
				order.setBalance(incomeAccount.getBalanceCash());
				dao.addEntity("addOrders", order);
				//把订单项修改为成功 记录批次号
				OrderItemExtend updateOie = new OrderItemExtend();
				updateOie.setToUser(tempMap.get("toUser").toString());
				updateOie.setOldItemStatus(CommonCode.ORDERITEM_ITEMSTATUS_01);
				updateOie.setYsTime(DateUtil.transferDate(tempMap.get("ysTime").toString(), "yyyy-MM-dd"));
				updateOie.setItemStatus(CommonCode.ORDERITEM_ITEMSTATUS_02);
				updateOie.setOrderCode(orderCode);
				updateOie.setItemType(CommonCode.ORDERITEM_ITEMTYPE_03);
				dao.updateEntity("updateOrderItem", updateOie);
				//给客户加钱
				AccountExtend account = new AccountExtend();
				account.setUserCode(tempMap.get("toUser").toString());
				account.setChangeBalance(order.getOrderTotalPrice());
				accountServiceImpl.updateAccount(account);
				//记录支出
				Income ic = new Income();
				ic.setCashType(CommonCode.CASH_TYPE_01);
				ic.setCreateTime(order.getCreateTime());
				ic.setToUser(tempMap.get("toUser").toString());
				ic.setIncomeId(gen.genId().toString());
				ic.setIncomeType(CommonCode.INCOME_TYPE_02);
				ic.setOrderCode(orderCode);
				ic.setOrderTotalPrice(order.getOrderTotalPrice());
				ic.setOrderType(CommonCode.ORDER_TYPE_08);
				dao.addEntity("addIncome", ic);
				
			}*/
			//结算网络费
			System.out.println("====测试任务执行了");
		} catch (Exception e) {
			logger.error("[error:199999]userService：激活用户出错。" + e.getMessage());
			throw new AppException("199999","激活用户出错");
		}
	}
	
	
	private void iteratorCountIncome(UserRela tempUr) throws Exception {
		if(tempUr.getNextIncomeTime().before(new Date())) {
			OrderItem saveOi = new OrderItem();
			saveOi.setCreateTime(hikefa.core.util.DateUtil.CalendarToDate());
			String itemCode = DateUtil.getCurrentDateTime("yyyyMMddHHmmss") 
					+ CommonUtil.getIndex();
			saveOi.setItemCode(itemCode);
			saveOi.setItemId(gen.genId().toString());
			saveOi.setItemPrice(tempUr.getIncomeCash());
			saveOi.setItemStatus(CommonCode.ORDERITEM_ITEMSTATUS_01);
			saveOi.setItemType(CommonCode.ORDERITEM_ITEMTYPE_02);
			saveOi.setCashType(CommonCode.ORDERITEM_CASHTYPE_03);
			saveOi.setRemark("团队分红");
			saveOi.setSuccessTime(saveOi.getCreateTime());
			saveOi.setToUser(tempUr.getAncestor());
			saveOi.setSourceCode(tempUr.getUserRelaCode());
			saveOi.setYsTime(tempUr.getNextIncomeTime());
			dao.addEntity("addOrderItem", saveOi);
			
			Calendar tempCalendar = Calendar.getInstance();
			tempCalendar.setTime(tempUr.getNextIncomeTime());
			tempCalendar.add(Calendar.DATE, 1);
			tempUr.setNextIncomeTime(tempCalendar.getTime());
			iteratorCountIncome(tempUr);
		}
		
	}
	
	
	private void iteratorStockIncome(UserStock tempUs) throws Exception {
		if(tempUs.getNextIncomeTime().before(new Date())) {
			if(tempUs.getIncomeCs() >= 15) {
				tempUs.setState(CommonCode.USERSTOCK_STATE_02);
			}else {
				OrderItem saveOi = new OrderItem();
				saveOi.setCreateTime(hikefa.core.util.DateUtil.CalendarToDate());
				String itemCode = DateUtil.getCurrentDateTime("yyyyMMddHHmmss") 
						+ CommonUtil.getIndex();
				saveOi.setItemCode(itemCode);
				saveOi.setItemId(gen.genId().toString());
				saveOi.setItemPrice(tempUs.getIncomeCash());
				saveOi.setItemStatus(CommonCode.ORDERITEM_ITEMSTATUS_01);
				saveOi.setItemType(CommonCode.ORDERITEM_ITEMTYPE_03);
				saveOi.setCashType(CommonCode.ORDERITEM_CASHTYPE_01);
				saveOi.setRemark("点股分红");
				saveOi.setSuccessTime(saveOi.getCreateTime());
				saveOi.setToUser(tempUs.getUserCode());
				saveOi.setSourceCode(tempUs.getStockCode());
				saveOi.setYsTime(tempUs.getNextIncomeTime());
				dao.addEntity("addOrderItem", saveOi);
				
				Calendar tempCalendar = Calendar.getInstance();
				tempCalendar.setTime(tempUs.getNextIncomeTime());
				tempCalendar.add(Calendar.DATE, 1);
				tempUs.setNextIncomeTime(tempCalendar.getTime());
				tempUs.setIncomeCs(tempUs.getIncomeCs() + 1);
				iteratorStockIncome(tempUs);
			}
			
		}
		
	}
	

}

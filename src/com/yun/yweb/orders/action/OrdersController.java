package com.yun.yweb.orders.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yun.yweb.base.BaseDispatchAction;
import com.yun.yweb.orders.service.OrdersService;

@RequestMapping({"/orders"})
@Controller
public class OrdersController extends BaseDispatchAction {
	
	@Autowired
	private OrdersService ordersServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/** 登录成功跳转首页url */
	//private static String REDIRECT = "index.do";
	
	
	
	
	
}

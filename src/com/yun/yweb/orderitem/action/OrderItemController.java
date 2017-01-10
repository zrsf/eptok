package com.yun.yweb.orderitem.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yun.yweb.base.BaseDispatchAction;
import com.yun.yweb.orderitem.service.OrderItemService;

@RequestMapping({"/orderitem"})
@Controller
public class OrderItemController extends BaseDispatchAction {
	
	@Autowired
	private OrderItemService orderItemServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/** 登录成功跳转首页url */
	//private static String REDIRECT = "index.do";
	
	
	
}

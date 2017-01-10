package com.yun.yweb.account.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yun.yweb.account.service.AccountService;
import com.yun.yweb.base.BaseDispatchAction;

@RequestMapping({"/account"})
@Controller
public class AccountController extends BaseDispatchAction {
	
	@Autowired
	private AccountService accountServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/** 登录成功跳转首页url */
	//private static String REDIRECT = "index.do";
	
	
	
	
	
}

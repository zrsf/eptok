package com.yun.yweb.netfeerecord.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yun.yweb.base.BaseDispatchAction;
import com.yun.yweb.netfeerecord.service.NetfeeRecordService;

@RequestMapping({"/netfeerecord"})
@Controller
public class NetfeeRecordController extends BaseDispatchAction {
	
	@Autowired
	private NetfeeRecordService netfeeRecordServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/** 登录成功跳转首页url */
	//private static String REDIRECT = "index.do";
	
	
	
}

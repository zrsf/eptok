package com.yun.yweb.recommendedrecord.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yun.yweb.base.BaseDispatchAction;
import com.yun.yweb.recommendedrecord.service.RecommendedRecordService;

@RequestMapping({"/recommendedrecord"})
@Controller
public class RecommendedRecordController extends BaseDispatchAction {
	
	@Autowired
	private RecommendedRecordService recommendedRecordServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/** 登录成功跳转首页url */
	//private static String REDIRECT = "index.do";
	
	
	
}

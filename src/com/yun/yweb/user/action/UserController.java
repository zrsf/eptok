package com.yun.yweb.user.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yun.yweb.account.dto.Account;
import com.yun.yweb.account.service.AccountService;
import com.yun.yweb.base.BaseDispatchAction;
import com.yun.yweb.common.CommonCode;
import com.yun.yweb.common.JsonUtil;
import com.yun.yweb.common.PropertiesUtils;
import com.yun.yweb.exception.AppException;
import com.yun.yweb.user.dto.User;
import com.yun.yweb.user.dto.UserTemp;
import com.yun.yweb.user.service.UserService;

import hikefa.core.exception.BizException;
import hikefa.core.util.DateUtil;
import hikefa.core.web.page.Paginater;
import hikefa.core.web.util.RequestManager;

@RequestMapping({"/user"})
@Controller
public class UserController extends BaseDispatchAction {
	
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private AccountService accountServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/** 登录成功跳转首页url */
	//private static String REDIRECT = "index.do";
	
	/**
	 * 跳转至用户用户登录页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "method=login")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response, User user) {
		try {
			User queryUser = new User();
			queryUser.setUserCode(user.getUserCode());
			queryUser.setLoginPwd(user.getLoginPwd());
			queryUser.setState(CommonCode.USER_STATE_00);
			User u = userServiceImpl.queryUser(queryUser);
			if(u != null && u.getUserCode() != null) {
				setSessionUser(request, u);
				return new ModelAndView("index");
			}else {
				return new ModelAndView("login");
			}
			
		} catch (AppException e) {
			logger.error("[error:10000]UserController：登录发生异常：" + e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("[error:10000]UserController：登录发生异常：" + e.getMessage());
			throw new BizException("登录发生异常");
		}
	}
	
	/**
	 * 用户退出
	 * 
	 * @return
	 */
	@RequestMapping(params = "method=loginOut")
	public ModelAndView loginOut(HttpServletRequest request) {
		try {
			removeSessionUser(request);
			return new ModelAndView("login");
		} catch (Exception e) {
			logger.error("[error:10001]UserController：用户退出出错。" + e.getMessage());
			throw new BizException("用户退出出错");
		}
		
	}
	
	/**
	 * 推广链接
	 * 
	 * @return
	 */
	@RequestMapping(params = "method=expandedHref")
	public ModelAndView expandedHref(HttpServletRequest request, User user) {
		try {
			Boolean flag = (Boolean)this.getSessionAttribute(request, "hasVilidSecondPwd");
			if(flag != null && flag) {
				String expandedUrl = PropertiesUtils.getProperty("EXPANDED_URL");
				User u = this.getSessionUser(request);
				request.setAttribute("expandedHref", expandedUrl + "?userCode=" + u.getUserCode());
				return new ModelAndView("/pages/bisinessmanager/expandedHref");
			}else {
				request.setAttribute("callBackUrl", "/user.do?method=expandedHref");
				return new ModelAndView("secondPwd");
			}
		} catch (Exception e) {
			logger.error("[error:10002]UserController：打开推广链接发生异常：" + e.getMessage());
			throw new BizException("打开推广链接发生异常");
		}
		
	}
	
	/**
	 * 推广链接
	 * 
	 * @return
	 */
	@RequestMapping(params = "method=checkSecondPwd")
	public String checkSecondPwd(HttpServletRequest request, User user) {
		try {
			User sessionUser = getSessionUser(request);
			User queryUser = new User();
			queryUser.setSecondPwd(user.getSecondPwd());
			queryUser.setUserCode(sessionUser.getUserCode());
			queryUser.setState(CommonCode.USER_STATE_00);
			User u = userServiceImpl.queryUser(queryUser);
			String callBackUrl = request.getParameter("callBackUrl");
			if(u != null && u.getUserCode() != null) {
				this.setSessionAttribute(request, "hasVilidSecondPwd", true);
				return "forward:" + callBackUrl;
			}else {
				request.setAttribute("callBackUrl", callBackUrl);
				return "forward:/secondPwd.jsp";
			}
		} catch (AppException e) {
			logger.error("[error:10003]UserController：校验二级密码发生异常：" + e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("[error:10003]UserController：校验二级密码发生异常：" + e.getMessage());
			throw new BizException("校验二级密码发生异常");
		}
		
	}
	
	/**
	 * 注册
	 * 
	 * @return
	 */
	@RequestMapping(params = "method=register")
	public ModelAndView register(HttpServletRequest request, UserTemp user) {
		try {
			user.setState(CommonCode.USERTEMP_STATE_00);
			user.setUserType(CommonCode.USER_TYPE_0);
			userServiceImpl.addUserTemp(user);
			return new ModelAndView("registerSuccess");
		} catch (AppException e) {
			logger.error("[error:10004]UserController：注册发生异常：" + e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("[error:10004]UserController：注册发生异常：" + e.getMessage());
			throw new BizException("注册发生异常");
		}
		
	}
	
	/**
	 * 注册
	 * 
	 * @return
	 */
	@RequestMapping(params = "method=loadApproveUser")
	public ModelAndView loadApproveUser(HttpServletRequest request) {
		try {
			User u = this.getSessionUser(request);
			UserTemp queryUser = new UserTemp();
			queryUser.setParentUsercode(u.getUserCode());
			queryUser.setState(CommonCode.USERTEMP_STATE_00);
			Paginater paginater = userServiceImpl.findUserTempPage(queryUser,
					RequestManager.getPager(request));
			responsePager(request, paginater);
			return new ModelAndView("/pages/bisinessmanager/approveUser");
		} catch (AppException e) {
			logger.error("[error:10005]UserController：查询待审核推广链接用户列表发生异常：" + e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("[error:10005]UserController：查询待审核推广链接用户列表发生异常：" + e.getMessage());
			throw new BizException("查询待审核推广链接用户列表发生异常");
		}
		
	}
	
	/**
	 * 审核用户
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 */
	@RequestMapping(params = "method=approveUser", produces = "application/json; charset=utf-8")
	public @ResponseBody String approveUser(HttpServletRequest request, UserTemp bean) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			String operatorType = request.getParameter("operatorType");
			User u = this.getSessionUser(request);
			
			UserTemp queryUser = new UserTemp();
			queryUser.setUserCode(bean.getUserCode());
			UserTemp approveUser = userServiceImpl.queryUserTemp(queryUser);
			if(approveUser != null && u.getUserCode().equals(approveUser.getParentUsercode())) {
				if("1".equals(operatorType)) {
					Account querybean = new Account();
					querybean.setUserCode(u.getUserCode());
					querybean.setState(CommonCode.USER_STATE_00);
					Account account = accountServiceImpl.queryAccount(querybean);
					if(account != null) {
						if(account.getActivateCash() >= 300) {
							approveUser.setApproveUser(u.getUserCode());
							approveUser.setApproveTime(DateUtil.CalendarToDate());
							userServiceImpl.approveUserTemp(approveUser);
							returnMap.put("ret", true);
							returnMap.put("msg", "激活成功");
						}else {
							returnMap.put("ret", false);
							returnMap.put("msg", "激活币余额不足");
						}
					}else {
						returnMap.put("ret", false);
						returnMap.put("msg", "账户不存在或已被禁用");
					}
				}else {
					UserTemp updateBean = new UserTemp();
					updateBean.setUserCode(bean.getUserCode());
					updateBean.setState(CommonCode.USERTEMP_STATE_02);
					updateBean.setApproveUser(u.getUserCode());
					updateBean.setApproveTime(DateUtil.CalendarToDate());
					userServiceImpl.updateUserTemp(updateBean);
					returnMap.put("ret", true);
					returnMap.put("msg", "删除成功");
				}
			}else {
				returnMap.put("ret", false);
				returnMap.put("msg", "非法操作");
			}
			
			
		} catch (AppException e) {
			logger.error("[error:10006]UserController：审核用户发生异常：" + e.getMessage());
			returnMap.put("ret", false);
			returnMap.put("msg", "操作发生异常");
		} catch (Exception e) {
			logger.error("[error:10006]UserController：审核用户发生异常：" + e.getMessage());
			returnMap.put("ret", false);
			returnMap.put("msg", "操作发生异常");
		}
		return JsonUtil.map2json(returnMap);
	}
	
	
	
}

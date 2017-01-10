package com.yun.yweb.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yun.yweb.user.dto.User;

/**
 * 用户登录拦截器，判断用户是否登录
 * 用于户登录、退出权限管理
 * @Description	
 * @author ql
 * @date 2014-11-5 下午1:32:27
 * @version V1.0
 *
 */
public class UserLoginInterceptor extends HandlerInterceptorAdapter{

	public final static String SEESION_MEMBER = "seesion_member";
	 
	private Logger logger = Logger.getLogger(this.getClass());
	
	private List<String> excludedMethods;

    public void setExcludedMethods(List<String> excludedMethods) {
        this.excludedMethods = excludedMethods;
    }
	
    /*
     * (non-Javadoc)
     * 拦截mvc.xml配置的/member/**路径的请求
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
		/**
		 * 用户登录权限判定
		 * 
		 * （注：用户登录、注册跳转、登录等操作不做拦截操作）
		 * 
		 * 1、判定用户是否登录、Session是否失效
		 * 		没有登录跳转至登录界面
		 * 
		 */
		
		//这里可以根据session的用户来判断角色的权限，根据权限来重定向不同的页面，简单起见，这里只是做了一个重定向
		boolean flag = false;
        //请求的路径
//        String contextPath=request.getContextPath();
        User user = (User)request.getSession().getAttribute("user");
		
        String methodName = request.getParameter("method");
        String url = request.getRequestURL().toString();				//请求uri
        if(methodName != null && !"".equals(methodName.trim())) {
        	url += "?method=" + methodName;
        }
        String requestedWith = request.getHeader("x-requested-with"); //请求头
        logger.info("\nurl >>>:" + url);
        for (String s : excludedMethods) {
            if (url.contains(s)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
        	if(user == null){
        		// ajax请求
    			if (requestedWith != null && "XMLHttpRequest".equals(requestedWith)) {
    				response.setHeader("session-status", "timeout");
    				response.getWriter().print("{\"msg\":\"timeout\",\"code\":\"1001\"}");
    			}else{//普通请求
    				//String redirect_url = SystemGlobals.getProperty("user_logout_url");
    				String redirect_url = "login.jsp";
    				redirect_url = request.getContextPath() + "/" + redirect_url;
    				logger.info("未登录跳转至：" + redirect_url);
//    				response.getWriter().write("<script>ilogin()</script>");
    				response.getWriter().write("<script>window.top.location.href=\""+redirect_url+"\";</script>");
    				
    				/*
					String redirect_url = SystemGlobals.getProperty("user_logout_url");
		            if(redirect_url.equals("index.do")){
		            	response.sendRedirect(contextPath+"/index.do");		//本地登录
		            }else{
		            	response.sendRedirect(redirect_url);				//服务器登录
		            }
    				 */
    				
//    				response.sendRedirect(request.getContextPath()+loginUrl);
//    				request.getRequestDispatcher("/index.do").forward(request, response);
    			}
    			return false;
        	}else{
        		flag = true;
        	}
        }
		return flag;
    }
 
	
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
            Exception arg3) throws Exception {
        // TODO Auto-generated method stub
 
    }
 
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
            ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub
 
    }
 

}

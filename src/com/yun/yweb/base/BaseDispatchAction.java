package com.yun.yweb.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.yun.yweb.user.dto.User;

import hikefa.core.web.page.Paginater;

public class BaseDispatchAction{

	private static final Logger LOG = Logger.getLogger(BaseDispatchAction.class);
	

	protected static final int PAGE_SIZE = 10;
	
	public static final String SUCCESS_RETURN_URL = "forward:/pages_public/common/success.jsp?isClose=Y";
	


	protected void setResult(boolean success, String message,
			HttpServletRequest request) {
		request.setAttribute("result", Boolean.valueOf(success));
		request.setAttribute("message", message);
	}


	public void setResult(String result, String message,
			HttpServletRequest request) {
		request.setAttribute("result", result);
		request.setAttribute("message", message);
	}


	protected static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}


	protected void setAttribute(HttpServletRequest request, String key,
			String value) {
		request.setAttribute(key, value);
	}

	protected void setAttribute(HttpServletRequest request, String key,
			Object obj) {
		request.setAttribute(key, obj);
	}


	protected void setSessionAttribute(HttpServletRequest request, String key,
			Object obj) {
		request.getSession().setAttribute(key, obj);
	}
	
	protected User getSessionUser(HttpServletRequest request) {
		return (User)this.getSessionAttribute(request, "user");
	}
	
	protected void setSessionUser(HttpServletRequest request, User obj) {
		request.getSession().setAttribute("user", obj);
	}
	
	protected void removeSessionUser(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
	}
	
	protected void removeSessionAttribute(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}

	protected String getAttribute(HttpServletRequest request, String key) {
		return (String) request.getAttribute(key);
	}


	protected Object getSessionAttribute(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}

	protected String getParameter(HttpServletRequest request, String key) {
		return request.getParameter(key);
	}

	protected int getPage(HttpServletRequest request) {
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("pageNumber"));
		}catch (NumberFormatException e) {
		}
		return page;
	}


	protected int getPageSize(HttpServletRequest request) {
		int pageSize = PAGE_SIZE;
		try {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}catch (NumberFormatException e) {
		}
		return pageSize;
	}


	protected void responsePager(HttpServletRequest request, Paginater paginater)
			throws IOException {
		if(paginater==null){
			setAttribute(request, "count", Long.toString(0));
			setAttribute(request, "beans", null);
		}else{
			setAttribute(request, "count", Long.toString(paginater.getMaxRowCount()));
			setAttribute(request, "beans", paginater.getData());
		}	
	}


	protected void responseMessage(HttpServletResponse response, String message)
			throws IOException {
		LOG.debug(message);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(message);
	}



	
}

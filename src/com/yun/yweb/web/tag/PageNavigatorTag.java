package com.yun.yweb.web.tag;
import java.lang.reflect.Method;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 实现分页导航的标签
 * 
 * @author liudw 2012-06-12
 * 
 */
public class PageNavigatorTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(PageNavigatorTag.class);
	// 每页显示记录数
	public static final int PAGE_SIZE = 20;
	// 数字颜色
	public static final String NUMBER_FONT = "#337ab7";
	// 无效导航连接
	public static final String DISABLED_URL = "#808080";
	// 导航参数
	public static final String NAV_PARAM = "pageNumber";
	// 对象默认容器
	public static final String DEFAULT_SCOPE = "request";
	//分页传入参数
	public static final String PAGESIZE = "pageSize";

	private String total; // 总记录数
	private String name;// 保存记录数的对象
	private String property;// 保存记录数的属性
	private String type;// 对象的类型
	private String scope;// 对象容器，默认为request
	private int pageSize; // 每页显示记录数 默认为PAGE_SIZE
	private String formName; // 查询结果页面，需要知道查询form的名字
	private int formIndex; // form的次序
	private String url; // 页面地址
	private String navParamName; // 导航参数 默认为NAV_PARAM

	private int sumPage; // 总页数
	private int currPage; // 当前页数 默认为1
	private int sum;		// 总记录数

	public PageNavigatorTag() {
		total = null;
		name = null;
		property = null;
		type = null;
		scope = DEFAULT_SCOPE;
		pageSize = PAGE_SIZE;
		formName = null;
		formIndex = 0;
		url = null;
		navParamName = NAV_PARAM;

		sumPage = 0;
		currPage = 1;
		sum = -1;
	}

	public int doStartTag() {
		String echo = null;
		int result;
		try {
			echo = process();
			result = SKIP_BODY;
		} catch (Exception e) {
			echo = e.getMessage();
			result = SKIP_PAGE;
		}
		
		JspWriter out = pageContext.getOut();
		try {
			out.println(echo);//输出字符串
		} catch(Exception e) {
            logger.error(e.getMessage(), e);
		}
		
		return result;
		
	}
	
	public void release() {
		super.release();
		
		total = null;
		name = null;
		property = null;
		type = null;
		scope = DEFAULT_SCOPE;
		pageSize = PAGE_SIZE;
		formName = null;
		formIndex = 0;
		url = null;
		navParamName = NAV_PARAM;

		sumPage = 0;
		currPage = 1;
		sum = -1;

	}
	
	/**
	 * 处理标签
	 */
	private String process() throws Exception {

		StringBuffer nav = new StringBuffer();
		ServletRequest request = pageContext.getRequest(); //获取request对象
		
		String size = request.getParameter(PAGESIZE);
		if(size != null && !"".equals(size)) {
			pageSize = Integer.parseInt(size);
		} else {
			pageSize = PAGE_SIZE;
		}
		// total和name必须指定一个；如果都指定则total优先
		if (total == null && name == null) {
				throw new Exception("传入参数错误：没有指定记录总数。total和name属性必须指定一个。");
		}

		// 没有指定total的情况
		if (name != null) {
			request = pageContext.getRequest();
			Object obj;
			if ("session".equals(scope)) {//判断对象容器
				obj = pageContext.getSession().getAttribute(name);
			} else {
				obj = request.getAttribute(name);//name为count 获取count的值
				if ( null == obj){
					obj = "0";
				}
			}
			if (type != null) {
				// 指定了类名，需要使用反射机制来取得记录数
				try {
					Class c = Class.forName(type);
					Class[] cs = null;
					Method m = c.getMethod("get" + this.upperFirstChar(property), cs);
					Object[] objs = null;
					sum = Integer.parseInt(m.invoke(obj, objs).toString());
				} catch (Exception e) {
					throw new Exception("传入参数错误：没有指定记录总数。无法取得对象的属性值作为记录数。",e);
				}
			} else {
				// 直接用对象保存记录数
				try {
					sum = Integer.parseInt((String) obj);
				} catch (Exception e) {
					throw new Exception("传入参数错误：没有指定记录总数。对象不能转化为数字。",e);
				}
			}
		} else {
			try {
				sum = Integer.parseInt(total);
			} catch (Exception e) {
				throw new Exception("传入参数错误：没有指定记录总数。对象不能转化为数字。",e);
			}
		} 

		if (sum == 0) {
			nav.append("没有记录。");
		} else if (sum <= pageSize) {
			nav.append("共 <font color=\"").append(NUMBER_FONT).append("\">").append(sum).append(
					"</font> 条记录，每页显示  <select id='pageSize' name='pageSize' onchange=\"javascript:gotoPage(1,$('#pageSize').val());\">");
			//每页显示条数，下拉框列表
			int[] arr = new int[]{20,50,100,200,500,1000};
			for (int i : arr) {
				nav.append("<option style=\"color:").append(NUMBER_FONT).append("\" value=\"" + i + "\"");
				if (pageSize == i) {
					nav.append(" selected");
				}
				nav.append(">").append(i).append("</option>");
			}
			nav.append("</select>条记录。第 <font color=\"").append(NUMBER_FONT).append("\">1</font> 页，共 <font color=\"")
					.append(NUMBER_FONT).append("\">1</font> 页。");
		} else {
			nav.append("共 <font color=\"").append(NUMBER_FONT).append("\">").append(sum).append(
					"</font> 条记录，每页显示  <select id='pageSize' name='pageSize' onchange=\"javascript:gotoPage(1,$('#pageSize').val());\">");
			//每页显示记录数，下拉框列表
			int[] arr = new int[]{20,50,100,200,500,1000};
			for (int i : arr) {
				nav.append("<option style=\"color:").append(NUMBER_FONT).append("\" value=\"" + i + "\"");
				if (pageSize == i) {
					nav.append(" selected");
				}
				nav.append(">").append(i).append("</option>");
			}
			nav.append("</select>条记录。");
			
			countPage();
			try {
				currPage = Integer.parseInt(request.getParameter(navParamName));
			} catch (Exception e) {
				currPage = 1;
			}
			if (currPage < 1) {
				currPage = 1;
			}
			if (currPage > sumPage) {
				currPage = sumPage;
			}

			// 如果页数少于100，则用下拉列表跳转，否则用文本框跳转
			if (sumPage < 100) {
				nav.append("第 <select id='currPage' name='currPage' onchange=\"javascript:gotoPage($('#currPage').val(),$('#pageSize').val());\">");
				for (int i = 1; i <= sumPage; i++) {
					nav.append("<option style=\"color:").append(NUMBER_FONT).append("\" value=\"" + i + "\"");
					if (currPage == i) {
						nav.append(" selected");
					}
					nav.append(">").append(i).append("</option>");
				}
				nav.append("</select> 页");
			} else {
				nav.append("第 <input type=\"text\" id=\"_pf_nav_text_\" class=\"textSearch\" value=\"").append(currPage).
				append("\"> 页<input type=\"button\" class=\"operatebuttonSearch\" value=\"跳转\" style=\"cursor:pointer;\" onclick=\"javascript:gotoPage(0,$('#pageSize').val());\">");
			}
			
			nav.append("，共 <font color=\"").append(NUMBER_FONT).append("\">").append(sumPage).append(
					"</font> 页。");

			int next, pre;
			next = currPage + 1;
			pre = currPage - 1;
			if (next > sumPage) {
				next = sumPage;
			}
			if (pre < 1) {
				pre = 1;
			}

			if (currPage == 1) {
				nav.append("<font class=\"e-disabled\" >首页</font>  ");
				nav.append("<font class=\"e-disabled\" >上一页</font>  ");
			} else {
				nav.append("<a href=\"#\" onclick=\"javascript:return gotoPage(1,$('#pageSize').val());\">首页</a> ");
				nav.append("<a href=\"#\" onclick=\"javascript:return gotoPage(").append(pre).append(",$('#pageSize').val());\">上一页</a> ");
			}
			if (currPage == sumPage) {
				nav.append("<font color=\"").append(DISABLED_URL).append("\">下一页</font> ");
				nav.append("<font color=\"").append(DISABLED_URL).append("\">末页</font>");
			} else {
				nav.append("<a href=\"#\" onclick=\"javascript:return gotoPage(").append(next)
						.append(",$('#pageSize').val());\">下一页</a>  ");
				nav.append("<a href=\"#\" onclick=\"javascript:return gotoPage(").append(sumPage).append(",$('#pageSize').val());\">末页</a>");
			}
		}
		nav.append("<script language=\"javascript\">");
		nav.append("function gotoPage(index,pageSize) {");
		nav.append("if (index == 0) {index = document.getElementById(\"_pf_nav_text_\").value;}");

		if (url != null) {
			nav.append("location.href = \"").append(url);
			if (url.indexOf('?') == -1) {
				nav.append('?');
			} else {
				nav.append('&');
			}
			nav.append(navParamName).append("=\" + index;");
			//添加每页显示条数
			nav.append('&');
			nav.append(PAGESIZE).append("=\" + pageSize;");
		} else {
			if (formName != null) {
				nav.append("var frm = document.").append(formName).append(";");
			} else {
				nav.append("var frm = document.forms[").append(formIndex).append("];");
			}

			nav.append("try {frm.").append(navParamName).append(".value = index;} catch (e) {");
			// 若表单没有导航参数则动态增加一个#14
			nav.append("var ele = document.createElement(\"input\");");
			nav.append("ele.type=\"hidden\";");
			nav.append("ele.name=\"").append(navParamName).append("\";");
			nav.append("ele.value=index;");
			nav.append("frm.appendChild(ele);");
			//添加每页显示几条记录
			nav.append("var ele2 = document.createElement(\"input\");");
			nav.append("ele2.type=\"hidden\";");
			nav.append("ele2.name=\"").append(PAGESIZE).append("\";");
			nav.append("ele2.value=pageSize;");
			nav.append("frm.appendChild(ele2);}");
			
//			nav.append("try {frm.").append("pageSize").append(".value = "+pageSize+";} catch (e) {");
//			// 若表单没有导航参数则动态增加一个#14
//			nav.append("var ele = document.createElement(\"input\");");
//			nav.append("ele.type=\"hidden\";");
//			nav.append("ele.name=\"").append("pageSize").append("\";");
//			nav.append("ele.value="+pageSize+";");
//			nav.append("frm.appendChild(ele);}");

			nav.append("frm.submit();");
		}
		nav.append("return false;}");
		nav.append("</script>");
		return nav.toString();
	}

	// 根据记录数，计算总页数
	private void countPage() {
		sumPage = sum / pageSize;
		if (sum % pageSize != 0){
			sumPage++;
		}
	}

	// 将字符串第一个字符大写
	private String upperFirstChar(String str) {
		if (str == null){
			return null;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
	}


	public int getFormIndex() {
		return formIndex;
	}

	public void setFormIndex(int formIndex) {
		this.formIndex = formIndex;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getNavParamName() {
		return navParamName;
	}

	public void setNavParamName(String navParamName) {
		this.navParamName = navParamName;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static String getNavParam() {
		return NAV_PARAM;
	}
}

package com.yun.yweb.web.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 简化一些表格元素操作
 * 
 * @author liudw 2012-06-02
 * 
 */
public class PageFormatTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(PageFormatTag.class);
	private static final String WIDTH = "98%";
	private static final String TB_ALIGN = "center";

	private String width;
	private String tbalign;
	private String align;
	private String styleClass;

	public PageFormatTag() {
		width = WIDTH;
		tbalign = TB_ALIGN;
		align = null;
		styleClass = null;
	}

	public int doStartTag() {

		StringBuffer format = new StringBuffer();
		format.append("<table ");
		if (styleClass != null) {
			format.append("class=\"").append(styleClass).append("\" ");
		}
		format.append("border=\"0\" cellpadding=\"4\" cellspacing=\"0\" width=\"").append(width).append("\" align=\"")
				.append(tbalign).append("\"><tr><td class=\"text-center\" align=\"right\" ");
		if (align != null) {
//			format.append(" align=\"").append(align).append("\"");
		}
		format.append(">");
		// format.append("><img border=\"0\" src=\"/images/ido.gif\" align=\"absmiddle\">&nbsp;&nbsp;");
		JspWriter out = pageContext.getOut();
		try {
			out.print(format);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return SKIP_PAGE;
		}

		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() {

		JspWriter out = pageContext.getOut();
		try {
			out.println("</td></tr></table>");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return SKIP_PAGE;
		}

		return SKIP_BODY;

	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getTbalign() {
		return tbalign;
	}

	public void setTbalign(String tbalign) {
		this.tbalign = tbalign;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

}

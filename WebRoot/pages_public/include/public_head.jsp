<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="/WEB-INF/tld/page-format.tld" prefix="pf"%> 
<%@ taglib uri="/WEB-INF/tld/page-navigator.tld" prefix="pn"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%
String _contextPath = request.getContextPath();
request.setAttribute("CONTEXT_PATH", _contextPath);
request.setAttribute("ctx", _contextPath);
String _basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+_contextPath+"/";
request.setAttribute("BASE_PATH", _basePath);
request.setAttribute("STATIC_SERVER_URL", _contextPath);
%>
<script type="text/javascript" src="${STATIC_SERVER_URL}/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${STATIC_SERVER_URL}/js/plugin/bootstrap/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<!--js脚本位置都是相对于网站的-->
<script type="text/javascript">
	var CONTEXT_PATH = '<%=_contextPath%>';
	var ctx = '<%=_contextPath%>';
	var BASE_PATH = '<%=_basePath%>';
</script>
<link rel="shortcut icon" href="${STATIC_SERVER_URL}/images/favicon.ico" type="image/x-icon" />
<link rel="icon" sizes="any" mask href="${STATIC_SERVER_URL}/images/favicon.svg" type="image/svg+xml" />

<!-- bootstrap -->
<link href="${STATIC_SERVER_URL}/js/plugin/awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${STATIC_SERVER_URL}/js/plugin/bootstrap/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script type="text/javascript" src="${STATIC_SERVER_URL}/js/fit/css3-mediaqueries.js"></script>
    <script type="text/javascript" src="${STATIC_SERVER_URL}/js/fit/html5shiv.min.js"></script>
    <script type="text/javascript" src="${STATIC_SERVER_URL}/js/fit/respond.min.js"></script>
<![endif]-->

<!-- 自定义公共样式 -->
<link href="${CONTEXT_PATH}/plugin/font/helveticaneuelt/helveticaneuelt.css" rel="stylesheet" type="text/css">
<link href="${CONTEXT_PATH}/css/global/useful.css" rel="stylesheet" type="text/css">
<link href="${CONTEXT_PATH}/css/global/framework.css" rel="stylesheet" type="text/css">
<link href="${CONTEXT_PATH}/css/global/global.css" rel="stylesheet" type="text/css">
<link href="${CONTEXT_PATH}/css/global/animate.min.css" rel="stylesheet" type="text/css">
<link href="${CONTEXT_PATH}/js/plugin/darsaSly/css/main.css" rel="stylesheet" type="text/css">
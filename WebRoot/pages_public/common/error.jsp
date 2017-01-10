<!DOCTYPE HTML>
<%@ page language="java"  pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>信息提示</title>
<%@ include file="/pages_public/include/public_base.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=_contextPath%>/css/error.css" />
<link rel="stylesheet" type="text/css" href="<%=_contextPath%>/css/mbox.css" />

<!--提示图标字体-->
<link rel="stylesheet" type="text/css" href="<%=_contextPath%>/plugin/font/warn/iconfont.css" />
<style>

</style>
</head>

<body>
<div id="e-ror-container">
    <!--头部-->
    <%@ include file="error_header.jsp" %>
    
    <!--主体-->
    <div id="e-ror-content">
        <div class="e-ror-container">
            <div class="e-ror-content">
                <ul class="e-ror-ul">
                    <li class="e-w120"><i class="icon iconfont roundclosefill">&#xe658;</i></li>
                    <li class="e-w430">
                        <p class="e-ror-h">错误信息提示</p>
                        <p class="e-ror-p">非常抱歉,${errorMessage}</br>您还可以点击<a href="javascript:window.history.back(-1)">立即返回</a></p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--底部-->
    <%@ include file="error_footer.jsp" %>
</div>

</body>
</html>

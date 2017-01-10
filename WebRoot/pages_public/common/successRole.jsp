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
                    <li class="e-w120"><i class="icon iconfont kaixin">&#xe653;</i></li>
                    <li class="e-w430">
                        <p class="e-ror-h">非常恭喜您!</p>
                        <p class="e-ror-p">您的
                            <c:if test="${ !empty msg }">${msg}</c:if>
                            <c:if test="${ empty msg }"> 操作成功!</c:if>
                            还可以点击
                            <%if("Y".equals(request.getParameter("isClose"))){%>
                                <span onclick="closeWin();">关闭</span>  
                            <%}%>
                        </p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--底部-->
    <%@ include file="error_footer.jsp" %>
</div>
<script type="text/javascript"> 
function closeWin(){
    parent.closeWin();
}
</script> 
</body>
</html>

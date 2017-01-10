<%@ page contentType="text/html; charset=utf-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="author" content="深圳银盛金融集团有限公司" /> 
<meta name="description" content="银盛统一用户业务管理中心" />
<meta name="keywords" content="银盛兑" />
<meta name="theme-color" content="#357ebd">

<title>银盛兑-注册</title>
<!--********** About js file and plug-in dependencies **********-->
<%@ include file="./pages_public/include/public_head.jsp" %>
<!--********** Use only the current page **********-->
<link href="${CONTEXT_PATH}/css/global/global_ltd.css" rel="stylesheet" type="text/css">
<link href="${CONTEXT_PATH}/css/register/register.css" rel="stylesheet" type="text/css">
</head>

<body onload="timingSkip('${pageContext.request.contextPath}/login.jsp', 120)">
<!--********** register_body_header **********-->

<!--********** content **********-->
<div style="margin-top: 0px; margin: auto;font-size: 15px" align="center">
	<p>提交成功</p>
	<p>我们将在7个工作日内前完成审核</p>
	<span id="show">2分0秒后跳转到登录首页</span><a href="${pageContext.request.contextPath}/login.jsp" style="padding-left: 10px;">手动跳转</a>
	
</div>

<!--********** public_footer **********-->
<%@ include file="../../pages_public/common/public_footer.jsp" %>
<!--********** Use only the current page **********-->
<script type="text/javascript" src="${STATIC_SERVER_URL}/js/jquery/jquery.min.js"></script>
<script type="text/javascript">
//定时跳转
function timingSkip(url, timeout) {
	var t = timeout;
	var f = Math.floor(t/60);
	var m = t % 60;
	var str = "";
	if(f > 0) {
		str = f + "分"
	}
	document.getElementById('show').innerHTML = str + m + "秒后跳转到登陆首页";
	if(--t > 0) {    
		setTimeout("timingSkip('" + url + "', " + t + ")",1000);    
	} else {      
		location = url;  
	}    
   
}
</script>
</body>
</html>
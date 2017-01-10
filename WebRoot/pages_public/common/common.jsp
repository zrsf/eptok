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
                    <li class="e-w120">
                        <!--<img src="<%=_contextPath%>/images/global/404.png">404错误-->
                        <i class="icon iconfont warn">&#xe663;</i>一般警告
                        <!--没有权限no_privilege/没有登录no_login-->
                        <i class="icon iconfont warnfill">&#xe662;</i>严重警告
                        <i class="icon iconfont roundclose">&#xe659;</i>一般错误<!--(弹窗用)-->
                        <i class="icon iconfont roundclosefill">&#xe658;</i>严重错误
                        <!--业务错误信息提示business_errors/
                            错误信息提示errors/error_footer/error_header/
                            异常信息提示exception/
                            系统错误信息提示system_errors-->
                        
                        <i class="icon iconfont jinzhi">&#xf00b4;</i>禁止
                        <i class="icon iconfont roundcheck">&#xe657;</i>打勾成功(增加成功/修改成功等【模态窗】)<!--(弹窗用)-->
                        <i class="icon iconfont roundcheckfill">&#xe656;</i>圆满成功(登录成功/注册成功等)
                        
                        <i class="icon iconfont zujianxinxi">&#xf0321;</i>组建-信息
                        <i class="icon iconfont zujianxianxingxinxi">&#xf0320;</i>组建-线性信息
                        <i class="icon iconfont tishi">&#xe628;</i>提示信息
                        <!--overtime/-->
                        <i class="icon iconfont jingshi">&#xe627;</i>警示信息
                        
                        <i class="icon iconfont kulian">&#xf014e;</i><!--哭脸-->
                        
                    <i class="icon iconfont kaixin">&#xe653;</i>
                    <!--success/successRole-->
                    </li>
                    <li class="class="e-w430"">
                        <p class="e-ror-h">您访问的页面不存在</p>
                        <p class="e-ror-p">可能该服务已经过期，或者您输入的地址有误。</br>返回<a class="modalBox" href="#">首页</a></p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--底部-->
    <%@ include file="error_footer.jsp" %>
</div>
<!--模态窗-->
<%@ include file="modalbox.jsp" %>
</body>
</html>

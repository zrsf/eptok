<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<script type="text/javascript">
function logout(){
	if (confirm("确认退出登录?")) {
		top.location = "${ctx}/login.do?method=logout";
	} else {
		return false;
	}
}
</script>
<!--**********head**********-->
<div class="e-head">
    <!-- top -->
    <div class="container-fluid e-bg-top">
        <div class="container">
            <div class="row e-top">
                <div class="col-md-12">
                    <ul class="list-unstyled list-inline e-abs-rt5">
                        <c:choose>
                    	<c:when test="${not empty sessionScope.YSUSERINFO.username}">
                    		<li>
		                        <a href="userInfo.do?method=userInfoQuery">欢迎您 , ${sessionScope.YSUSERINFO.username}</a>
		                        <div class="e-top-line"></div>
		                    </li>
		                    <li>
	                            <a target="blank" href="pages_public/common/online.jsp">在线客服</a>
	                            <div class="e-top-line"></div>
	                        </li>
	                        <li><a href="official/help.jsp">帮助中心</a></li>
	                        <li><a href="javascript:void(0);" onclick="logout();" title="安全退出">退出</a></li>
                   	 	</c:when>
                   	 	<c:otherwise>
            <!--        	 		<li>
	                            <a href="pages/exchange/eCheck.jsp">e券查询</a>
	                            <div class="e-top-line"></div>
	                        </li> -->
                   	 		<li>
	                            <a href="register.do?method=toRegisterEnte">在线注册</a>
	                            <div class="e-top-line"></div>
	                        </li>
	                        <li>
	                            <a target="blank" href="pages_public/common/online.jsp">在线客服</a>
	                            <div class="e-top-line"></div>
	                        </li>
	                        <li><a href="official/help.jsp">帮助中心</a></li>
                   	 	</c:otherwise>
                    </c:choose>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <!--logo navigation-->
    <div class="e-logo-nav">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <a class="e-logo" href="home.jsp" ><img src="${CONTEXT_PATH}/images/global/logo.png"></a>
                    <ul class="list-unstyled list-inline e-ltd-nav e-abs_r e-cur-on e-cur-tab">
                        <li class="e-cur-a" tab="e-cur-tab-01">
                            <a href="home.jsp">首页</a>
                        </li>
                        <li class="e-cur-b" tab="e-cur-tab-02">
                            <a href="official/service.jsp">产品服务</a>
                        </li>
                        <li class="e-cur-c" tab="e-cur-tab-03">
                            <a href="exchange.do?method=exchange">商品兑换</a>
                        </li> 
                        <li class="e-cur-d" tab="e-cur-tab-04">
                            <a href="official/case.jsp">解决方案</a>
                        </li>
                        <li class="e-cur-e" tab="e-cur-tab-05">
                            <a href="login.do?method=login">企业</a>
                        </li>
                        <li class="e-cur-f" tab="e-cur-tab-06">
                            <a href="official/about.jsp">关于我们</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
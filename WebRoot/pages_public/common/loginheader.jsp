<style>
.example {
    margin: 0px auto 0px;
}
.example .frame {
    width: 100%;
    height: 70px;
    line-height: 70px;
}
.example .frame {
    width: 100%;
    height: 70px;
    line-height: 70px;
}
.example .frame ul {
    height: 70px;
    font-size: 16px;
}
.example .frame ul li {
    width: 120px;
    height: 70px;
}
.controls {
    height: 70px;
    line-height: 70px;
}
.controls button{
    line-height: normal;
    background: #062e58;
    color: #8b919c;
    border: none;
}

/* bg */
.example .frame ul li {
    background: #1c4876;
    color: #fff;
}
.example .frame ul li a {
    color: #fff;
    font-weight: bold;
}
/* 设置一级导航高亮样式 */
.example .frame ul li.active {
    color: #ff8813;
    background: #1c4876;
    border-bottom: 2px solid #ff8813;
}
.example .frame ul li.active a {
    color: #ff8813;
}
/* 二级导航 */
.list_ul {
    list-style: none;
    padding: 0;
    margin: 0;
}
.list_ul li {
    display: inline-block;
    padding: 0 10px;
    margin-right: 8px;
    line-height: 38px;
}
.list_ul li a {
    color: #fff;
}
/* 设置二级导航高亮样式 */
.list_ul li.e-cur a {
    color: #ff8813;
}
</style>

<div class="container-fluid e-bg-top">
    <div class="container">
        <div class="row e-top">
            <div class="col-md-4">
                <ul class="list-unstyled list-inline e-abs-lt5">
                    <li><a href="home.jsp">官网首页</a></li>
                </ul>
            </div>
            <div class="col-md-8">
                <ul class="list-unstyled list-inline e-abs-rt5">
                    <li>
                        <a href="userInfo.do?method=userInfoQuery">欢迎您 , ${sessionScope.YSUSERINFO.username}</a>
                        <div class="e-top-line"></div>
                    </li>
                    <li>
                        <a target="blank" href="pages_public/common/online.jsp">在线客服</a>
                        <div class="e-top-line"></div>
                    </li>
                    <li><a href="official/help.jsp" target="blank">帮助中心</a></li>
                    <li><a href="javascript:void(0);" onclick="logout();" title="安全退出">退出</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<!--logo navigation-->
<div class="container-fluid e-blue">
    <div class="container">
        <div class="row" id="mainNavs">
            <div class="col-xs-12 col-md-12 col-lg-12" style="padding-right: 45px; padding-left: 45px;">
                <div class="example pagespan">
                    <div class="frame">
                        <ul class="e-clearfix">
                        <c:forEach items="${sessionScope.afterMenu}" var="menu" varStatus="status">
                            <c:if test="${menu.parentId=='0'}">
                            <li tab="e-cur-tab-0${status.index}" id="mainNav_${menu.id}">
                                <a href="distribution.do?method=distribution&limitId=${menu.id}" >${menu.name}</a>
                            </li>
                            </c:if>
                        </c:forEach> 
                        </ul>
                    </div>
                </div>
                <div class="controls" style="position: absolute; left: -30px; top: 0px;">
                    <button class="prev">
                        <i class="fa fa-backward"></i>
                    </button>
                </div>
                <div class="controls" style="position: absolute; right: -30px; top: 0px;">
                    <button class="next">
                        <i class="fa fa-forward"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>  
</div>

<!--企业二级导航-->
<div class="container-fluid animated list_bigbox" id="subNavs" style="display: none; height: 38px; background: #335F90;">
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-md-12 col-lg-12">
                <div class="list_box">
                    <ul class="list_ul" style="display: inline;">
                    <c:forEach items="${sessionScope.afterMenu}" var="menu1">
                        <c:if test="${menu1.parentId!='0'&& menu1.parentId==sessionScope.requestFId}">
                        <li id="sModule${menu1.id}">
                            <a href="${menu1.url}">${menu1.name}</a>
                        </li>
                        </c:if>
                    </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

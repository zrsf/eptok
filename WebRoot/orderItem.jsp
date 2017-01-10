<%@ page contentType="text/html; charset=utf-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="author" content="深圳银盛金融集团有限公司" /> 
<meta name="description" content="银盛统一用户业务管理中心" />
<meta name="keywords" content="随e兑" />
<meta name="theme-color" content="#357ebd">

<title>随e兑-订单查询</title>

<!--********** About js file and plug-in dependencies **********-->
<%@ include file="./pages_public/include/public_head.jsp" %>
<!--********** Use only the current page **********-->
<link href="${CONTEXT_PATH}/css/global/global_ltd.css" rel="stylesheet" type="text/css">
</head>

<body>
<!--********** loginheader **********-->
<%@ include file="./pages_public/common/loginheader.jsp" %>
<!--********** content **********-->
<div class="container e-chzhi-box e-bor-blue-a">

    <div class="row">
    	<div class="col-md-12">
            <h4 class="e-chzhi-tit">订单查询</h4>
        </div>
        <div class="col-md-12">
            <form class="form-horizontal e-pur-form e-mar-tb4874"  action="orders.do?method=orderItemQuery"  method="post" role="form" id="mainForm" name="mainForm">
                
                <div style="margin: 30px auto;">
                    <!--订单记录明细-->
                    <table class="table table-striped table-hover text-center table-bordered e-btn-detail e-order" style="">
                        <thead>
                            <tr class="info">
                                <th>序号</th>
                                <th>操作员编号</th>
                                <th>交易时间</th>
                                <th>订单号</th>
                                <th>交易类型</th>
                                <th>充值号码</th>
                                <th>面额</th>
                                <th>支付金额</th>
                                <th>交易状态</th>
                               
                            </tr>
                        </thead>
                        <c:forEach items="${beans}" var="par" varStatus="status">
                       
                            <tr>
                                <td>${status.index+1}</td>
                                  <td>${par.userCode}</td>
                                <td><fmt:formatDate type="both"
                                        value="${par.createTime}"></fmt:formatDate></td>
                                <td>${par.loginPwd}</td>
                                <td>${par.loginPwd }</td>
                                    <td>${par.loginTime}</td>
                                    <td>${par.state}</td>
                                	<td><fmt:formatNumber value="${par.pwdErrorNum}" type="number" pattern="0.00"/></td>
                                	<td>${par.userName }</td>
                            </tr>
                       
						</c:forEach>
						
                    </table>
                     <pf:rect styleClass="page" align="right">
                        <pn:nav name="count" formName="mainForm" scope="request" />
                     </pf:rect>	
                     
                </div>
            </form>
        </div>
    </div>
</div>
<!--********** public_footer **********-->
<%@ include file="./pages_public/common/public_footer.jsp" %>
<!--订单汇总弹窗-->
<div class="e-mask-box" id="orderItemshow" style="display: none;"></div>
<!--********** About js file and plug-in dependencies **********-->
<%@ include file="./pages_public/common/navigation.jsp" %>

</body>
</html>
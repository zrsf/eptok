<%@ page contentType="text/html; charset=utf-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="icon" href="favicon.ico" />
    <!--[if lt IE 9]>
      <script src="js/bootstrap/html5shiv.min.js"></script>
      <script src="js/bootstrap/respond.min.js"></script>
    <![endif]-->
    
    <title>后台管理中心</title>
	<!--********** About js file and plug-in dependencies **********-->
	<%@ include file="../../../pages_public/include/public_head.jsp" %>
	<!--********** Use only the current page **********-->
	<!-- 相对于网站根目录 -->
	<base href="${BASE_PATH}" />
	<link href="css/default.css" rel="stylesheet" type="text/css" />
	<script src="js/default.js" type="text/javascript"></script>
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-expanded="false"><i class="fa fa-user fa-fw"></i>&nbsp;${sessionScope.user.userCode }&nbsp;<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="top-right1.html">修改资料</a></li>
                            <li class="divider"></li>
                            <li><a href="user.do?method=loginOut">退出</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container-fluid all">
        <div class="sidebar">
            <ul class="nav">
                <li><a href="index.html">资料管理</a></li>
                <li class="has-sub">
                    <a href="javascript:void(0);"><span>后台管理</span><i class="fa fa-caret-right fa-fw pull-right"></i></a>
                    <ul class="sub-menu">
                        <li><a href="user.do?method=listUser"><i class="fa fa-circle-o fa-fw"></i>&nbsp;操作员预览</a></li>
                        <li><a href="role.do?method=listRole"><i class="fa fa-circle-o fa-fw"></i>&nbsp;角色</a></li>
                        <li><a href="roleApprove.do?method=listRoleApprove"><i class="fa fa-circle-o fa-fw"></i>&nbsp;用户管理审核</a></li>
                        <li><a href="privilege.do?method=listPrivilege"><i class="fa fa-circle-o fa-fw"></i>&nbsp;菜单</a></li>
                    </ul>
                </li>
                <li class="has-sub">
                	<a href="javascript:void(0);"><span>业务管理</span><i class="fa fa-caret-right fa-fw pull-right"></i></a>
                    <ul class="sub-menu">
                        <li><a href="user.do?method=listUser"><i class="fa fa-circle-o fa-fw"></i>&nbsp;会员注册</a></li>
                        <li><a href="user.do?method=expandedHref"><i class="fa fa-circle-o fa-fw"></i>&nbsp;推广链接</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="maincontent row">
            <div class="container e-chzhi-box e-bor-blue-a">

			    <div class="row">
			    	<div class="col-md-12">
			            <h4 class="e-chzhi-tit">推广链接审核</h4>
			        </div>
			        <div class="col-md-12">
			            <form class="form-horizontal e-pur-form e-mar-tb4874"  action="orders.do?method=orderItemQuery"  method="post" role="form" id="mainForm" name="mainForm">
			                
			                <div style="margin: 30px auto;">
			                    <!--订单记录明细-->
			                    <table class="table table-striped table-hover text-center table-bordered e-btn-detail e-order" style="">
			                        <thead>
			                            <tr class="info">
			                            	<th>序号</th>
			                                <th>报单类别</th>
			                                <th>购买时间</th>
			                                <th>姓名</th>
			                                <th>报单金额</th>
			                                <th>报单状态</th>
			                                <th>操作</th>
			                            </tr>
			                        </thead>
			                        <c:forEach items="${beans}" var="par" varStatus="status">
			                       
			                            <tr>
			                                <td>${status.index+1}</td>
			                                <td>
			                                	<c:if test="${par.userType eq '0' }">
			                                		前台注册
			                                	</c:if>
			                                	<c:if test="${par.userType eq '1' }">
			                                		后台注册
			                                	</c:if>
			                                </td>
			                                <td>
			                                	<fmt:formatDate type="both"
			                                        value="${par.createTime}"></fmt:formatDate>
			                                </td>
			                                <td>${par.userCode}</td>
			                                <td>300.00</td>
			                                <td>未确认</td>
			                                <td>
			                                	<a href="javascript:void(0)" onclick="approveUserTemp('${par.userCode}',2, '删除')" class="apply">删除</a>|
			                                	<a href="javascript:void(0)" onclick="approveUserTemp('${par.userCode}',1,'激活')" class="apply">激活</a>
			                                </td>
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
        </div>
    </div>
<script type="text/javascript" src="${CONTEXT_PATH}/js/bisinessmanager/approveUser.js"></script>
</body>
</html>

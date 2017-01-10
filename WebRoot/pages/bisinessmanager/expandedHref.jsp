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
            <!--我是主要内容 start-->
            <ul class="breadcrumb">
                <li class="active">首页</li>
            </ul>
            <div class="col-sm-12">
                <div class="jumbotron">
                    <table>
                    	<tr>
                    		<td>推广链接：</td>
                    		<td>${expandedHref }</td>
                    	</tr>
                    </table>
                </div>
            </div>
            <!--我是主要内容 end-->
        </div>
    </div>

</body>
</html>

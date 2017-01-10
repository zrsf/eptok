<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@page import="javax.servlet.http.Cookie"%>
<!-- 相对于网站根目录 -->
<base href="${pageContext.request.contextPath}/">
<%  
Cookie[] cookies = request.getCookies();  
String username="";  
if (cookies != null) {  
    for (Cookie c : cookies) {  
        if ("userEmail".equals(c.getName())) {  
            username=c.getValue();
            break;  
        }  
    }  
}  
%>
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

<title>随e兑官网-登录</title>

<!--********** About js file and plug-in dependencies **********-->
<%@ include file="pages_public/include/public_head.jsp" %>



<script type="text/javascript">
/**
 * js脚本位置都是相对于网站的
 */
var CONTEXT_PATH = '${CONTEXT_PATH}';
var ctx = '${CONTEXT_PATH}';
var BASE_PATH = '${BASE_PATH}';
$(function(){
	checkBrowser();
});

function checkBrowser(){
	if (navigator.userAgent.indexOf('Firefox') >= 0){
        //判断是否是Firefox浏览器
        //$(".e-form-ctrl").css({
        	  //"height":"46px",
        	  //"width":"118%"
        	  //});
        
        $("#_ocx_password_td").removeClass("e-fc-a");
        $("#_ocx_password_td").addClass("e-fc-b");
        $("#_ocx_password").removeClass("e-ocx_ipt-a");
        $("#_ocx_password").addClass("e-ocx_ipt-b");
        
    }else {
        //判断是否是其他浏览器
    	//$(".e-form-ctrl").css({
        	 // "height":"34px",
        	  //"width":"100%"
        	  //});
        $("#_ocx_password_td").removeClass("e-fc-b");
        $("#_ocx_password_td").addClass("e-fc-a");
        $("#_ocx_password").removeClass("e-ocx_ipt-b");
        $("#_ocx_password").addClass("e-ocx_ipt-a");
    }
    
}
/**
 * 看不清刷新一个新的验证码
 */
function refreshVerify(obj){
	obj.src=obj.src+"&s="+new Date();
}

/**
 * 修改密码框样式
 */
function changeBlue() {
    $(".e-control-a").css("border-color","#1E88E5");
    $("#mima").tooltip('destroy');
}
function changeGray() {
    $(".e-control-a").css("border-color","#DCDEE0");
}

function submit1(obj){
	validation1(obj);
}

function validation1(obj){
	with(obj){
		if(usercode.value==""){
               var msg = '用户名不能为空';
               $("#username").tooltip({
                   trigger: 'manual',
                   placement: 'top',
                   title: msg
               }).attr('data-original-title', msg).tooltip('show');
               
			 //$("#tip").style="text-align: center";
			 //$("#tip").html('<img src="${pageContext.request.contextPath}/images/fault.gif" align="absmiddle">&nbsp;用户名不能为空.');
			 $("#username").focus(function(){
				 $("#username").tooltip('destroy');
			 });
			return false;
		}else {
               
			 $.ajax({
					url: "${CONTEXT_PATH}"+"/ocx/srand_num.jsp?"+get_time(),
					type: "GET",
					async: false,
					success: function(srand_num){
					    pgeditor.pwdSetSk(srand_num);
					}
				 });
				var PwdResult=pgeditor.pwdResult();
				if(pgeditor.pwdLength()==0){
					 $("_ocx_password").focus();
					 	var msg = '密码不能为空';
                       $("#mima").tooltip({
                           trigger: 'manual',
                           placement: 'bottom',
                           title: msg
                       }).attr('data-original-title', msg).tooltip('show');
                       /* pgeditor.pge.pgeOnfocus(function(){
          				 	$("#mima").tooltip('destroy');
          			 	}); */
					 return false;
				}else{
                       $("#mima").tooltip('destroy');
                   }
				if(validateCode.value==""){
					var msg = '验证码不能为空';
					 $("#validateCode").tooltip({
                           trigger: 'manual',
                           placement: 'bottom',
                           title: msg
                       }).attr('data-original-title', msg).tooltip('show');

					 $("#validateCode").focus(function(){
                    	   $("#validateCode").tooltip('destroy');
                       });
					return false;
				}else{
                       $("#validateCode").tooltip('destroy');
                   }
				
				$("#password").val(PwdResult);//获得密码密文,赋值给表单
				$("#macAddr").val(pgeditor.machineNetwork()); //获取网卡信息
				if($("#password").val() != ''){
					$("#mainForm").attr("action",BASE_PATH+"login.do?method=login");
					$(loginbutton).attr("disabled",true);
					$(loginbutton).val('正在登录');
				}else {
					 	var msg = '密码不能为空';
                        $("#username").tooltip({
                           trigger: 'manual',
                           placement: 'top',
                           title: msg
                       }).attr('data-original-title', msg).tooltip('show');
                       
					 return false;
				}
		
		}
		
	}
}
</script>

<!-- from xiaomai -->
<script type="text/javascript">
$(function(){
	/**发送验证码*/
	$("#sendCode").click(function(){
   		var account = $("#username").val();
    	if (account=='') {
            $("#username").tooltip({
                trigger: 'manual',
                placement: 'top',
                title: '用户名不能为空'
            });
            $("#username").tooltip('show');
	            
		 	//$("#tip").style="text-align: center";
		 	//$("#tip").html('<img src="${pageContext.request.contextPath}/images/fault.gif" align="absmiddle">&nbsp;用户名不能为空.');
		 	username.focus();
			return false;		
		}

		$.post("login.do?method=sendMesg",{mobile:account},function(data){		
			if(data=='erro'){
				//手机号码不正确
				alert("发送失败")
			}else if(data == 'succ'){
				/* 发送成功,按钮失效放重复发送 */
				$("#sendCode").attr("disabled","disabled");
				$("#phoneCode").val("");
				/* 下次发送等待时间 */
				nextSendAuthCodetTime(120);
			}
		})  
    });

	/* 验证码发送成功后重新发送需要等待时间  */
	var nextSendAuthCodetTime = function (timeOut){
		if(timeOut <= 0){
			$("#sendCode").removeAttr("disabled","disabled");
			$("#sendCode").attr("value",'重新发送');//重新发送");
		 }else{
		 	timeOut--;
	        $("#sendCode").attr("value",'('+timeOut+') 发送成功');
		    setTimeout("nextSendAuthCodetTime("+timeOut+")",1000);
		 }
}
	 
});

</script>


<style>
/*头部不固定覆盖样式*/
.e-head {
    position: relative;
}

html input[type="button"]{
    -webkit-appearance: button;
    cursor: default;
}
.form-control[disabled],
.form-control[readonly],
fieldset[disabled] .form-control {
  background-color: #eee;
  opacity: 1;
}
</style>
<!--[if lte IE 10]> 
<link href="${CONTEXT_PATH}/css/company/ie.css" rel="stylesheet" type="text/css">
<![endif]--> 
</head>

<body>
<!--********** public_header **********-->
<%@ include file="pages_public/common/public_header.jsp" %>
<!--********** content **********-->
<div style="height: 642px; background: url('${CONTEXT_PATH}/images/company/login_bg.png') center top;" class="">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div style="position: absolute; right: 0; top: 96px; width: 390px;">
                    <div class="e-log-box" style="border: 1px solid #e3e3e3;">
                      <h3 class="text-center" style="color: #363B40;padding: 6px 0px 18px;">登录企业平台1${username}</h3>
                        <form class="form-horizontal" id="mainForm" method="post" action="user.do?method=login" id="mainForm" name="mainForm" onsubmit="return validation1(this);">
                           
                            <div class="form-group">
                                <label class="hidden" for="userCode">账号：</label>
                                <input id="userCode" name="userCode" type="text" value="${user.userCode }" class="form-control"  tabindex="1" placeholder="请输入帐号">
                            </div>
                            <div class="form-group" id="mima">
                                <label class="hidden" for="loginPwd">账号：</label>
                                <input id="loginPwd" name="loginPwd" type="password" value="${user.loginPwd }" class="form-control"  tabindex="1" placeholder="请输入密码">
                            </div>
                            
                             <div class="form-group" >
                                <div class="col-sm-6" style="padding-left: 0;">
                                    <input id="validateCode" name="validateCode" type="text" class="form-control"  autocomplete="off"  tabindex="3" placeholder="验证码">
                                </div>
                                <div class="col-sm-6" style="padding-right: 0;">
                                 <!-- <input type="button"   class="form-control" value="发送验证码" style="width:100%;" id="sendCode" />  -->
                                      <div class="form-control" style="border:none; padding: 0; height: 50px; overflow: hidden;">
                                        <img alt="点击刷新" title="验证码" src="${pageContext.request.contextPath}/code.do?method=generateCode" onclick="refreshVerify(this);" style="cursor: pointer; width: 100%; "  class="" > 
                                    </div>
                                </div>
                            </div>
                            <div class="form-group" >
                                <div class="col-sm-3 text-left" style="padding-left: 0; padding-right: 0;">
                                    <div class="checkbox">
                                        <label>
                                            <input name="jzusername" type="checkbox" value="1" > 记住账号
                                        </label>
                                    </div>
                                </div>
                                <div class="col-sm-9" style="padding-left: 0; padding-right: 0;">
                                    <div class="text-right e-reg-new" style="height: 27px; padding-top: 7px">
                                        <a href="${pageContext.request.contextPath}/userInfo.do?method=findLoginPw" style="border-right: 1px solid #616366;">忘记密码</a>
                                        <a href="${pageContext.request.contextPath}/register.do?method=toRegisterEnte">在线注册</a>
                                    </div>
                                </div>
                            </div>
                             <div class="form-group">
                                <div class="col-sm-6" style="padding-left: 0;">
                                    <input type="submit" id="loginbutton" value="登录"  class="button" id="tijiao" tabindex="4" style="width:100%;"/>
                                </div>
                                <div class="col-sm-6" style="padding-right: 0;">
                                    <input type="reset" value="重置" class="button" style="width:100%;"/>
                               </div>
                            </div>
                              
                            <input type="hidden" name="langType1" value="${localLanguage}"  />
                            <input type="hidden" name="result" value="${result}" />
                            <input type="hidden" name="message"   id="message" value="${message}"/>
                            <input type="hidden" name="macAddr" id="macAddr" value=""/>
                        </form>
                        <div id="tip" class="tip" style="display: none;text-align: center"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
</div>
<ul class="">
<li>
</li>
</ul>
<!--********** public_footer **********-->
<%@ include file="pages_public/common/public_footer.jsp" %>

</body>
</html>
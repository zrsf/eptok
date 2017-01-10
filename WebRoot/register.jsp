<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@page import="javax.servlet.http.Cookie"%>
<!-- 相对于网站根目录 -->
<base href="${pageContext.request.contextPath}/">
<%
	Cookie[] cookies = request.getCookies();
	String username = "";
	if (cookies != null) {
		for (Cookie c : cookies) {
			if ("userEmail".equals(c.getName())) {
				username = c.getValue();
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
<%@ include file="pages_public/include/public_head.jsp"%>
</head>
<body>
	<form name="form1" method="post"
		action="user.do?method=register" id="form">
		<table class="tablebg" id="table1">
			<tbody>
				<tr>
					<td class="tbkey">会员编号：</td>
					<td class="tbval"><input type="text" value="" name="userCode"
						id="userCode"> &nbsp;<span id="state_userid" class="msg">*</span>
					</td>
				</tr>
				<!--基本信息-->
				<tr>
					<td class="tbkey">姓名：</td>
					<td class="tbval"><span><input type="text" value=""
							name="userName"></span>&nbsp;<span id="state_name" class="msg">*</span></td>
				</tr>
				<tr>
					<td class="tbkey">昵称：</td>
					<td class="tbval"><span><input type="text" value=""
							name="nickName"></span>&nbsp;<span id="state_alias" class="msg"></span></td>
				</tr>
				<tr>
					<td class="tbkey">证件号码：</td>
					<td class="tbval"><span><input type="text" value=""
							name="idNo"></span>&nbsp;<span id="state_id_card" class="msg"></span></td>
				</tr>
				<tr>
					<td class="tbkey">Email：</td>
					<td class="tbval"><span><input type="text" value=""
							name="email"></span>&nbsp;<span id="state_email" class="msg"></span></td>
				</tr>
				<tr>
					<td class="tbkey">QQ：</td>
					<td class="tbval"><span><input type="text" value=""
							name="qqNumber"></span>&nbsp;<span id="state_qq" class="msg"></span></td>
				</tr>
				<!--微信填写-->
				<tr>
					<td class="tbkey">微信账号：</td>
					<td class="tbval"><span><input type="text" value=""
							name="weixinNumber" onkeyup="getInfo(this)" id="weixin"
							autocomplete="off" maxlength="200"></span>&nbsp;&nbsp;<span
						class="msg" id="state_weixin">*</span></td>
				</tr>
				<tr>
					<td class="tbkey">移动电话：</td>
					<td class="tbval"><span><input type="text" value=""
							name="mobile"></span>&nbsp;<span id="state_mobile" class="msg">*</span></td>
				</tr>
				<tr>
					<td class="tbkey">推荐人编号：</td>
					<td class="tbval"><span style="color: #000">${param.userCode}</span> <input
						type="hidden" value="${param.userCode}" size="20" name="parentUsercode"
						onkeyup="getInfo(this)" id="net_7" autocomplete="off">
						&nbsp;<span id="state_net_7" class="msg"></span></td>
				</tr>
				<!--所属商铺-->

				<tr>
					<td class="tbkey">开户行：</td>
					<td class="tbval"><select name="bankType" id="bankType">
							<option value="">请选择</option>
							<option value="中国银行">中国银行</option>
							<option value="中国建设银行">中国建设银行</option>
							<option value="中国工商银行">中国工商银行</option>
							<option value="中国农业银行">中国农业银行</option>
							<option value="财付通">财付通</option>
							<option value="支付宝">支付宝</option>
							<option value="浦发银行">浦发银行</option>
							<option value="交通银行">交通银行</option>
							<option value="中国民生银行">中国民生银行</option>
							<option value="中国光大银行">中国光大银行</option>
							<option value="中国邮政储蓄银行">中国邮政储蓄银行</option>
							<option value="兴业银行">兴业银行</option>
							<option value="中信银行">中信银行</option>
							<option value="招商银行">招商银行</option>
							<option value="华夏银行">华夏银行</option>
					</select> &nbsp;<span id="bank_apply_name" class="msg">*</span></td>
				</tr>
				<tr>
					<td class="tbkey">银行卡号：</td>
					<td class="tbval"><span><input type="text" value=""
							name="bankNo"></span>&nbsp;<span id="state_bank_card"
						class="msg"></span>*</td>
				</tr>
				<tr>
					<td class="tbkey">开户名：</td>
					<td class="tbval"><span><input type="text" value=""
							name="bankOwner"></span>&nbsp;<span id="state_bank_name"
						class="msg"></span>*</td>
				</tr>
				<tr>
					<td class="tbkey">开户地址：</td>
					<td class="tbval"><span><input type="text" value=""
							name="bankAddress"></span>&nbsp;<span
						id="state_bank_apply_addr" class="msg">*</span></td>
				</tr>
				<tr>
					<td class="tbkey">一级密码：</td>
					<td class="tbval"><span><input type="password"
							autocomplete="off" value="" name="loginPwd"></span>&nbsp;<span
						id="state_pass1" class="msg">*</span></td>
				</tr>
				<tr>
					<td class="tbkey">一级密码确认：</td>
					<td class="tbval"><span><input type="password"
							autocomplete="off" value="" name="pass1c"></span>&nbsp;<span
						id="state_pass1c" class="msg">*</span></td>
				</tr>
				<tr>
					<td class="tbkey">二级密码：</td>
					<td class="tbval"><span><input type="password"
							autocomplete="off" value="" name="secondPwd"></span>&nbsp;<span
						id="state_pass2" class="msg">*</span></td>
				</tr>
				<tr>
					<td class="tbkey">二级密码确认：</td>
					<td class="tbval"><span><input type="password"
							autocomplete="off" value="" name="pass2c"></span>&nbsp;<span
						id="state_pass2c" class="msg">*</span></td>
				</tr>
				<tr>
	                <td colspan="2">
	                	<input type="submit" value="提交" >
	                </td>
                </tr>
			</tbody>
		</table>
	</form>

</body>
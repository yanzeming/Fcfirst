<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员登录</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	function checkLogin(){
		//alert("3223");
		var username = document.getElementById("username").value;
		var password=document.getElementById("password").value;
			alert(username+"密码是"+password);
			// 1.创建异步加载对象:
			var xhr = createXMLHttpRequest();
			// 2.设置监听
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						var data = xhr.responseText;
						document.getElementById("loginmsg").innerHTML = data;
					}
				}
			}
			
			var url ="${ pageContext.request.contextPath }/user_login.action";
			/* timeStamp=" + new Date().getTime()"&username="+username; */
			/* var url = "${pageContext.request.contextPath}/user_checkUserName"; */
			var queryString = "username=" +username+"&password="+password;
			xhr.open("POST", url);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.send(queryString ); 
	}
	
	
	function createXMLHttpRequest() {
		var xmlHttp;
		try { // Firefox, Opera 8.0+, Safari
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {// Internet Explorer
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}

		return xmlHttp;
	}
</script>

</head>
<body>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="http://localhost:8080/mango/">
				<img src="${pageContext.request.contextPath}/image/renleipic_01/logo.gif" alt="传智播客">
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
		<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
</div>	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="./会员登录.htm">登录</a>|
				</li>
				<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="./会员注册.htm">注册</a>|
				</li>
				<li id="headerUsername" class="headerUsername"></li>
				<li id="headerLogout" class="headerLogout">
					<a href="./index.htm">[退出]</a>|
				</li>
						<li>
							<a >会员中心</a>
							|
						</li>
						<li>
							<a >购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a href="./购物车.htm">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>
	<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="./index.htm">首页</a>
						|
					</li>
					<li>
						<a href="./蔬菜分类.htm">定制套餐</a>
						|
					</li>
					<li>
						<a >安全频道</a>
						|
					</li>
					<li>
						<a >亿家卡</a>
						|
					</li>
					<li>
						<a >蔬菜基地</a>
						|
					</li>
					<li>
						<a >节气养生</a>
						|
					</li>
					<li>
						<a>便民服务</a>
						|
					</li>
					
		</ul>
	</div>
</div>	
<div class="container login">
		<div class="span12">
<%-- <div class="ad">
	<img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
</div>	 --%>	
</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>会员登录</strong>USER LOGIN
					</div>
					<div class="title">
						<s:actionerror/>
					</div>
					<form id="loginForm" action=""  method="post" novalidate="novalidate">
						<table>
							<tbody><tr>
								<th>
										用户名:
								</th>
								<td>
									<input type="text" id="username" name="username" class="text" maxlength="20"/><span><s:fielderror fieldName="username"/></span>
								</td>
							</tr>
							<tr>
								<th>
									密&nbsp;&nbsp;码:
								</th>
								<td>
									<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off"/><span><s:fielderror fieldName="password"/></span>
								</td>
							</tr>
								<tr>
									<th>
										验证码:
									</th>
									<td>
										<span class="fieldSet">
											<input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4" autocomplete="off"><img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath}/image/captcha.jhtml" title="点击更换验证码">
										</span>
									</td>
								</tr>
							<tr>
								<th>&nbsp;
									
								</th>
								<td>
									<label>
										<input type="checkbox" id="isRememberUsername" name="isRememberUsername" value="true">记住用户名
									</label>
									<label>
										&nbsp;&nbsp;<a >找回密码</a>
									</label>
								</td>
							</tr>
							<tr>
								<th>&nbsp;
									
								</th>
								<td>
									<input type="button" class="submit" value="登 录" onclick="checkLogin()">
									 <span id="loginmsg"></span>
								</td>
							</tr>
							<tr class="register">
								<th>&nbsp;
									
								</th>
								<td>
									<dl>
										<dt>还没有注册账号？</dt>
										<dd>
											立即注册即可体验在线购物！
											<a href="${ pageContext.request.contextPath }/user_registPage.action">立即注册</a>
										</dd>
									</dl>
								</td>
							</tr>
						</tbody></table>
					</form>
				</div>
			</div>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
	  <div class="footerAd"><img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势" /></div>	
	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a>招贤纳士</a>
						|
					</li>
					<li>
						<a>法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a>服务声明</a>
						|
					</li>
					<li>
						<a>广告声明</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
	</div>
</div>
</body></html>
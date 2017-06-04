<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>登录 - Powered By Mango Team</title>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/register.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	
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
	function change(){
		var img=document.getElementById("checkImg");
		//alert(121);
		img.src="${pageContext.request.contextPath}/checkImg.action?"+new Date();
	}
	window.codepass=0;
	function checkCode(){
		var code=document.getElementById("checkcode").value;
		//alert(code);
		if(code==null){
			document.getElementById("code").innerHTML =
				"<font color='red'验证码不能为空</font>";
		}else {
			var xhr = createXMLHttpRequest();
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						window.codepass= xhr.responseText;
						//alert(window.codepass);
						if(window.codepass==0){
							document.getElementById("code").innerHTML ="<font color='red'>验证码错误</font>";
						}else if(window.codepass==1) {
							document.getElementById("code").innerHTML ="<font color='green'>验证码正确</font>";
							
						}
						
					}
				}
				
			}
			var url =	"${pageContext.request.contextPath}/user_checkCode";
			/* timeStamp=" + new Date().getTime()"&username="+username; */
			/* var url = "${pageContext.request.contextPath}/user_checkUserName"; */
			var queryString = "timeStamp=" + new Date().getTime()+"&checkcode="+code;
			xhr.open("POST", url);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.send(queryString ); 
		}
	}
	function checkFrom(){
		checkCode();
		/*  alert(password);
		alert(checkPassword()&&checkRepassword()&&ema&&use); */
		if(window.codepass==1) return true;
		else return false;
	}
</script>
</head>
<body>
	<div class="container header">
		<%@ include file="header.jsp" %>
		 <%@ include file="menu.jsp" %>  
</div>	
	</div>
	<div class="container register">
		<div class="span24">
			<div class="wrap">
				<div class="main clearfix">
					<div class="title">
						<strong>登录</strong>USER REGISTER

					</div>
					<form id="registerForm"
						action="${ pageContext.request.contextPath }/user_login.action"
						method="post" novalidate="novalidate"
						onsubmit="return checkForm();">
						<table>
							<tbody>
								<s:if test="#session.rememberUser!=null">
								有保存的用户
									<tr>
									<th><span class="requiredField">*</span>用户名:</th>
										<td><input type="text" id="username" name="username" 
										value="<s:property  value="#session.remember.username"/>"
											class="text" maxlength="20"/></td>
									</tr>
									<tr>
									<th><span class="requiredField">*</span>密&nbsp;&nbsp;码:</th>
									<td><input type="password" id="password" name="password"
										value="<s:property  value="#session.remember.password"/>"
										class="text" maxlength="20" />
									</td>
								</tr>
								</s:if>
								<s:else>
								<tr>
									<th><span class="requiredField">*</span>用户名:</th>
									<td><input type="text" id="username" name="username" 
									onblur="checkUserName()"
										class="text" maxlength="20"/><span
										id="span1"><s:fielderror fieldName="username" /><!-- onblur="checkUserName()" --> 
									</span></td>
								</tr>
								<tr>
									<th><span class="requiredField">*</span>密&nbsp;&nbsp;码:</th>
									<td><input type="password" id="password" name="password"
										class="text" maxlength="20" autocomplete="off" onblur="checkPassword()"/> <span id="pass"></span>
										<span><s:fielderror
												fieldName="password" />
									</span></td>
								</tr>
								</s:else>
								<tr>
									<th><span class="requiredField">*</span>验证码:</th>
									<td><span class="fieldSet"> <input type="text"
											id="checkcode" name="checkcode" class="text captcha"
											maxlength="4" autocomplete="off"   onblur="checkCode()">
											<img id="checkImg" class="captchaImage"
												src="${pageContext.request.contextPath}/checkImg.action"
												title="点击更换验证码">
									</span><input type="button" value="点击更换" onclick="change()">
									<span id="code"></span>	
									</td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><input type="submit" class="submit" value="登录" onclick="return checkFrom()">
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
										&nbsp;&nbsp;<a href="${ pageContext.request.contextPath }/user_findPassWordPage">找回密码</a>
										</label>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="container footer">
		<div class="span24">
			<div class="footerAd">
				<img src="${pageContext.request.contextPath}/image/footer.jpg"
					width="950" height="52" alt="我们的优势" title="我们的优势">
			</div>
		</div>
		<div class="span24">
			<ul class="bottomNav">
				<li><a>关于我们</a> |</li>
				<li><a>联系我们</a> |</li>
				<li><a>招贤纳士</a> |</li>
				<li><a>法律声明</a> |</li>
				<li><a>友情链接</a> |</li>
				<li><a target="_blank">支付方式</a> |</li>
				<li><a target="_blank">配送方式</a> |</li>
				<li><a>服务声明</a> |</li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div class="span24">
			<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
		</div>
	</div>
	<div id="_my97DP"
		style="position: absolute; top: -1970px; left: -1970px;">
		<iframe style="width: 190px; height: 191px;"
			src="./会员注册 - Powered By Mango Team_files/My97DatePicker.htm"
			frameborder="0" border="0" scrolling="no"></iframe>
	</div>
</body>
</html>
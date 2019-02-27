<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/initial.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.messages_cn.js"></script>
	
	<script type="text/javascript">
		$(function(){
		
			 $("#myForm").validate({
				rules:{
					"manager.username":{ required:true},
					"manager.password":{required:true},
					"manager.telnumber":{digits:true},
					"manager.email":{required:true}
				}
			});
		});
	</script>
  </head>
  
  <body>
  		<div class="size title">
    		<h1>客户关系管理系统</h1>
    	</div>
    		
    	<div class="size login_div">
			管理员注册<br/><br/>
			<div class="msg"><s:property value='msg' /></div>
			<form id="myForm" action="${pageContext.request.contextPath }/manager_register.action" method="post">
				<table class="login_table">
					<tr>
						<td>登录名:</td>
						<td><input type="text"  name="manager.username" value='<s:property value="manager.username"/>'/></td>
					</tr>
					<tr>
						<td>密码:</td>
						<td><input id="first" type="password"  name="manager.password" /></td>
					</tr>
					<tr>
						<td>确认密码:</td>
						<td><input id="second" type="password" name="confirm" /></td>
					</tr>
					<tr>
						<td>手机号码:</td>
						<td><input  type="text" name="manager.telnumber" /></td>
					</tr>
					<tr>
						<td>邮箱:</td>
						<td><input  type="email"  name="manager.email"><td>
					</tr>
				</table>
				<br />
				<input class="button" type="submit" value="注册" /> <br /><br />
			</form>
    	</div>
  </body>
</html>

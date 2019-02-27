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
    
    <title>客户关系管理系统登录界面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/initial.css" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css" />
    </head>
    <body>
    	<div class="size title">
    		<h1>客户关系管理系统</h1>
    	</div>
    		
    	<div class="size login_div">
			管理员登录<br/><br/>
			<div class="msg"><s:property value='msg' /></div>
			<form action="${pageContext.request.contextPath }/manager_login.action" method="post">
				<table class="login_table">
					<tr>
						<td>登录名:</td>
						<td><input type="text" name="manager.username" value='<s:property value="manager.username"/>'/></td>
					</tr>
					<tr>
						<td>密码:</td>
						<td><input type="password" name="manager.password" /></td>
					</tr>
				</table>
				<br />
				<input class="button" type="submit" value="登录" /> <br /><br />
				<a href="${pageContext.request.contextPath}/register.jsp">点击注册</a>
			</form>
    	</div>
 	</body>
</html>
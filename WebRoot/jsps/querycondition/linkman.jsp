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
    
    <title>My JSP 'linkman.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/initial.css" />
  </head>
  
  <body>
    <div>
    	当前位置：综合查询 > 联系人信息查询
    	</div>
    	<br />
    	<div class="msg"><s:property value='msg'/></div>
    	<form action="${pageContext.request.contextPath}/linkman_findAllLinkMan.action" method="post" enctype="multipart/form-data">
    		<table>
    			<tr>
    				<td>所属客户：</td>
    				<td>
    					<input type="text" name="linkMan.customer.custName"/>
    				</td>
    			</tr>
    			<tr>
    				<td>联系人名称：</td>
    				<td><input type="text" name="linkMan.linkName"/></td>
    				<td>联系人性别:</td>
    				<td><input type="text" name="linkMan.linkGender"/>注:请填入男或女</td>
    			</tr>
    			<tr>
    				<td>联系人电话：</td>
    				<td><input type="text" name="linkMan.linkPhone"/></td>
    				<td>联系人手机：</td>
    				<td><input type="text" name="linkMan.linkMobile"/></td>
    			</tr>
    		</table>
    		<br />
    		<input type="submit" class="button" value="查询" />
    	</form>
  </body>
</html>

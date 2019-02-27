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
    
    <title>My JSP 'visit.jsp' starting page</title>
    
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
    		当前位置：综合查询> 客户拜访记录查询
    	</div>
    	<br />
    	<div class="msg"><s:property value='msg'/></div>
    	
    	<form action="${pageContext.request.contextPath }/visit_findAllVisitByCondiction" method="post">
    		<table>
    			<tr>
    				<td>客户：</td>
    				<td><input type="text" name="visit.customer.custName"/></td>
    			</tr>
    			<tr>
    				<td>用户：</td>
    				<td><input type="text" name="visit.manager.username"/></td>
    			</tr>
    			<tr>
    				<td>拜访地址：</td>
    				<td><input type="text" name="visit.vaddress" /></td>
    				<td>拜访内容：</td>
    				<td><input type="text" name="visit.vcontent" /></td>
    			</tr>
    		</table>
    		<br />
    		<input type="submit" class="button" value="查询" />
    	</form>
  </body>
</html>

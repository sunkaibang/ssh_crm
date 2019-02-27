<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">
    
	    <title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/initial.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/listcustomer.css" />
        
    </head>
    <body>
    	<div>
    		当前位置：客户来源统计 > 客户列表
    	</div>
    	<br />
    	<div class="msg"></div>
    	
    	<table>
    		<tr>
    			<th>客户来源</th>
    			<th>数量</th>
    		</tr>
    		<c:forEach items="${request_customerList}" var="customer">
    			<tr>
    				<td>${customer.custSource }</td>
	    			<td>${customer.num }</td>
    			</tr>
    		</c:forEach>
    	</table>
 	</body>
</html>
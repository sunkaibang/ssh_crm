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
    
    <title>My JSP 'customer.jsp' starting page</title>
    
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
    	当前位置：综合查询 > 客户信息查询
    	</div>
    	<br />
    	<div class="msg"><s:property value='msg'/></div>
    	<form action="${pageContext.request.contextPath}/customer_findAllCustomer.action" method="post">
    		<table>
    			<tr>
    				<td>客户名称：</td>
    				<td><input type="text" name="customer.custName"  /></td>
    				<td>客户级别：</td>
    				<td><select name="customer.dataDictionary.did">
    						<s:iterator value="dataDictionaryList" var="dataDictionary">
    							<option value="<s:property value='#dataDictionary.did'/>">
    								<s:property value="#dataDictionary.dname"/>
    							</option>
    						</s:iterator>
    					</select>
    					是否选择级别：是:<input type="radio" name="isSelectLevel" value="yes" checked /> 
    					 否:<input type="radio" name="isSelectLevel" value="no" />
    				</td>
    			</tr>
    			<tr>
    				<td>信息来源：</td>
    				<td><input type="text" name="customer.custSource"  /></td>
    			</tr>
    			<tr>
    				<td>固定电话：</td>
    				<td><input type="text" name="customer.custPhone"  /></td>
    				<td>移动电话：</td>
    				<td><input type="text" name="customer.custMoble"  /></td>
    			</tr>
    		</table>
    		<br />
    		<input type="submit" class="button" value="查询" />
    	</form>
  </body>
</html>

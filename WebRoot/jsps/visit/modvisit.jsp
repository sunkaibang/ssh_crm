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
    
	    <title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/initial.css" />
    </head>
    <body>
    	<div>
    		当前位置：客户拜访管理 > 修改客户拜访
    	</div>
    	<br />
    	<div class="msg"><s:property value='msg'/></div>
    	
    	<form action="${pageContext.request.contextPath }/visit_modifyVisit" method="post">
    		<input type="hidden" name="visit.vid" value="<s:property value='visit.vid'/>">
    		<table>
    			<tr>
    				<td>客户：</td>
    				<td>
    					<select name='visit.customer.cid'>
    						<s:iterator value="customerList" var="customer">
    							<option value="<s:property value='#customer.cid'/>" <s:if test='#customer.cid == visit.customer.cid'>selected</s:if>>
    								<s:property value='#customer.custName'/>
    							</option>
    						</s:iterator>
    					</select>
    				</td>
    			</tr>
    			<tr>
    				<td>用户：</td>
    				<td>
    					<select name='visit.manager.id'>
    						<s:iterator value='managerList' var='manager'>
    							<option value='<s:property value='#manager.id'/>' <s:if test='#manager.id == visit.manager.id'>selected</s:if>>
    								<s:property value='#manager.username'/>
    							</option>
    						</s:iterator>
    					</select>
    				</td>
    			</tr>
    			<tr>
    				<td>拜访地址：</td>
    				<td><input type="text" name="visit.vaddress" value="<s:property value='visit.vaddress'/>"/></td>
    				<td>拜访内容：</td>
    				<td><input type="text" name="visit.vcontent" value="<s:property value='visit.vcontent'/>"/></td>
    			</tr>
    		</table>
    		<br />
    		<input type="submit" class="button" value="保存" />
    	</form>
 	</body>
</html>
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
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.messages_cn.js"></script>
		
		<script type="text/javascript">
			$(function() {
				$("#myForm").validate({
					rules: {
						"customer.custName":{required:true},
						"customer.custSource":{required:true},
						"customer.custPhone":{digits:true},
						"customer.custMoble":{digits:true}
					}
				});
			});
		</script>
    </head>
    <body>
    	<div>
    		当前位置：客户管理 > 添加客户
    	</div>
    	<br />
    	<div class="msg"><s:property value='msg'/></div>
    	<form id="myForm" action="${pageContext.request.contextPath}/customer_addCustomer.action" method="post">
    		<table>
    			<tr>
    				<td>客户名称：</td>
    				<td><input type="text" name="customer.custName" value="<s:property value='customer.custName'/>" /></td>
    				<td>客户级别：</td>
    				<td>
    					<select name="customer.dataDictionary.did">
    						<s:iterator value="dataDictionaryList" var="dataDictionary">
    							<option value="<s:property value='#dataDictionary.did'/>">
    								<s:property value="#dataDictionary.dname"/>
    							</option>
    						</s:iterator>
    					</select>
    				</td>
    			</tr>
    			<tr>
    				<td>信息来源：</td>
    				<td><input type="text" name="customer.custSource" value="<s:property value='customer.custSource'/>" /></td>
    			</tr>
    			<tr>
    				<td>固定电话：</td>
    				<td><input type="text" name="customer.custPhone" value="<s:property value='customer.custPhone'/>" /></td>
    				<td>移动电话：</td>
    				<td><input type="text" name="customer.custMoble" value="<s:property value='customer.custMoble'/>" /></td>
    			</tr>
    		</table>
    		<br />
    		<input type="submit" class="button" value="保存" />
    	</form>
 	</body>
</html>
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
    		当前位置：联系人管理 > 修改联系人
    	</div>
    	<br />
    	<div class="msg"><s:property value='msg'/></div>
    	<form action="${pageContext.request.contextPath}/linkman_modifyLinkMan.action" method="post">
    		<input type="hidden" name="linkMan.linkid" value="<s:property value='linkMan.linkid'/>">
    		<table>
    			<tr>
    				<td>所属客户：</td>
    				<td><select name="linkMan.customer.cid">
    						<s:iterator value="customerList" var="customer">
    							<option value="<s:property value='#customer.cid'/>" <s:if test='linkMan.customer.cid == #customer.cid'>selected</s:if> >
    								<s:property value='#customer.custName'/>
    							</option>
    						</s:iterator>
    					</select>
    				</td>
    			</tr>
    			<tr>
    				<td>联系人名称：</td>
    				<td><input type="text" name="linkMan.linkName" value="<s:property value='linkMan.linkName'/>" /></td>
    				<td>联系人性别:</td>
    				<td>男:<input type="radio" name="linkMan.linkGender" value="男"  <s:if test='linkMan.linkGender == "男"'>checked</s:if> /> 
    				       女:<input type="radio" value="女" name="linkMan.linkGender" <s:if test='linkMan.linkGender == "女"'>checked</s:if> />
    				 </td>
    			</tr>
    			<tr>
    				<td>联系人电话：</td>
    				<td><input type="text" name="linkMan.linkPhone" value="<s:property value='linkMan.linkPhone'/>" /></td>
    				<td>联系人手机：</td>
    				<td><input type="text" name="linkMan.linkMobile" value="<s:property value='linkMan.linkMobile'/>"/></td>
    			</tr>
    		</table>
    		<br />
    		<input type="submit" class="button" value="保存" />
    	</form>
 	</body>
</html>
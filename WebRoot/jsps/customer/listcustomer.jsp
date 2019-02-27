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
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/listcustomer.css" />
        
    </head>
    <body>
    	<div>
    		当前位置：客户管理 > 客户列表
    	</div>
    	<br />
    	<div class="msg"></div>
    	
    	<form action="${pageContext.request.contextPath}/customer_findAllCustomer.action?" method="post">
	    	客户名称:<input type="text" name="customer.custName" /> 
	    	<input type="submit"  value="筛选"/>
    	</form>
    	<table>
    		<tr>
    			<th>客户名称</th>
    			<th>客户级别</th>
    			<th>客户来源</th>
    			<th>手机号码</th>
    			<th>电话号码</th>
    			<th>操作</th>
    		</tr>
    		<s:iterator value="pageBean.List" var="customer">
    			<tr>
    				<td><s:property value='#customer.custName'/></td>
	    			<td><s:property value='#customer.dataDictionary.dname'/></td>
	    			<td><s:property value='#customer.custSource'/></td>
	    			<td><s:property value='#customer.custPhone'/></td>
	    			<td><s:property value='#customer.custMoble'/></td>
	    			<td>
	    				<a href="${pageContext.request.contextPath}/customer_moddifyCustomerFirst.action?customer.cid=<s:property value='#customer.cid'/>">修改</a>
	    				|
	    				<a href="${pageContext.request.contextPath}/customer_deleteCustomer.action?customer.cid=<s:property value='#customer.cid'/>">删除</a>
	    			</td>
    			</tr>
    		</s:iterator>
    	</table>
    	<br />
    	<br />
    	<br />
    	<div>
    		<s:if test="pageBean.currentPage != 1">
    			<a href="<s:property value='pageBean.url'/>pageBean.currentPage=1">首页</a> 
    		</s:if>
    		<s:if test="pageBean.currentPage > 1">
    			<a href="<s:property value='pageBean.url'/>pageBean.currentPage=<s:property value='pageBean.currentPage - 1'/>">上一页</a> 
    		</s:if>
    		
    		<s:if test="pageBean.totalPage <= 3">
    			<s:set var="begin" value="1"></s:set>
    			<s:set var="end" value="pageBean.totalPage"></s:set>
    		</s:if>
    		<s:else>
    			<s:set var="begin" value="pageBean.currentPage - 1"></s:set>
    			<s:set var="end" value="pageBean.currentPage + 1"></s:set>
    			<s:if test="#begin < 1">
    				<s:set var="begin" value="1"></s:set>
    				<s:set var="end" value="3"></s:set>
    			</s:if>
    			<s:if test="#end > pageBean.totalPage">
    				<s:set var="begin" value="pageBean.totalPage - 2"></s:set>
    				<s:set var="end" value="pageBean.totalPage"></s:set>
    			</s:if>
    		</s:else>
    		<s:iterator var="i" begin="begin" end="end">
    			<s:if test="pageBean.currentPage == #i">
    				<s:property value="i"/>
    			</s:if>
    			<s:else>
    				<a href="<s:property value='pageBean.url'/>pageBean.currentPage=<s:property value='i'/>"><s:property value="i"/></a>
    			</s:else>
    		</s:iterator>
    		
    		<s:if test="pageBean.currentPage < pageBean.totalPage">
    			<a href="<s:property value='pageBean.url'/>pageBean.currentPage=<s:property value='pageBean.currentPage + 1'/>">下一页</a>
    		</s:if>
			<s:if test="pageBean.currentPage != pageBean.totalPage">
				<a href="<s:property value='pageBean.url'/>pageBean.currentPage=<s:property value='pageBean.totalPage'/>">尾页</a>
			</s:if>
    		
    		<form action="<s:property value='pageBean.url'/>" method="post">
    			跳到<input class="pageInput" type="text" name="pageBean.currentPage"/>
    			<input type="submit" value="确定" />
    			当前页:<s:property value='pageBean.currentPage'/>/总页:<s:property value='pageBean.totalPage'/> <br />
    		</form>
    	</div>
 	</body>
</html>
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
        
        <!-- pageCount是有多少个角标，个pageBean中的pageCount(一页显示多少数据)是不一样的，midIndex是中间的页数是的几个角标 -->
        <s:set var='pageCount' value='3'></s:set>
        <s:set var='midIndex' value='2'></s:set>
    </head>
    <body>
    	<div>
    		当前位置：客户拜访管理 > 客户拜访列表
    	</div>
    	<br />
    	<div class="msg"></div>
    	
    	<table>
    		<tr>
    			<th>用户名称</th>
    			<th>客户名称</th>
    			<th>拜访地址</th>
    			<th>拜访内容</th>
    			<th>操作</th>
    		</tr>
    		<s:iterator value="pageBean.list" var="visit">
	    		<tr>
	    			<td><s:property value='#visit.customer.custName'/></td>
	    			<td><s:property value='#visit.manager.username'/></td>
	    			<td><s:property value='#visit.vaddress'/></td>
	    			<td><s:property value='#visit.vcontent'/></td>
	    			<td>
	    			<a href="${pageContext.request.contextPath}/visit_modifyVisitFirst?visit.vid=<s:property value='#visit.vid'/>">修改</a>
		    				|
		    		<a href="${pageContext.request.contextPath}/visit_deleteVisit?visit.vid=<s:property value='#visit.vid'/>">删除</a>
		    		</td>
	    		</tr>
    		</s:iterator>
    	</table>
    	<br />
    	<br />
    	<br />
    	<div>
    		<s:if test="pageBean.currentPage != 1">
    			<a href='<s:property value="pageBean.url"/>pageBean.currentPage=1'>首页 </a>
    		</s:if>
    		<s:if test="pageBean.currentPage > 1">
    			<a href='<s:property value="pageBean.url"/>pageBean.currentPage=<s:property value="pageBean.currentPage - 1"/>'>上一页  </a>
    		</s:if>
    		
    		<s:if test="pageBean.totalPage <= #pageCount">
    			<s:set var="begin" value="1"></s:set>
    			<s:set var="end" value="pageBean.totalPage"></s:set>
    		</s:if>
    		<s:else>
    			<s:set var='addNum' value='#pageCount - #midIndex'></s:set>
    			<s:set var='subNum' value='#pageCount - #addNum - 1'></s:set>
    			<s:set var="begin" value="pageBean.currentPage - #subNum"></s:set>
    			<s:set var="end" value="pageBean.currentPage + #addNum"></s:set>
    			<s:if test="#begin < 1">
    				<s:set var='begin' value='1'></s:set>
    				<s:set var='end' value='#pageCount'></s:set>
    			</s:if>
    			<s:if test="#end > pageBean.totalPage" >
    				<s:set var='begin' value='pageBean.totalPage - #pageCount + 1'></s:set>
    				<s:set var='end' value='pageBean.totalPage'></s:set>
    			</s:if>
    		</s:else>
    		<s:iterator var='i' begin='begin' end='end'>
    			<s:if test='pageBean.currentPage == #i'>
    				<s:property value='i'/>
    			</s:if>
    			<s:else>
    				<a href='<s:property value="pageBean.url"/>pageBean.currentPage=<s:property value="i"/>'><s:property value='i'/></a>
    			</s:else>
    		</s:iterator>
    		
    		<s:if test="pageBean.currentPage < pageBean.totalPage">
    			<a href='<s:property value="pageBean.url"/>pageBean.currentPage=<s:property value="pageBean.currentPage + 1"/>'>下一页  </a>
    		</s:if>
    		<s:if test="pageBean.currentPage != pageBean.totalPage">
    			<a href='<s:property value="pageBean.url"/>pageBean.currentPage=<s:property value="pageBean.totalPage"/>'>尾页 </a>
    		</s:if>
    		
    		<form action="<s:property value='pageBean.url'/>" method="post">
    			跳到<input class="pageInput" type="text" name="pageBean.currentPage"/>
    			<input type="submit" value="确定" />
    			当前页:<s:property value='pageBean.currentPage'/>/总页:<s:property value='pageBean.totalPage'/>  <br />
    		</form>
    	</div>
 	</body>
</html>
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
						"linkMan.linkName":{required:true},
						"linkMan.linkPhone":{digits:true},
						"linkMan.linkMobile":{digits:true}
					}
				});
			});
		</script>
    </head>
    <body>
    	<div>
    		当前位置：联系人管理 > 添加联系人
    	</div>
    	<br />
    	<div class="msg"><s:property value='msg'/></div>
    	<form id="myForm" action="${pageContext.request.contextPath}/linkman_addLinkMan.action" method="post" enctype="multipart/form-data">
    		<table>
    			<tr>
    				<td>所属客户：</td>
    				<td><select name="linkMan.customer.cid">
    						<s:iterator value="customerList" var="customer">
    							<option value="<s:property value='#customer.cid'/>"><s:property value='#customer.custName'/></option>
    						</s:iterator>
    					</select>
    				</td>
    			</tr>
    			<tr>
    				<td>联系人名称：</td>
    				<td><input type="text" name="linkMan.linkName"/></td>
    				<td>联系人性别:</td>
    				<td>男:<input type="radio" name="linkMan.linkGender" value="男" checked="checked"/> 女:<input type="radio" value="女" name="linkMan.linkGender"/></td>
    			</tr>
    			<tr>
    				<td>联系人电话：</td>
    				<td><input type="text" name="linkMan.linkPhone"/></td>
    				<td>联系人手机：</td>
    				<td><input type="text" name="linkMan.linkMobile"/></td>
    			</tr>
    			<tr>
					<td>上传文件：</td>
					<td><input type="file" name="upload" /></td>
    			</tr>
    		</table>
    		<br />
    		<input type="submit" class="button" value="保存" />
    	</form>
 	</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<base href="<%=basePath%>">
    
	    <title>客户关系管理系统</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/initial.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />

		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>

		<style>
			a {
				text-decoration: none;
				font-size: 16px;
			}
			
			a:hover {
				color: red;
			}
		</style>

		<script>
			$(function() {
				$("#accordion").accordion({
					heightStyle: "content",
					icons: {
						"header": "ui-icon-plus",
						"activeHeader": "ui-icon-minus"
					},
					animate: 250,
					collapsible: true
				});
			});
		</script>
	</head>

	<body>
		<div class="title_main">
			<div class="title_div">客户关系管理系统</div>
			<div class='fr'><a href='${pageContext.request.contextPath}/manager_exit'>退出</a></div>
			<div class='fr title_left_div'>当前用户:${sessionScope.session_manager.username }</div>
		</div>

		<div class="body">
			<!--导航栏-->
			<div id="accordion" class="menu fl">
				<h3>客户管理</h3>
				<div>
					<div>
						<a target="mainPage" href="${pageContext.request.contextPath}/dataDictionary_toAddCustomer">新增客户</a>
					</div>
					<div>
						<a target="mainPage" href="${pageContext.request.contextPath}/customer_findAllCustomer.action">客户列表</a>
					</div>
				</div>

				<h3>联系人管理</h3>
				<div>
					<div>
						<a target="mainPage" href="${pageContext.request.contextPath}/linkman_addLinkManPage.action">新增联系人</a>
					</div>
					<div>
						<a target="mainPage" href="${pageContext.request.contextPath}/linkman_findAllLinkMan.action">联系人列表</a>
					</div>
				</div>

				<h3>客户拜访管理</h3>
				<div>
					<div>
						<a target="mainPage" href="${pageContext.request.contextPath}/visit_addVisitPage.action">新增客户拜访</a>
					</div>
					<div>
						<a target="mainPage" href="${pageContext.request.contextPath}/visit_findAllVisit.action">客户拜访列表</a>
					</div>
				</div>

				<h3>综合查询</h3>
				<div>
					<div>
						<a target="mainPage" href="${pageContext.request.contextPath}/dataDictionary_tofindByCondtionPage">客户信息查询</a>
					</div>
					<div>
						<a target="mainPage" href="${pageContext.request.contextPath}/jsps/querycondition/linkman.jsp">联系人信息查询</a>
					</div>
					<div>
						<a target="mainPage" href="${pageContext.request.contextPath}/jsps/querycondition/visit.jsp">客户拜访记录查询</a>
					</div>
				</div>

				<h3>统计分析</h3>
				<div>
					<div>
						<a target="mainPage" href="${pageContext.request.contextPath}/customer_countByLevel">客户级别统计</a>
					</div>
					<div>
						<a target="mainPage" href="${pageContext.request.contextPath}/customer_countBySource">客户来源统计</a>
					</div>
				</div>
			</div>

			<!--右边主题部分-->
			<div class="mian fl">
				<iframe name="mainPage" src="${pageContext.request.contextPath}/dataDictionary_toAddCustomer" width="100%" height="100%"></iframe>
			</div>
		</div>
	</body>

</html>
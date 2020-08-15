<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<%--<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>
	<%--静态包含样式和地址--%>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<%--静态包含 管理菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
			</tr>
			<c:forEach items="${requestScope.allorders}" var="order">
				<tr>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<td><a href="#">查看详情</a></td>
					<c:choose>
						<c:when test="${order.status == 0}">
							<td><a href="#">点击发货</a></td>
						</c:when>
						<c:when test="${order.status == 1}">
							<td>已发货</td>
						</c:when>
						<c:otherwise>
							<td>已完成</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%--替换掉页脚商标--%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
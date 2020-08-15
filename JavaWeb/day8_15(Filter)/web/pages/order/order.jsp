<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<%--<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>
	<%--静态包含样式和地址--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<%--<div>--%>
				<%--<span>欢迎<span class="um_span">韩总</span>光临尚硅谷书城</span>--%>
				<%--<a href="../order/order.jsp">我的订单</a>--%>
				<%--<a href="../../index.jsp">注销</a>&nbsp;&nbsp;--%>
				<%--<a href="../../index.jsp">返回</a>--%>
			<%--</div>--%>
		<%--静态包含登录成功之后的菜单--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>		
			<c:forEach items="${requestScope.orders}" var="order">
				<tr>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<td>
						<c:choose>
							<c:when test="${order.status == 0}">
								未发货
							</c:when>
							<c:when test="${order.status == 1}">
								已发货
							</c:when>
							<c:otherwise>
								已完成
							</c:otherwise>
						</c:choose>
					</td>
					<td><a href="#">查看详情</a></td>
				</tr>
			</c:forEach>
		</table>
		
	
	</div>

	<%--替换掉页脚商标--%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
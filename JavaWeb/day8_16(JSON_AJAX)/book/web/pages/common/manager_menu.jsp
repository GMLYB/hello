<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <c:if test="${empty sessionScope.user}">
        <a href="pages/user/login.jsp">登录</a> |
        <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>管理员</span>
        <a href="manager/bookServlet?action=page">图书管理</a>
        <a href="orderServlet?action=showAllOrders">订单管理</a>
    </c:if>
    <a href="index.jsp">返回商城</a>
</div>
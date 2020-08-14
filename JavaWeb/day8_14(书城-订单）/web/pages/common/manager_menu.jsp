<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <c:if test="${empty sessionScope.user}">
        <a href="pages/user/login.jsp">登录</a> |
        <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        <a href="pages/cart/cart.jsp">购物车</a>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <a href="manager/bookServlet?action=page">图书管理</a>
        <a href="pages/manager/order_manager.jsp">订单管理</a>
        <a href="index.jsp">返回商城</a>
    </c:if>
</div>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%--<base href="http://localhost:8080/0730web/">--%>
    <%--<link type="text/css" rel="stylesheet" href="static/css/style.css">--%>
    <%--静态包含样式和地址--%>
    <%@include file="/pages/common/head.jsp"%>
    <script>
        $(function () {
            //修改商品数量
            $(".updateCount").change(function () {
                //获取商品名称
                var name = $(this).parent().parent().find("td:first").text();
                var id = $(this).attr('bookId');
                //获取商品数量
                var count = this.value;

                if ( confirm("你确定要将【" + name + "】商品修改数量为：" + count + " 吗?") ) {
                    // 发 起 请 求 。 给 服 务 器 保 存 修 改
                    location.href = "http://localhost:8080/0730web/cartServlet?action=updateCount&count="+count+"&id="+id;
                } else {
                    // defaultValue 属性是表单项 Dom 对象的属性。它表示默认的 value 属性值。
                    this.value = this.defaultValue;
                }
            });

            //清空购物车
            $("#clearCart").click(function () {
                return confirm("是否清空购物车？");
            });
            //删除单个商品项
            $("a.deleteItem").click(function () {
                return confirm("你确定要删除["+$(this).parent().parent().find("td:first").text()+"]吗？");
            });
        });
    </script>
</head>
<body>

    <div id="header">
        <img class="logo_img" alt="" src="static/img/logo.gif">
        <span class="wel_word">购物车</span>
        <%--<div>--%>
            <%--<span>欢迎<span class="um_span">韩总</span>光临尚硅谷书城</span>--%>
            <%--<a href="pages/order/order.jsp">我的订单</a>--%>
            <%--<a href="index.jsp">注销</a>&nbsp;&nbsp;--%>
            <%--<a href="index.jsp">返回</a>--%>
        <%--</div>--%>
        <%--静态包含登录成功之后的菜单--%>
        <%@include file="/pages/common/login_success_menu.jsp"%>
    </div>

    <div id="main">

        <table>
            <tr>
                <td>商品名称</td>
                <td>数量</td>
                <td>单价</td>
                <td>金额</td>
                <td>操作</td>
            </tr>


            <c:if test="${empty sessionScope.cart.items}">
                <%--购物车为空--%>
                <tr>
                    <td colspan="5"><a href="index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！</a></td>
                </tr>
            </c:if>
            <%--购物车非空，循环输出购物车信息--%>
            <c:if test="${not empty sessionScope.cart.items}">
                <c:forEach items="${sessionScope.cart.items}" var="entry">
                    <tr>
                        <td>${entry.value.name}</td>
                        <td>
                                <input class="updateCount" style="width: 80px;"
                                       bookid="${entry.value.id}"
                                       type="text" value="${entry.value.count}">
                        </td>
                        <td>${entry.value.price}</td>
                        <td>${entry.value.totalPrice}</td>
                        <td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
                    </tr>
                </c:forEach>
            </c:if>

        </table>

        <%--购物车非空才输出如下信息--%>
        <c:if test="${not empty sessionScope.cart.items}">
            <div class="cart_info">
                <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
                <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
                <span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
                <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
            </div>
        </c:if>

    </div>
    <%--替换掉页脚商标--%>
    <%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
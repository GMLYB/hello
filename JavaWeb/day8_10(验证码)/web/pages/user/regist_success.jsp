<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <!--base标签永远固定相对路径跳转的结果-->
    <%--<base href="http://localhost:8080/0730web/">--%>
    <%--<link type="text/css" rel="stylesheet" href="static/css/style.css">--%>
    <%--静态包含样式和地址--%>
    <%@include file="/pages/common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }
    </style>
</head>
<body>
    <div id="header">
        <img class="logo_img" alt="" src="static/img/logo.gif">
        <span class="wel_word"></span>
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

        <h1>注册成功! <a href="index.jsp">转到主页</a></h1>

    </div>

    <%--替换掉页脚商标--%>
    <%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
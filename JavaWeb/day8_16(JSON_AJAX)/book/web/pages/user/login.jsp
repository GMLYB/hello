<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员登录页面</title>
    <!--base标签永远固定相对路径跳转的结果-->
    <%--<base href="http://localhost:8080/0730web/">--%>
    <%--<!--<link type="text/css" rel="stylesheet" href="../../static/css/style.css">-->--%>
    <%--<link type="text/css" rel="stylesheet" href="static/css/style.css">--%>
    <%--静态包含样式和地址--%>
    <%@include file="/pages/common/head.jsp"%>
</head>
<body>
    <div id="login_header">
        <!--<img class="logo_img" alt="" src="../../static/img/logo.gif">-->
        <img class="logo_img" alt="" src="static/img/logo.gif">
    </div>

    <div class="login_banner">

        <div id="l_content">
            <span class="login_word">欢迎登录</span>
        </div>

        <div id="content">
            <div class="login_form">
                <div class="login_box">
                    <div class="tit">
                        <h1>尚硅谷会员</h1>
                        <a href="pages/user/regist.jsp">立即注册</a>
                    </div>
                    <div class="msg_cont">
                        <b></b>
                        <%--<span class="errorMsg">${errorMsg == null ? "请输入用户名和密码" : errorMsg}</span>--%>
                        <span class="errorMsg">${empty requestScope.errorMsg ? "请输入用户名和密码" : requestScope.errorMsg }</span>
                    </div>
                    <div class="form">
                        <form action="userServlet" method="post">
                        <%--<form action="loginServlet" method="post">--%>
                            <input type="hidden" name="action" value="login">
                            <label>用户名称：</label>
                            <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                                   value="${requestScope.username}" name="username"/>
                            <br/>
                            <br/>
                            <label>用户密码：</label>
                            <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                                   name="password"/>
                            <br/>
                            <br/>
                            <input type="submit" value="登录" id="sub_btn"/>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <%--替换掉页脚商标--%>
    <%@ include file="/pages/common/footer.jsp" %>

</body>
</html>
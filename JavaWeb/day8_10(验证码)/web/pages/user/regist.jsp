<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%--<!--base标签永远固定相对路径跳转的结果-->--%>
    <%--<base href="http://localhost:8080/0730web/">--%>

    <%--<link type="text/css" rel="stylesheet" href="static/css/style.css">--%>
    <%--<script src="static/script/jquery-3.2.1.js"></script>--%>
    <%--静态包含样式和地址--%>
    <%@include file="/pages/common/head.jsp"%>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }
    </style>
    <script>
        $(function () {
            $("#code_img").click(function () {
                this.src = "${basePath}kaptcha.jpg?d=" + new Date();
            });

            $("#sub_btn").click(function () {
                var patt = /^\w{5,12}$/;
                var email_patt = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;

                // 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                var isName = patt.test($("#username").val());
                if (!isName) {
                    $("span.errorMsg").text("用户名不合法！");
                    return false;
                }
                // 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                var isPass = patt.test($("#password").val());
                if (!isPass) {
                    $("span.errorMsg").text("密码不合法！");
                    return false;
                }
                // 验证确认密码：和密码相同
                var pass1 = $("#password").val();
                var pass2 = $("#repwd").val();
                if (pass1 != pass2) {
                    $("span.errorMsg").text("两次密码不一致!");
                    return false;
                }
                // 邮箱验证：xxxxx@xxx.com
                var email = $("#email").val();
                if (!email_patt.test(email)) {
                    $("span.errorMsg").text("邮箱格式错误!");
                    return false;
                }
                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
                var codeText = $("#code").val();
                // 去掉验证码前后空格
                codeText = $.trim(codeText);
                if (codeText == null || codeText == "") {
                    $("span.errorMsg").text("验证码不能为空!");
                    return false;
                }

                $("span.errorMsg").text("");


            });

        })
    </script>

</head>

<body>
    <div id="login_header">
        <img class="logo_img" alt="" src="static/img/logo.gif">
    </div>

    <div class="login_banner">

        <div id="l_content">
            <span class="login_word">欢迎注册</span>
        </div>

        <div id="content">
            <div class="login_form">
                <div class="login_box">
                    <div class="tit">
                        <h1>注册尚硅谷会员</h1>
                        <span class="errorMsg">${requestScope.errorMsg}</span>
                    </div>
                    <div class="form">
                        <%--<form action="registerServlet" method="post">--%>
                        <form action="userServlet" method="post">
                            <input type="hidden" name="action" value="regist">
                        <!--<form action="regist_success.jsp">-->
                            <label>用户名称：</label>
                            <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                                   name="username" id="username" value="${requestScope.username}"/>
                            <br/>
                            <br/>
                            <label>用户密码：</label>
                            <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                                   name="password" id="password"/>
                            <br/>
                            <br/>
                            <label>确认密码：</label>
                            <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                                   name="repwd" id="repwd"/>
                            <br/>
                            <br/>
                            <label>电子邮件：</label>
                            <input class="itxt" type="email" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                                   name="email" id="email" value="${requestScope.email}"/>
                            <br/>
                            <br/>
                            <label>验证码：</label>
                            <input class="itxt" type="text" name="code" style="width: 80px;" id="code"/>
                            <img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 50px; width: 150px">
                            <br/>
                            <br/>
                            <input type="submit" value="注册" id="sub_btn"/>

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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <base href="http://localhost:8080/0809web/">
</head>
<body>
    <form action="loginSerlvet" method="get">
        用户名：<input type="text" name="username" value="${cookie.username.value}"> <br>
        密码：<input type="password" name="password" value=""> <br>
        <input type="submit">
    </form>
</body>
</html>

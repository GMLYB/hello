<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>

</head>
<body>
    <h1>这是主页</h1>
    <a href="${pageContext.request.contextPath}/user/t1">/user/t1</a>
    <form action="${pageContext.request.contextPath}/user/t4" method="post">
        <input type="text" name="name">名字<br><br>
        <input type="submit">

    </form>
    <br><br>
    <form action="${pageContext.request.contextPath}/user/t4" method="get">
        <input type="text" name="name">名字<br><br>
        <input type="submit">

    </form>
</body>
</html>

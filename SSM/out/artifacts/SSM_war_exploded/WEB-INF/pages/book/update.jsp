<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书操作界面</title>
    <style>

        a{
            text-decoration: none;
            color: #000;
        }
        a:hover{
            color: red;
        }

        .nav{
            width: 1000px;
            margin: 50px auto;
        }
        .inp{
            width: 400px;
            margin-top: 10px;

        }


    </style>
</head>
<body>

    <div class="nav">


        <form action="${pageContext.request.contextPath}/book/${empty book ? "add":"update"}" method="post">
            <c:if test="${not empty book}">
                <input type="hidden" name="id" value="${book.id}">
            </c:if>
        <div style="text-align: center">书名：<input class="inp" type="text" name="name" value="${book.name}"> </div>
        <div style="text-align: center">作者：<input class="inp" type="text" name="author" value="${book.author}"> </div>
        <div style="text-align: center">价钱：<input class="inp" type="number" name="price" value="${book.price}"></div>
            <input style="margin-top: 20px;width: 100px;margin-left: 300px" type="submit" value="提交">
            <a href="${pageContext.request.contextPath}/book/all" style="background-color: silver;padding: 3px;margin-left: 280px">返回</a>
        </form>
    </div>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书展示界面</title>
    <%--http://localhost:8080/ssm/script/jquery-3.2.1.js--%>
    <script src="${pageContext.request.contextPath}/statics/script/jquery-3.2.1.js"></script>
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
        .btitle{
            margin: 0px auto;
            text-align: center;
            font-size: 18px;
            font-weight: 700;
        }

        .books{
            margin-top: 10px;
            height: 260px;
            width: 100%;
            text-align: center;
            border-collapse: collapse;
            font-size: 14px;
        }

        .books td,
        .books th{
            border:1px solid blue;
        }

    </style>

    <script type="text/javascript">
        $(function () {
            $("a[id='dbook']").click(function () {
               return confirm("确定删除["+$(this).parent().parent().find("td:eq(1)").text()+"]吗");
            });
        });
    </script>
</head>
<body>

    <div class="nav">
        <div><a href="${pageContext.request.contextPath}/book/add">添加图书</a></div>
        <div class="btitle">图书列表</div>
        <table class="books">
            <tr>
                <th>图书id</th>
                <th>图书名称</th>
                <th>作者</th>
                <th>价钱</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.price}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/book/update?id=${book.id}">修改</a>&nbsp;&nbsp;
                        <a id="dbook" href="${pageContext.request.contextPath}/book/delete?id=${book.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>

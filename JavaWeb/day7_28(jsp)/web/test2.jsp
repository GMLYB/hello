<%@ page import="java.util.List" %>
<%@ page import="pojo.Student" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020-07-28
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息</title>
    <style>
        table {
            margin: 100px auto;
            text-align: center;
            border-collapse: collapse;
        }
        table td,
        table th {
            padding: 0 20px;
            border: 1px solid red;
        }
        table td a {
            text-decoration: none;
            outline-style: none;
            color: #000;
        }

        a:hover {
            color: red;
        }
    </style>
</head>
<body>
    <%--jsp输出一个表格，里面包含10个学生信息--%>
    <%
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int t = i+1;
            studentList.add(new Student(t,"name"+t,18+t,"phone"+t));
        }
    %>

    <table>
        <tr>
            <th>ID</th>
            <th>学生姓名</th>
            <th>年龄</th>
            <th>手机号</th>
            <th>操作</th>
        </tr>
        <%for (Student student : studentList){ %>
        <tr>
            <td><%= student.getId()%></td>
            <td><%= student.getName()%></td>
            <td><%= student.getAge()%></td>
            <td><%= student.getPhone()%></td>
            <td><a href="#">删除</a> <a href="#">修改</a></td>
        </tr>
        <% } %>

    </table>





</body>
</html>

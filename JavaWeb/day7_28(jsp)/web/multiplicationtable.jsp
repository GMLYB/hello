<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020-07-28
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>九九乘法表</title>
    <style>
        #ninenine {
            border-collapse: collapse;
            margin: 100px auto;
            text-align: center;
        }
        .row td {
            /*border: 1px solid red;*/
            padding: 10px;

        }
    </style>

</head>
<body>
    <table id="ninenine">
        <%for (int i = 1;i <= 9;i++){ %>
        <tr class="row">
            <% for (int j = 1; j <= i; j++) {%>
                <td><%=j + "x"+ i + "=" + i*j %></td>
            <%}%>
        </tr>
        <%}%>
    </table>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020-07-29
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL表达式</title>
</head>
<body>
    <%
        request.setAttribute("username","root");
    %>

    <div>表达式脚本的输出值为:<%= request.getAttribute("username")%></div>
    <div>EL表达式输出的值为:${username}</div>
    <h4>为空的情况</h4>
    <div>表达式脚本的输出值为:<%= request.getAttribute("username1")%></div>
    <div>EL表达式输出的值为:${username1}</div>
    <h4>为空的情况，两者效果相同</h4>
    <div>表达式脚本的输出值为:<%= request.getAttribute("username1")==null?"":request.getAttribute("username")%></div>
    <div>EL表达式输出的值为:${username1}</div>

</body>
</html>

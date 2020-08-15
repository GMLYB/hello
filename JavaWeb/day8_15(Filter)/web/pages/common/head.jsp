<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--base标签永远固定相对路径跳转的结果-->

<%
    String basePath = request.getScheme()
        + "://"
        + request.getServerName()
        + ":"
        + request.getServerPort()
        + request.getContextPath()
        + "/";
%>

<%--<base href="http://localhost:8080/0730web/">--%>
<base href="<%= basePath %>">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script src="static/script/jquery-3.2.1.js"></script>

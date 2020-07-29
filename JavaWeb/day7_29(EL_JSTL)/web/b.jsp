<%@ page import="pojo.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020-07-29
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>输出复杂的Bean对象</title>
</head>
<body>
    <%
        Person person = new Person();
        person.setName("爷将主宰你的世界");
        person.setPhones(new String[]{"10001", "10086", "6666666"});
        List<String> cities = new ArrayList<>();
        cities.add("北京");
        cities.add("上海");
        cities.add("杭州");
        person.setCities(cities);
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");

        person.setMap(map);

        pageContext.setAttribute("person", person);
    //        request.setAttribute("person",person);
    //        session.setAttribute("person",person);
    //        application.setAttribute("person",person);
    %>
    <div>输出person</div>
    <div>${ person }</div>
    <div>输出person name</div>
    <div>${person.name}</div>
    <div>输出person phone</div>
    <div>${person.phones[0]}</div>
    <div>输出person cities</div>
    <div>${person.cities}</div>
    <div>输出person cities中某个元素</div>
    <div>${person.cities[0]}</div>
    <div>输出person map</div>
    <div>${person.map.get("key2")}</div>


</body>
</html>

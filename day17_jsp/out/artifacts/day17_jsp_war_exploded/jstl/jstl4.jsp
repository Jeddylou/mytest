<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="cn.itcast.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: 11527
  Date: 2020/6/11
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    List list = new ArrayList();
    list.add(new User("zhangsan", 23, new Date()));
    list.add(new User("lisi", 24, new Date()));
    list.add(new User("wangwu", 25, new Date()));
    request.setAttribute("list", list);
%>


<table border="1" width="500" align="center">
    <tr>
        <td>编号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>生日</td>
    </tr>
    <c:forEach items="${list}" var="user" varStatus="s">

        <c:if test="${s.count % 2 == 0}">
        <tr bgcolor="red">
            <td>${s.count}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.birStr}</td>
        </tr>
        </c:if>

        <c:if test="${s.count % 2 != 0}">
            <tr bgcolor="green">
                <td>${s.count}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.birStr}</td>
            </tr>
        </c:if>
    </c:forEach>
</table>

</body>
</html>

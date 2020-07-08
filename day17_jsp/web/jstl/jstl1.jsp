<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
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

<c:if test="true">
    我是真....
</c:if>


<%
    List list = new ArrayList();
    list.add("aaaa");
    request.setAttribute("list", list);
%>

<c:if test="${not empty list}">
    我是真....
</c:if>
</body>
</html>

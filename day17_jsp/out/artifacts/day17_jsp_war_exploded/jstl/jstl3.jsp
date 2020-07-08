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

<c:forEach begin="2" end="10" var="i" step="3" varStatus="s">
    ${i} ${s.index} ${s.count}<br>
</c:forEach>



<%
    List list = new ArrayList();
    list.add("aa");
    list.add("bhb");
    list.add("cc");

    request.setAttribute("list", list);
%>

<c:forEach items="${list}" var="str" varStatus="s">

    ${s.index} ${s.count} ${str} <br>
</c:forEach>


</body>
</html>

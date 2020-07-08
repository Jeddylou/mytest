<%@ page import="cn.itcast.domain.User" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: 11527
  Date: 2020/6/10
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
User user = new User();
user.setName("zhangsan");
user.setAge(23);
user.setBirthday(new Date());

request.setAttribute("u", user);
%>

${requestScope.u}

${requestScope.u.name}<br>
${requestScope.u.age}<br>
${requestScope.u.birthday.year}<br>
${requestScope.u.birthday.month}<br>
${requestScope.u.birthday.day}<br>
${requestScope.u.birStr}<br>
</body>
</html>

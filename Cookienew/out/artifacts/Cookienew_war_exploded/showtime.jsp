<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: 11527
  Date: 2020/6/10
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>itcast</title>
</head>
<body>

    <%

        Cookie [] cookies = request.getCookies();
        boolean flag = false;
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie : cookies){
                String name = cookie.getName();
                if("lastTime".equals(name)){
                    flag = true;



                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String str_date = sdf.format(date);

                    str_date = URLEncoder.encode(str_date, "utf-8");

                    cookie.setValue(str_date);
                    cookie.setMaxAge(60*60*24*30);
                    response.addCookie(cookie);

                    String value = cookie.getValue();
                    value = URLDecoder.decode(value, "utf-8");
                    out.write("<h1>欢迎回2来:" + value + "</h1>");


                    break;
                }
            }
        }


        if(cookies == null || cookies.length == 0 || flag == false){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String str_date = sdf.format(date);
            str_date = URLEncoder.encode(str_date, "utf-8");
            Cookie cookie = new Cookie("lastTime", str_date);
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);

            %>
            <h1>欢迎首次访问</h1>
    <%
        }
    %>



</body>
</html>

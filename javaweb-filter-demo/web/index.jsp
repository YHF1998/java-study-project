<%--
  Created by IntelliJ IDEA.
  User: 71432
  Date: 2022/9/1
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>在线人数 ： <span><%= request.getServletContext().getAttribute("OnlineCount") %></span></h1>
</body>
</html>

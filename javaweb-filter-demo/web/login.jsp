<%--
  Created by IntelliJ IDEA.
  User: 71432
  Date: 2022/9/4
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet/login" method="post">
    名字：<input type="text" name="username">
    密码：<input type="password" name="password">
    <input type="submit" value="提交">
</form>
</body>
</html>

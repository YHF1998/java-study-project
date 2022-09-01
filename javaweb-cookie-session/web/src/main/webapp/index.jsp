<%@ page contentType="text/html; charset=utf-8" %>
<%@ page errorPage="/error/500.jsp" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>时间22</h1>
<%= new java.util.Date() %>


<%
    int x = 10 / 0;
    out.println(x);

%>

<%
    for (int i = 0; i < 10; i++) {
        out.println("777");
        out.print(222);
%>
<h2>hello</h2>
<%
    }
%>
</body>
</html>
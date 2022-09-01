<%--
  Created by IntelliJ IDEA.
  User: 71432
  Date: 2022/8/30
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--显式声明这是一个错误页面--%>
<%--<%@ page isErrorPage="true" %>--%>


<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>455645</h1>
<%--使用javaBean--%>
<jsp:useBean id="people" class="com.getdream.www.pojo.People" scope="page"/>

<%--设置javaBean内容--%>
<jsp:setProperty name="people" property="id" value="11"/>
<jsp:setProperty name="people" property="name" value="李明"/>


<%--获取javaBean内容--%>
ID:
<jsp:getProperty name="people" property="id"/>

Name:
<jsp:getProperty name="people" property="name"/>

</body>
</html>

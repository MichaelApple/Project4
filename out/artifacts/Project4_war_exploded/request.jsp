<%--
  Created by IntelliJ IDEA.
  User: Miha
  Date: 09.09.2017
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request Page</title>
</head>
<body>
<jsp:useBean id="userRequest" class="models.entity.Request" scope="application"/>

    <h1>${requestScope.time}</h1>
    <h1>${requestScope.now}</h1>

    <h1>${userRequest.toString()}</h1>
</body>
</html>

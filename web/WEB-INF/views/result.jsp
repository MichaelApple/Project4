<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<body>
<jsp:useBean id="userRequest" class="model.entities.Request" scope="application"/>
<div class="container">
    <div class="jumbotron" align="center">
        <div class="alert alert-success" role="alert">
            <div class="container">
                <h3>Your request:</h3>
                <p>WorkKind: <strong>${userRequest.workKind}</strong></p>
                <p>WorkScale: <strong>${userRequest.workScale}</strong></p>
                <p>Desired Date: <strong>${userRequest.desiredDateTime}</strong></p>
            </div>
            <c:choose>
                <c:when test="${sessionScope.user.role == 'USER'}">
                        <a class="nav-link" href="./pages/personal"><fmt:message key="navbar.personal.cabinet"/></a>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${sessionScope.user.role == 'ADMIN'}">
                        <a class="nav-link" href="./pages/admin"><fmt:message key="navbar.admin.cabinet"/></a>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<body>
    <jsp:useBean id="user" class="models.entity.User" scope="application"/>

    <h1>${requestScope.get("userExist")}</h1>

    <div class="container">
        <h1 align="center"><fmt:message key="personal.hello"/> ${user.userName}</h1>
        <p><fmt:message key="personal.info"/></p>
        <a href="index.jsp">Create new Request</a>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <div class="container myContainer">
                    <h2 align="center"><fmt:message key="index.hello"/> ${user.userName}</h2>
                    <h4>Your
                        <c:choose>
                            <c:when test="${empty param.newEmail}">
                                email: ${sessionScope.user.email}
                            </c:when>
                            <c:otherwise>
                                <b>new email: ${param.newEmail}</b>
                            </c:otherwise>
                        </c:choose>
                    </h4>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="container myContainer">
                    <h1><fmt:message key="personal.change.email"/></h1>
                    <form action="ChangeEmail" method="post">
                        <div class="form-group">
                            <label for="newEmail"><fmt:message key="personal.change.newemail"/></label>
                            <input type="email" class="form-control" name="newEmail" id="newEmail">
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary" value="Change Data">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>

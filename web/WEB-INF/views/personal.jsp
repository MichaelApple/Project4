<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<body>
    <jsp:useBean id="user" class="model.entities.User" scope="application"/>
    <jsp:useBean id="workPlan" class="model.entities.WorkPlan" scope="application"/>

    <h1>${requestScope.get("userExist")}</h1>

    <div class="container">
        <h1 align="center"><fmt:message key="personal.hello"/> ${user.userName}</h1>
        <p><fmt:message key="personal.info"/></p>
        <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="personal.request"/></a>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <div class="container myContainer">
                    <h3 align="center"><fmt:message key="index.hello"/> ${user.userName}</h3>
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
                    <h3><fmt:message key="personal.change.email"/></h3>
                    <form action="./pages/changeEmail" method="post">
                        <div class="form-group">
                            <label for="newEmail"><fmt:message key="personal.change.newemail"/></label>
                            <input type="email" class="form-control" name="newEmail" id="newEmail">
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="personal.change.submit"/>">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="container myContainer">
        <div class="row">
            <div class="col-sm-4">
                <h3>Your requests: </h3>
            </div>
            <div class="col-sm-8">
                <p>${workPlan.brigadeId}</p>
            </div>
        </div>
    </div>
</body>
</html>

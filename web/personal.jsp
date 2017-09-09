<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<body>
    <jsp:useBean id="user" class="models.entity.User" scope="application"/>

    <h1>${requestScope.get("userExist")}</h1>

    <div class="container">
        <h1 align="center">Hello, ${user.userName}</h1>
        <p>It is yor personal cabinet. You can change your data or create new request.</p>
        <a href="index.jsp">Create new Request</a>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <div class="container myContainer">
                    <h2 align="center">Hello, ${user.userName}</h2>
                    <h4>Your
                        <c:choose>
                            <c:when test="${empty param.newEmail}">
                                email: ${user.email}
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
                    <h1>Change your email</h1>
                    <form action="ChangeEmail" method="post">
                        <div class="form-group">
                            <label for="newEmail">Enter your new Email:</label>
                            <input type="email" class="form-control" name="newEmail" id="newEmail">
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary" value="Change Password">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>

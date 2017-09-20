<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<body>
<jsp:useBean id="userRequest" class="model.entities.Request" scope="application"/>

    <%--<h1>${requestScope.time}</h1>--%>
    <%--<h1>${requestScope.now}</h1>--%>

    <div class="row">
        <div class="col-sm-12">
            <div class="jumbotron" align="center">
                <div class="alert alert-success" role="alert">
                    <div class="container">
                        <p>${userRequest.workKind}</p>
                        <p>${userRequest.workScale}</p>
                        <p>${userRequest.desiredDateTime}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>

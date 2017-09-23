<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error Page</title>
</head>

<!-- Stylesheets -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">


<%-- Scripts --%>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/moment.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/myScripts.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-datetime.js"></script>
<body>
    <div class="container">
        <div class="jumbotron" align="center">
            <div class="alert alert-danger" role="alert">
                <h3><strong>404. Such page doesn't exist</strong></h3>
            </div>
            <h3><a href="${pageContext.request.contextPath}/index.jsp">Back to main menu</a></h3>
        </div>
    </div>
</body>
</html>

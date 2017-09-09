<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Project4</title>
</head>

<!-- Stylesheets -->

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/main.css">


<%-- Scripts --%>
<script src="js/jquery.js"></script>
<script src="js/moment.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/myScripts.js"></script>
<script src="js/bootstrap-datetime.js"></script>


<body>
<nav class="navbar navbar-toggleable-md navbar-dark bg-faded">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="#">Navbar</a>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Features</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Pricing</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Change language
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">

                    <a class="dropdown-item" href="?language=en">English</a>
                    <a class="dropdown-item" href="?language=ua">Українська</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.login"/><span class="caret"></span></a>
                <ul id="login-dp" class="dropdown-menu">
                    <li>
                        <div class="row">
                            <div class="col-md-12">
                                <form class="form" role="form" method="post" action="LoginServlet" accept-charset="UTF-8" id="login-nav1">
                                    <div class="form-group">
                                        <label class="sr-only" for="exampleInputEmail2">Email address</label>
                                        <input type="email" name="email" class="form-control" id="exampleInputEmail2" placeholder="Email address" required>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="exampleInputPassword2">Password</label>
                                        <input type="password" name="password" class="form-control" id="exampleInputPassword2" placeholder="Password" required>
                                        <div class="help-block text-right"><a href="">Forget the password ?</a></div>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                                    </div>
                                </form>
                            </div>
                            <div class="bottom text-center">
                                <a href="Logout">Logout</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </li>
            <li class="nav-item" >
                <a class="nav-link" data-toggle="modal" data-target="#largeShoes" href="#"><fmt:message key="navbar.register"/></a>
            </li>
        </ul>
    </div>
</nav>



<!-- The modal Registration Form-->
<div class="modal fade" id="largeShoes" tabindex="-1" role="dialog" aria-labelledby="modalLabelLarge" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="modalLabelLarge">Registration From</h4>
            </div>

            <div class="modal-body">
                <form class="form-horizontal" method="POST" action="Register">
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-6">
                            <h2><fmt:message key="enter.data"/></h2>
                            <hr>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="name" class="col-sm-3 col-form-label"><fmt:message key="input.userName" /></label>
                        <div class="col-sm-6">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-user"></i></div>
                                <input type="text" name="name" class="form-control" id="name"
                                       placeholder="<fmt:message key="label.userName" />" required autofocus>
                            </div>
                        </div>
                        <div class="offset-3"></div>
                    </div>
                    <div class="form-group row">
                        <label for="name" class="col-sm-3 col-form-label"><fmt:message key="input.email" /></label>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                    <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-at"></i></div>
                                    <input type="text" name="email" class="form-control" id="email"
                                           placeholder="you@example.com" required autofocus>
                                </div>
                            </div>
                        </div>
                        <div class="offset-3"></div>
                    </div>
                    <div class="form-group row">
                        <label for="name" class="col-sm-3 col-form-label"><fmt:message key="input.password" /></label>
                        <div class="col-sm-6">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-key"></i></div>
                                <input type="password" name="password" class="form-control" id="password"
                                       placeholder="<fmt:message key="label.password" />" required>
                            </div>
                        </div>
                        <div class="offset-3"></div>
                    </div>
                    <div class="form-group row">
                        <div class="offset-sm-4 col-sm-6 offset-sm-2">
                            <button type="submit" class="btn btn-primary"><fmt:message key="button.senddata"/></button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<%-- Ending of modal --%>


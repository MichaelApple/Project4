<%@ page import="models.enums.WorkKind" %>
<%@ page import="models.enums.WorkScale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<body>

  <jsp:useBean id="user" class="models.entity.User" scope="application"/>
  <c:set var="workKindList" value="<%=WorkKind.values()%>" />
  <c:set var="workScaleList" value="<%=WorkScale.values()%>"/>

  <div class="container">
      <h1>${requestScope.get("error")}</h1>
      <h1>${requestScope.get("userExist")}</h1>

      <h2 align="center">Hello, ${user.userName}</h2>

  </div>
  <div class="container myContainer" style="width: 50%">
      <h1 align="center">New Request</h1>
        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <h3>You must login first to send a request</h3>
            </c:when>
            <c:otherwise>
              <form action="NewRequest" method="post">
                  <div class="form-group">
                      <label for="workKind">Select Kind of Work:</label>
                      <select class="form-control" id="workKind" name="workKind">
                          <c:forEach items="${workKindList}" var="workKind">
                              <option>${workKind.toString()}</option>
                          </c:forEach>
                      </select>
                  </div>
                  <div class="form-group">
                      <label for="workScale">Select Scale of Work:</label>
                      <select class="form-control" id="workScale" name="workScale">
                          <c:forEach items="${workScaleList}" var="workScale">
                              <option>${workScale.toString()}</option>
                          </c:forEach>
                      </select>
                  </div>
                  <div class="form-group">
                      <label for="workScale">Select Convenient Time:</label>
                      <div class='input-group date' id='datetimepicker8'>
                          <input type='text' class="form-control" name="desiredDateTime"/>
                          <span class="input-group-addon">
                    <span class="fa fa-calendar">
                    </span>
                    </span>
                      </div>
                  </div>
                  <div class="form-group">
                      <input type="submit" class="btn btn-primary" value="Send Request">
                  </div>
              </form>
            </c:otherwise>
        </c:choose>
  </div>


  </body>
</html>

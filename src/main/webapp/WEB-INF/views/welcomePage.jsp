<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Welcome</title>
  <link rel="stylesheet" href="../../theme/css/style.css">
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
  <%@ include file="fragments/sideMenu.jsp" %>
  <td class="mainContent">
    <%--CONTENT-START--%>
    <h2>Witaj <span class="dashCol">${registeredUser}</span> na platformie Moto Route!</h2>
    <p><a href='<c:url value="/login"/>'>Zaloguj się</a>, by w pełni zacząć korzystać z serwisu.</p>
    <%--CONTENT-START--%>
  </td>
</table>

<%@ include file="fragments/footer.jsp" %>
</body>
</html>
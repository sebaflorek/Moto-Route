<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Forgot Password</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>Zapomniałeś hasła?</h2>
            <p>Podaj adres email, na który zarejestrowałeś konto, a my wyślemy link do zresetowania hasła.</p>
        <form:form method="post" modelAttribute="userForgotPassDto">
            <label>
                <form:input path="email" placeholder="example@example.pl"/><br>
                <form:errors path="email" cssClass="errorMsg"/>
            </label><br>
            <label><input type="submit" value="Wyślij"></label>
        </form:form>
        <%--CONTENT-STOP--%>
    </td>
</table>


<%@ include file="fragments/footer.jsp" %>
</body>
</html>
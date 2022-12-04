<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Change password</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>Zmień hasło</h2>
        <form:form method="post" modelAttribute="userPassDto">
            <label>
                Podaj stare hasło:<br>
                <form:password path="oldPassword" placeholder="password"/><br>
                <form:errors path="oldPassword" cssClass="errorMsg"/>
            </label><br>
            <label>
                Podaj nowe hasło:<br>
                <form:password path="newPassword" placeholder="password"/><br>
                <form:errors path="newPassword" cssClass="errorMsg"/>
            </label><br>
            <label>
                Powtórz nowe hasło:<br>
                <form:password path="matchingNewPassword" placeholder="password"/><br>
                <form:errors path="matchingNewPassword" cssClass="errorMsg"/>
            </label><br>
            <form:hidden path="id"/>
            <label><input type="submit" value="Zapisz zmiany"></label>
        </form:form>
        <%--CONTENT-STOP--%>
    </td>
</table>
<%@ include file="fragments/footer.jsp" %>
</body>
</html>
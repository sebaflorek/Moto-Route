<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Reset password</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>Zresetuj hasło</h2>
        <form:form method="post" modelAttribute="userResetPassDto">
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
            <label><input type="submit" value="Zapisz"></label>
        </form:form>
        <%--CONTENT-STOP--%>
    </td>
</table>
<%@ include file="fragments/footer.jsp" %>
</body>
</html>
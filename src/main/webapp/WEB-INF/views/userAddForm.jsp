<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>User Form</title>
    <link rel="stylesheet" href="../../theme/css/style.css">
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>Zarejestruj nowego użytkownika</h2>
        <form:form method="post" modelAttribute="userDto">

            <label>
                Nazwa użytkownika:<br>
                <form:input path="username" placeholder="username"/><br>
                <form:errors path="username" cssClass="errorMsg"/>
            </label><br>
            <label>
                Podaj email:<br>
                <form:input path="email" placeholder="example@example.pl"/><br>
                <form:errors path="email" cssClass="errorMsg"/>
            </label><br>
            <label>
                Podaj hasło:<br>
                <form:password path="password" placeholder="password"/><br>
                <form:errors path="password" cssClass="errorMsg"/>
            </label><br>
            <label>
                Powtórz hasło:<br>
                <form:password path="matchingPassword" placeholder="password"/><br>
                <form:errors path="matchingPassword" cssClass="errorMsg"/>
            </label><br>
            <label><input type="submit" value="Zarejestruj"></label>
            <c:if test="${not empty errorList}">
                <p class="errorMsg">Nie można zarejestrować użytkownika!</p>
            </c:if>

        </form:form>
        <%--CONTENT-START--%>
    </td>
</table>


<%@ include file="fragments/footer.jsp" %>
</body>
</html>
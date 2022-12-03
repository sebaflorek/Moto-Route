<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>User Form</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>Edytuj swoje dane</h2>
        <form:form method="post" modelAttribute="userEditDto">

            <label title="Pamiętaj! Nazwa użytkownika służy do logowania się do serwisu. Zmiana nazwy użytkownika wiązać się będzie z nowym loginem.">
                Nazwa użytkownika:<br>
                <form:input path="username" placeholder="username"/><br>
                <form:errors path="username" cssClass="errorMsg"/>
            </label><br>
            <label>
                Podaj email:<br>
                <form:input path="email" placeholder="example@example.pl"/><br>
                <form:errors path="email" cssClass="errorMsg"/>
            </label><br>
            <%--            <label>--%>
            <%--                Podaj hasło:<br>--%>
            <%--                <form:password path="password" placeholder="password"/><br>--%>
            <%--                <form:errors path="password" cssClass="errorMsg"/>--%>
            <%--            </label><br>--%>
            <form:hidden path="id"/>
            <label><input type="submit" value="Zapisz zmiany"></label>
        </form:form>
        <%--CONTENT-STOP--%>
    </td>
</table>


<%@ include file="fragments/footer.jsp" %>
</body>
</html>
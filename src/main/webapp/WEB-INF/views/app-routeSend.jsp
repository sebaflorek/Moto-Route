<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Send Route</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>WYŚLIJ TRASĘ</h2>
        <form:form method="post" modelAttribute="routeSendDto">

            <label>
                Imię odbiorcy:<br>
                <form:input path="name"/><br>
                <form:errors path="name" cssClass="errorMsg"/>
            </label><br>
            <label>
                Email odbiorcy:<br>
                <form:input path="email"/><br>
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
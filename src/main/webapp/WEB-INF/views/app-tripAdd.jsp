<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Trip Form</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>DODAJ WYCIECZKĘ</h2>
        <form:form method="post" modelAttribute="tripCreateDto">

            <label>
                Nazwa wycieczki:<br>
                <form:input path="name"/><br>
                <form:errors path="name" cssClass="errorMsg"/>
            </label><br>
            <label>
                Opis wycieczki:<br>
                <form:textarea path="description" placeholder="Max 500 znaków"/><br>
                <form:errors path="description" cssClass="errorMsg"/>
            </label><br>
            <label><input type="submit" value="Dodaj"></label>

        </form:form>
        <%--CONTENT-STOP--%>
    </td>
</table>


<%@ include file="fragments/footer.jsp" %>
</body>
</html>
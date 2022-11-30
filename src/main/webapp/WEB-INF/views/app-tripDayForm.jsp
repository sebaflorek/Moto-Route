<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>TripDay Form</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>DODAJ TRASĘ DO WYCIECZKI</h2>
        <form:form method="post" modelAttribute="tripDay">

            <label>
                Podaj numer dnia:<br>
                <form:input path="dayNumber" type="number" min="0" step="1"/><br>
                <form:errors path="dayNumber" cssClass="errorMsg"/>
            </label><br>
            <label>
                Wybierz wycieczkę:<br>
                <form:select path="trip">
                    <form:option value="0" label="--Wybierz wycieczkę--"/>
                    <form:options items="${userTripList}" itemValue="id" itemLabel="name"/>
                </form:select><br>
                <form:errors path="trip" cssClass="errorMsg"/>
            </label><br>
            <label>
                Wybierz trasę:<br>
                <form:select path="route">
                    <form:option value="0" label="--Wybierz trasę--"/>
                    <form:options items="${routeList}" itemValue="id" itemLabel="name"/>
                </form:select><br>
                <form:errors path="route" cssClass="errorMsg"/>
            </label><br>
            <label><input type="submit" value="Dodaj"></label>

        </form:form>
        <%--CONTENT-STOP--%>
    </td>
</table>


<%@ include file="fragments/footer.jsp" %>
</body>
</html>
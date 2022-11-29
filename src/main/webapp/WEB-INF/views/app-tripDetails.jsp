<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Trip details</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>WYCIECZKA: <span class="dashCol">${trip.name.toUpperCase()}</span></h2>
        <hr>
        <button onclick="location.href='<c:url value="/app/trip/route-add"/>'"
                type="button">
            Dodaj trasę do wycieczki
        </button>
        <button onclick="location.href='<c:url value="/app/trip/${trip.id}/route-purge"/>'"
                type="button">
            Usuń wszystkie trasy z wycieczki
        </button>
        <hr>
        <h3>Czas trwania:</h3>
        <p>${trip.numberOfDays} dni</p>
        <h3>Opis:</h3>
        <p>${trip.description}</p>
        <h3>Plan wycieczki:</h3>
        <table class="details-table">
            <tr>
                <th>Dzień</th>
                <th>Nazwa trasy</th>
            </tr>
            <tr>
                <td>1</td>
                <td>Poznaj świętokrzyskie</td>
                <td>
                    <button onclick="location.href='<c:url value="/app/route/details/${route.id}"/>'"
                            type="button">
                        Szczegóły
                    </button>
                    <a href='<c:url value="/app/trip/${trip.id}/route-del/${route.id}"/>'>
                        <button type="submit">Usuń z wycieczki</button>
                    </a>
                </td>
            </tr>
            <tr>
            <td>2</td>
            <td>Poznaj podkarpackie</td>
            <td>
                <button onclick="location.href='<c:url value="/app/route/details/${route.id}"/>'"
                        type="button">
                    Szczegóły
                </button>
                <a href='<c:url value="/app/trip/${trip.id}/route-del/${route.id}"/>'>
                    <button type="submit">Usuń z wycieczki</button>
                </a>
            </td>
            </tr>
        </table>
        <%--CONTENT-STOP--%>
    </td>
</table>


<%@ include file="fragments/footer.jsp" %>
</body>
</html>
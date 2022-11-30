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
        <button onclick="location.href='<c:url value="/app/trip-day/add"/>'"
                type="button">
            Dodaj trasę do wycieczki
        </button>
        <a href='<c:url value="/app/trip-day/delete-all/${trip.id}"/>'>
            <button class="myButton" onclick="return confirm('Czy na pewno chcesz usunąć wszystkie dni z tej wycieczki?')"
                    type="submit">Usuń wszystkie dni
            </button>
        </a>
        <hr>
        <h3>Czas trwania:</h3>
        <p>${trip.tripDays.size()} dni</p>
        <h3>Opis:</h3>
        <p>${trip.description}</p>
        <h3>Plan wycieczki:</h3>
        <table class="details-table">

            <tr>
                <th>Dzień</th>
                <th>Nazwa trasy</th>
            </tr>
            <c:forEach var="tripDay" items="${trip.tripDays}">
                <tr>
                    <td>${tripDay.dayNumber}</td>
                    <td>${tripDay.route.name}</td>
                    <td>
                        <button onclick="location.href='<c:url value="/app/route/details/${tripDay.route.id}"/>'"
                                type="button" class="myButton">
                            Szczegóły trasy
                        </button>
                        <a href='<c:url value="/app/trip-day/delete/${tripDay.id}/${trip.id}"/>'>
                            <button class="myButton" onclick="return confirm('Czy na pewno usunąć ten dzień?')"
                                    type="submit">Usuń dzień
                            </button>
                        </a>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <%--CONTENT-STOP--%>
    </td>
</table>


<%@ include file="fragments/footer.jsp" %>
</body>
</html>
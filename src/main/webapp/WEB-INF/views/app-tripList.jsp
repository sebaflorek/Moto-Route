<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All Trips</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>MOJE WYCIECZKI</h2>
        <hr>
        <button onclick="location.href='<c:url value="/app/trip/route-add"/>'"
                type="button">
            Dodaj trasę do wycieczki
        </button>
        <hr>
        <c:forEach var="trip" items="${tripList}">
            Trasa: <span class=routeName>${trip.name}</span>
            <table class="tab">
                <tr>
                    <td class="tripInfo">
                        Ilość dni: <b>${trip.numberOfDays}</b><br>
                        Opis wycieczki:
                        <b>${trip.description}</b><br>
                    </td>
                    <td class="routeInfo">
                        <button onclick="location.href='<c:url value="/app/trip/details/${trip.id}"/>'" type="button">
                            Szczegóły
                        </button>
                        <br>
                        <button onclick="location.href='<c:url value="/app/trip/edit/${trip.id}"/>'" type="button">
                            Edytuj
                        </button>
                        <br>
                        <a href='<c:url value="/app/trip/delete/${trip.id}"/>'>
                            <button onclick="return confirm('Czy na pewno usunąć Wycieczkę?')" type="submit">Usuń</button>
                        </a>
                    </td>
                </tr>
            </table>
            <hr>
        </c:forEach>
        <%--CONTENT-STOP--%>
    </td>
</table>

<%@ include file="fragments/footer.jsp" %>
</body>
</html>
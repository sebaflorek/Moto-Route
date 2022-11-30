<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Pulpit</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>PULPIT</h2>
        <hr>
        <table>
            <tr>
                <td>
                    <button onclick="location.href='<c:url value="/app/route/add"/>'" type="button">DODAJ TRASĘ</button>
                </td>
                <td>
                    <button onclick="location.href='<c:url value="/app/trip/add"/>'" type="button">DODAJ WYCIECZKĘ
                    </button>
                </td>
                <td>
                    <button onclick="location.href='<c:url value="/app/trip-day/add"/>'" type="button">DODAJ TRASĘ DO WYCIECZKI
                    </button>
                </td>
            </tr>
        </table>
        <br>
        <div>Ilość tras: <b><c:out value="${routesNum}" default="0"/></b></div>
        <div>Ilość wycieczek: <b><c:out value="${tripsNum}" default="0"/></b></div>
        <hr>
        <h3>OSTATNIO DODANA TRASA: <span class="dashCol"><c:out value="${latestRoute.name.toUpperCase()}"
                                                                default="BRAK TRAS"/></span></h3>
        <c:if test="${not empty latestRoute}">
            <table class="tab">
                <tr>
                    <td width="300px" height="300px">
                        <iframe id="smallFrame" src="${latestRoute.map}/embed/" allowfullscreen=""
                                loading="lazy"></iframe>
                    </td>

                    <td class="routeInfo">
                        Rodzaj trasy: <b>${latestRoute.type.name}</b><br>
                        Lokalizacja: <b>${latestRoute.region.name}</b><br>
                        Długość trasy: <b>${latestRoute.distance}km</b><br>
                        <button onclick="location.href='<c:url value="/app/route/details/${latestRoute.id}"/>'"
                                type="button">
                            Szczegóły
                        </button>
                        <br>
                        <button onclick="location.href='<c:url value="/app/route/edit/${latestRoute.id}"/>'"
                                type="button">
                            Edytuj
                        </button>
                        <br>
                        <a href='<c:url value="/app/route/delete/${latestRoute.id}"/>'>
                            <button onclick="return confirm('Czy na pewno usunąć Trasę?')" type="submit">Usuń</button>
                        </a>
                        <br>
                        <button onclick="location.href='<c:url value="/app/route/download/${latestRoute.id}"/>'"
                                type="button">
                            Pobierz
                        </button>
                        <br>
                        <button onclick="location.href='<c:url value="/app/route/send/${latestRoute.id}"/>'"
                                type="button">
                            Wyślij
                        </button>
                        <br>
                        <br>
                        Popularność: <b>${latestRoute.popularity}</b><br>
                        Polubiono: <b>${latestRoute.likes}</b><br>
                    </td>
                </tr>
            </table>
        </c:if>
        <hr>
        <h3>OSTATNIO DODANA WYCIECZKA: <span class="dashCol"><c:out value="${latestTrip.name.toUpperCase()}"
                                                                    default="BRAK WYCIECZEK"/></span></h3>
        <c:if test="${not empty latestTrip}">
            <table class="tab">
                <tr>
                    <td class="tripInfo">
                        Ilość dni: <b>${latestTrip.tripDays.size()}</b><br>
                        Opis wycieczki:
                        <b>${latestTrip.description}</b><br>
                    </td>
                    <td class="routeInfo">
                        <button onclick="location.href='<c:url value="/app/trip/details/${latestTrip.id}"/>'"
                                type="button">
                            Szczegóły
                        </button>
                        <br>
                        <button onclick="location.href='<c:url value="/app/trip/edit/${latestTrip.id}"/>'"
                                type="button">
                            Edytuj
                        </button>
                        <br>
                        <a href='<c:url value="/app/trip/delete/${latestTrip.id}"/>'>
                            <button onclick="return confirm('Czy na pewno usunąć Wycieczkę?')" type="submit">Usuń</button>
                        </a>
                    </td>
                </tr>
            </table>
        </c:if>
        <%--CONTENT-STOP--%>
    </td>
</table>


<%@ include file="fragments/footer.jsp" %>
</body>
</html>
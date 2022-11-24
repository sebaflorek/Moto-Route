<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Favorite Routes</title>
    <link rel="stylesheet" href="../../theme/css/style.css">
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>Lista ulubionych tras:</h2>
        <hr>
        <c:forEach var="route" items="${routeList}">
            Trasa: <span class=routeName>${route.name}</span>
            <table class="tab">
                <tr>
                    <td width="300px" height="300px">
                        <iframe id="smallFrame" src="${route.map}/embed/" allowfullscreen="" loading="lazy"></iframe>
                    </td>

                    <td class="routeInfo">
                        Rodzaj trasy: <b>${route.type.name}</b><br>
                        Lokalizacja: <b>${route.region.name}</b><br>
                        Długość trasy: <b>${route.distance}km</b><br>
                        <button onclick="location.href='<c:url value="/route/all"/>'" type="button">
                            Szczegóły
                        </button>
                        <br>
                        <button onclick="location.href='<c:url value="/route/all"/>'" type="button">
                            Dodaj do ulubionych
                        </button>
                        <br>
                        <button onclick="location.href='<c:url value="/route/all"/>'" type="button">
                            Pobierz
                        </button>
                        <br>
                        <button onclick="location.href='<c:url value="/route/all"/>'" type="button">
                            Wyślij
                        </button>
                        <br>
                        <button onclick="location.href='<c:url value="/route/all"/>'" type="button">
                            Polub
                        </button>
                        <br>
                        <br>
                        Popularność: <b>${route.popularity}</b><br>
                        Polubiono: <b>${route.likes}</b><br>
                    </td>
                </tr>
            </table>
            <hr>
        </c:forEach>
        <%--CONTENT-START--%>
    </td>
</table>

<%@ include file="fragments/footer.jsp" %>
</body>
</html>
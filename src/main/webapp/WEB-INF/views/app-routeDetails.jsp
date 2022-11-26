<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Route details</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>TRASA: <span class="dashCol">${route.name.toUpperCase()}</span></h2>
        <hr>
        <button class="myButton" onclick="location.href='<c:url value="/app/route/download/${route.id}"/>'" type="button">
            Pobierz
        </button>
        <button class="myButton" onclick="location.href='<c:url value="/app/route/send/${route.id}"/>'" type="button">
            Wyślij
        </button>
        <hr>
        <h3>Opis:</h3>
        <p>${route.description}</p>
        <h3>Szczegóły:</h3>
        <table class="details-table">
            <tr>
                <th>Długość</th>
                <th>Lokalizacja</th>
                <th>Typ</th>
                <th>Popularność</th>
                <th>Polubienia</th>
            </tr>
            <tr>
                <td>${route.distance}</td>
                <td>${route.region.name}</td>
                <td>${route.type.name}</td>
                <td>${route.popularity}</td>
                <td>${route.likes}</td>
            </tr>
        </table>
        <h3>Mapa:</h3>
        <iframe id="bigFrame" src="${route.map}" allowfullscreen="true"
                loading="lazy"></iframe>
        <%--CONTENT-STOP--%>
    </td>
</table>


<%@ include file="fragments/footer.jsp" %>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>About</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <h2>Aplikacja Moto Route</h2>
        <p>Zbudowana przez motocyklistę dla motocyklistów platforma do dzielenia się trasami motocyklowymi na jednodniowe wypady wkoło komina oraz kilkudniowe wycieczki.</p>
        <p>Masz ochotę się przejechać, ale nie masz już pomysłu gdzie? Zarejestruj się i rozpocznij przygodę z Moto Route.</p>
        <p>Dzięki nam możesz:</p>
        <ul>
            <li>Dzielić się swoimi trasami z innymi</li>
            <li>Przeglądać dostępne trasy motocyklowe w całej Polsce</li>
            <li>Ściągać oraz udostępniać trasy znajomym</li>
            <li>Planować kilkudniowe wycieczki</li>
        </ul>
        <p>Celem przyświecającym aplikacji jest zrzeszanie motocyklistów oraz szerzenie szeroko rozumianej turystyki motocyklowej.</p>

    </td>
</table>


<%@ include file="fragments/footer.jsp" %>
</body>
</html>
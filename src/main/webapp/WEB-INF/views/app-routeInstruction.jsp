<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Moto Route</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>Skąd wziąć link do mapy?</h2>
        <p>Żeby dodać mapę do swojego profilu wykonaj poniższe kroki:</p>
        <ul>
            <li>Wejdź na <a href="https://maps.openrouteservice.org">Open Route Service</a> lub skorzystaj z okna na
                stronie dodawania trasy.
            </li>
            <li>Zaprojektuj swoją trasę i w rozwijanym na mapie menu kliknij share.</li>
            <li>Skopiuj i wklej link do Swojej mapy w odpowiednie pole formularza dodawania nowej trasy.</li>
        </ul>
        Gotowe!
        <%--CONTENT-STOP--%>
    </td>
</table>


<%@ include file="fragments/footer.jsp" %>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Pulpit</title>
    <link rel="stylesheet" href="../../theme/css/style.css">
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">

        <h2>Pulpit</h2>
        <hr>
        <table>
            <tr>
                <td>
                    <button onclick="location.href='<c:url value="/pulpit"/>'" type="button">+ TRASĘ</button>
                </td>
                <td>
                    <button onclick="location.href='<c:url value="/pulpit"/>'" type="button">+ WYCIECZKĘ</button>
                </td>
                <td>
                    <button onclick="location.href='<c:url value="/pulpit"/>'" type="button">+ TRASĘ DO WYCIECZKI</button>
                </td>
            </tr>
        </table>
        <br>
        <div>Ilość tras:</div>
        <div>Ilość wycieczek:</div>
        <hr>
        <h3>OSTATNIO DODANA TRASA: <span class="dashCol">NAZWA TRASY</span></h3>
        <br>
        <br>
        <br>
        <br>
        <br>
        <hr><h3>OSTATNIO DODANA WYCIECZKA: <span class="dashCol">NAZWA WYCIECZKI</span></h3>
        <br>
        <br>
        <br>
        <br>
        <br>

    </td>
</table>


<%@ include file="fragments/footer.jsp" %>
</body>
</html>
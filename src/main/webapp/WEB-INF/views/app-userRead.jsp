<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Contact</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <h2>TWÓJ PROFIL</h2>
        <hr>
        <button onclick="location.href='<c:url value="/app/user/edit"/>'" type="button">
            Edytuj profil
        </button>
        <button onclick="location.href='<c:url value="/app/user/change-pass"/>'" type="button">
            Zmień hasło
        </button>
        <a href='<c:url value="/app/user/delete"/>'>
            <button onclick="return confirm('Czy na pewno chcesz trwale usunąć swoje konto oraz jego zawartość? Operacja jest nieodwracalna!')"
                    type="submit">Usuń konto
            </button>
        </a>
        <hr>
        <h3>Login: <span class="dashCol">${userInfo.username}</span></h3>
        <h3>Email: <span class="dashCol">${userInfo.email}</span></h3>
    </td>
</table>

<%@ include file="fragments/footer.jsp" %>
</body>
</html>
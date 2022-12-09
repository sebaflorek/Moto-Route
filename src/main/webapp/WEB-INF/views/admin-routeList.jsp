<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin:Users</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>UŻYTKOWNICY</h2>
        <hr>
        <table class="details-table">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Created</th>
                <th>Updated</th>
                <th>Author ID</th>
            </tr>
            <c:forEach var="route" items="${routeList}">
                <tr>
                    <td>${route.id}</td>
                    <td>${route.name}</td>
                    <td>${route.created}</td>
                    <td>${route.updated}</td>
                    <td>${route.authorId}</td>
                    <td class="actions">
                        <a href='<c:url value="/app/route/details/${route.id}"/>'>
                            <button class="myButton" type="submit">Szczegóły</button>
                        </a>
                        <a href='<c:url value="/admin/route/delete/${route.id}"/>'>
                            <button class="myButton" style="color: red"
                                    onclick="return confirm('Czy na pewno trwale usunąć Trasę?')" type="submit">Usuń
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
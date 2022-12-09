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
                <th>Username</th>
                <th>Email</th>
                <th>Enabled</th>
                <th>Roles</th>
            </tr>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.enabled==1}">
                                YES
                            </c:when>
                            <c:when test="${user.enabled==0}">
                                NO
                            </c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:forEach varStatus="loopStatus" var="role" items="${user.roles}">
                            ${role.name}<c:if test="${!loopStatus.last}">,</c:if>
                        </c:forEach>
                    </td>
                    <td class="actions">
                        <a href='<c:url value="/admin/user/edit/${user.id}"/>'>
                            <button class="myButton" type="submit">Edytuj</button>
                        </a>
                        <c:choose>
                            <c:when test="${user.enabled=='1'}">
                                <a href='<c:url value="/admin/user/disable/${user.id}"/>'>
                                    <button class="myButton" type="submit">Zablokuj</button>
                                </a>
                            </c:when>
                            <c:when test="${user.enabled=='0'}">
                                <a href='<c:url value="/admin/user/enable/${user.id}"/>'>
                                    <button class="myButton" type="submit">Odblokuj</button>
                                </a>
                            </c:when>
                        </c:choose>
                        <a href='<c:url value="/admin/user/delete/${user.id}"/>'>
                            <button class="myButton" style="color: red"
                                    onclick="return confirm('Czy na pewno trwale usunąć Użytkownika?')" type="submit">Usuń
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
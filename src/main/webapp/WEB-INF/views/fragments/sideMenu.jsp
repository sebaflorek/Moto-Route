<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<sec:authorize access="isAuthenticated()">
<td class="sideMenu">
<button onclick="location.href='<c:url value="/route/dashboard"/>'" type="button">PULPIT</button><br><br>
<button onclick="location.href='<c:url value="/"/>'" type="button">MOJE TRASY</button><br><br>
<button onclick="location.href='<c:url value="/"/>'" type="button">DODAJ TRASĘ</button><br><br>
<button onclick="location.href='<c:url value="/"/>'" type="button">ULUBIONE TRASY</button><br><br>
<button onclick="location.href='<c:url value="/"/>'" type="button">WYCIECZKI</button><br><br>
<button onclick="location.href='<c:url value="/"/>'" type="button">EDYTUJ PROFIL</button><br><br>
<button onclick="location.href='<c:url value="/"/>'" type="button">ZMIEŃ HASŁO</button><br><br>
<button onclick="location.href='<c:url value="/logout"/>'" type="button">WYLOGUJ</button>
</td>
</sec:authorize>
<%--<if>--%>
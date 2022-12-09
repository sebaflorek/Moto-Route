<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
<td class="sideMenu">
<button onclick="location.href='<c:url value="/app/route/dashboard"/>'" type="button">PULPIT</button><br><br>
<button onclick="location.href='<c:url value="/app/route/my-list"/>'" type="button">MOJE TRASY</button><br><br>
<button onclick="location.href='<c:url value="/app/route/add"/>'" type="button">DODAJ TRASĘ</button><br><br>
<button onclick="location.href='<c:url value="/app/route/fav-list"/>'" type="button">ULUBIONE TRASY</button><br><br>
<button onclick="location.href='<c:url value="/app/trip/list"/>'" type="button">MOJE WYCIECZKI</button><br><br>
<button onclick="location.href='<c:url value="/app/trip/add"/>'" type="button">DODAJ WYCIECZKĘ</button><br><br>
<button onclick="location.href='<c:url value="/app/user/details"/>'" type="button">TWÓJ PROFIL</button><br><br>
<button onclick="location.href='<c:url value="/logout"/>'" type="button">WYLOGUJ</button>
<sec:authorize access="hasRole('ADMIN')">
    <p style="text-align: center">Admin panel:</p>
    <button onclick="location.href='<c:url value="/admin/user/list"/>'" type="button">UŻYTKOWNICY</button><br><br>
    <button onclick="location.href='<c:url value="/admin/route/list"/>'" type="button">TRASY</button>
</sec:authorize>
</td>
</sec:authorize>
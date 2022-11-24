<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header>
    <style>
        img {
            max-width: 400px;
            width: 50%;
        }
    </style>
    <img src='<c:url value="/images/mainlogo.png"></c:url>' alt="mainLogo"/>
</header>
<hr>

<button onclick="location.href='<c:url value="/"/>'" type="button">STRONA GŁÓWNA</button>
<button onclick="location.href='<c:url value="/register"/>'" type="button">REJESTRACJA</button>
<button onclick="location.href='<c:url value="/login"/>'" type="button">LOGOWANIE</button>
<button onclick="location.href='<c:url value="/about"/>'" type="button">O APLIKACJI</button>
<button onclick="location.href='<c:url value="/routes"/>'" type="button">TRASY</button>
<button onclick="location.href='<c:url value="/contact"/>'" type="button">KONTAKT</button>
<sec:authorize access="isAuthenticated()">
    <div style="display: inline-block">Zalogowany jako: <b><sec:authentication property="principal.username"/></b></div>
    <div style="display: inline-block; color: lightgray">tymczasowo wyświetlane role:<b><sec:authentication property="authorities"/></b></div>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <div style="display: inline-block">Jesteś niezalogowany</div>
</sec:authorize>
<hr>

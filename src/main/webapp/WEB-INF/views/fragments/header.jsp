<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <style>
        img {
            max-width: 400px;
            width: 50%;
        }
    </style>
    <img src='<c:url value="/images/mainlogo.png"></c:url>' alt="mailLogo"/>
</header><hr>

<button onclick="location.href='<c:url value="/"/>'" type="button">STRONA GŁÓWNA</button>
<button onclick="location.href='<c:url value="/register"/>'" type="button">REJESTRACJA</button>
<button onclick="location.href='<c:url value="/login"/>'" type="button">LOGOWANIE</button>
<button onclick="location.href='<c:url value="/about"/>'" type="button">O APLIKACJI</button>
<button onclick="location.href='<c:url value="/route/all"/>'" type="button">TRASY</button>
<button onclick="location.href='<c:url value="/contact"/>'" type="button">KONTAKT</button>
<div style="display: inline-block">Zalogowany jako: <b>GOŚĆ</b></div><hr>

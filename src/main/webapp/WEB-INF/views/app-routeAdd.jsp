<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Add Route</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>DODAJ TRASĘ</h2>
        <table width="100%">
            <td width="20%" style="vertical-align: top">
                <form:form method="post" modelAttribute="routeCreateDto">
                    <label>
                        Nazwa trasy:<br>
                        <form:input path="name"/><br>
                        <form:errors path="name" cssClass="errorMsg"/>
                    </label><br>
                    <label>
                        Długość trasy [km]:<br>
                        <form:input path="distance" type="number" min="0" step="1"/><br>
                        <form:errors path="distance" cssClass="errorMsg"/>
                    </label><br>
                    <label>
                        Opis trasy:<br>
                        <form:textarea path="description" placeholder="Max 500 znaków"/><br>
                        <form:errors path="description" cssClass="errorMsg"/>
                    </label><br>
                    <label>
                        <form:select path="region">
                            <form:option value="0" label="--Wybierz region--"/>
                            <form:options items="${regionList}" itemValue="id" itemLabel="name"/>
                        </form:select><br>
                        <form:errors path="region" cssClass="errorMsg"/>
                    </label><br>
                    <label>
                        <form:select path="type">
                            <form:option value="0" label="--Wybierz typ--"/>
                            <form:options items="${typeList}" itemValue="id" itemLabel="name"/>
                        </form:select><br>
                        <form:errors path="type" cssClass="errorMsg"/>
                    </label><br>
                    <label>
                        Link do mapy:<br>
                        <form:input path="map" placeholder="https://maps.openrouteservice.org/..."/><br>
                        <form:errors path="map" cssClass="errorMsg"/>
                        <a style="font-size: smaller" href='<c:url value="/app/route/info" />'>Jak to zrobić?</a>
                    </label><br><br>
                    <form:hidden path="authorId" value="${currentUser.user.id}"/>
                    <label><input type="submit" value="Dodaj"></label>
                </form:form>
                <%--Na potrzeby testów-START--%>
                <p style="color: darkgrey">Testowy przykład:</p>
                <p style="color: darkgrey; inline-size: 175px; overflow: scroll">
                    https://maps.openrouteservice.org/#/directions/Jachtowa,%C5%81eba,PM,Polska/Bursztynowa,Hel,PM,Polska/data/55,130,32,198,15,97,4,224,38,9,96,59,2,24,5,192,166,6,113,0,184,64,70,1,216,3,160,21,128,22,83,240,13,154,128,25,245,58,128,153,75,174,242,1,160,184,195,175,192,7,33,1,117,75,55,16,207,128,110,65,196,69,22,104,89,128,78,114,203,154,8,29,220,177,126,229,152,6,103,192,121,65,234,7,203,80,18,19,136,8,0,29,83,192,136,155,14,80,0,188,160,5,181,207,142,173,189,180,4,0,25,188,0,13,186,46,8,44,52,60,0,27,146,0,57,128,45,24,50,52,13,136,16,122,8,122,52,58,34,24,20,94,1,100,55,183,161,108,58,44,8,0,47,157,80,0
                </p>
                <%--Na potrzeby testów-END--%>
            </td>
            <td width=80% style="vertical-align: top; padding-left: 15px">
                Zaprojektuj trasę:<br>
                <iframe id="fitFrame" src="https://maps.openrouteservice.org/" allowfullscreen=""
                        loading="lazy"></iframe>
            </td>
        </table>
        <%--CONTENT-STOP--%>
    </td>
</table>


<%@ include file="fragments/footer.jsp" %>
</body>
</html>
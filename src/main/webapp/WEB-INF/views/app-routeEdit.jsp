<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Edit Route</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent">
        <%--CONTENT-START--%>
        <h2>EDYTUJ TRASĘ</h2>
        <table width="100%">
            <td width="20%" style="vertical-align: top">
                <form:form method="post" modelAttribute="routeEditDto">
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
                    <%--            <form:hidden path="popularity"/>--%>
                    <%--            <form:hidden path="likes"/>--%>
                    <%--            <form:hidden path="created"/>--%>
                    <form:hidden path="authorId"/>
                    <label><input type="submit" value="Zapisz"></label>
                </form:form>
                <%--Na potrzeby testów-START--%>
                <%--                <p style="color: darkgrey">Testowy przykład:</p>--%>
                <%--                <p style="color: darkgrey; inline-size: 175px; overflow: scroll">--%>
                <%--                    https://maps.openrouteservice.org/#/directions/Jachtowa,%C5%81eba,PM,Polska/Gda%C5%84ska,W%C5%82adys%C5%82awowo,PM,Polska/Majora%20Henryka%20Sucharskiego,Gda%C5%84sk,PM,Polska/data/55,130,32,198,15,97,4,224,38,9,96,59,2,24,5,192,166,6,113,0,184,64,70,1,216,3,160,21,128,22,83,240,13,154,128,25,245,58,128,153,75,174,242,1,160,184,195,175,192,7,33,1,117,75,55,16,207,128,110,65,197,201,214,164,217,136,170,133,75,13,45,220,175,97,1,57,240,49,160,63,129,210,50,7,22,160,60,162,242,204,3,50,144,31,119,117,194,90,229,208,115,121,160,131,229,9,8,65,56,64,32,0,29,81,224,33,17,176,113,64,0,188,160,1,109,113,241,152,66,195,160,32,0,205,224,0,109,209,112,65,97,161,224,0,220,144,1,204,1,104,193,145,161,130,65,51,209,179,209,161,209,16,193,10,241,219,32,146,146,59,97,209,96,64,1,125,70,128--%>
                <%--                </p>--%>
                <%--Na potrzeby testów-END--%>
            </td>
            <td width=80% style="vertical-align: top; padding-left: 15px">
                Zaprojektuj trasę:<br>
                <iframe id="fitFrame" src="${routeEditDto.map}" allowfullscreen=""
                        loading="lazy" sandbox="allow-same-origin allow-scripts"></iframe>
            </td>
        </table>
        <%--CONTENT-STOP--%>
        <%@ include file="fragments/footer.jsp" %>
</body>
</html>
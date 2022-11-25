<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Maintenance</title>
    <link rel="stylesheet" href='<c:url value="/theme/css/style.css"/>'>
    <style>
        img {
            max-width: 100%;
            max-height: 100%;
        }

        .center {
            display: block;
            margin-left: auto;
            margin-right: auto;
            width: 100%;
        }
    </style>
</head>
<body>
<%@ include file="fragments/header.jsp" %>

<table style="width: 100%">
    <%@ include file="fragments/sideMenu.jsp" %>
    <td class="mainContent" style="text-align: center">
        <%--CONTENT-START--%>
            <div class="center">
                <h2 class="dashCol">
                    Oops! Under construction!<br>
                    We apologize for the inconvenience.
                </h2>
                <img src="<c:url value="/images/underCon.jpg"/>" alt="underCon">
            </div>
        <%--CONTENT-STOP--%>
    </td>
</table>


<%@ include file="fragments/footer.jsp" %>
</body>
</html>
<%@ page import="entities.FlightEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head><title>Airport-Title</title></head>
<body>
<H1 align="center">FLIGHT LIST</H1>
<hr>
<%------------------------------~~~~~~<<    F I L T E R S    >>~~~---------------------------------------------%>
<h2 align="center">Filters:</h2>
<form action="/info" method="post">
    <table border="1" cellpadding="5" cellspacing="1" align="center">
        <tr>
            <%--left--%>
            <td width=50% style="background-color: aliceblue">
                <p align="center">Type:</p>
                <form>
                    <input type="radio" name="typeFilter" value="ВСЁ"> ВСЁ <br>
                    <input type="radio" name="typeFilter" value="ПРИЛЁТ"> ПРИЛЁТ <br>
                    <input type="radio" name="typeFilter" value="ОТЛЁТ"> ОТЛЁТ
                </form>
            </td>
            <%--right--%>
            <td width=50% style="background-color: azure">
                <p align="center">Sort:</p>
                <form>
                    <input type="radio" name="sortFilter" value="LeavingTime"> Leaving time <br>
                    <input type="radio" name="sortFilter" value="ArrivalTime"> Arrival time
                </form>
            </td>
        <tr align="center">
            <%--s k i p--%>
            <td width=50% style="background-color: burlywood">
                <button type="submit" name="action" value="filter-sort">
                    skip all
                </button>
            </td>
            <%--s u b m i t--%>
            <td width=50% style="background-color: lightgreen">
                <button type="submit" name="action" value="skip">
                    accept
                </button>
            </td>
        </tr>
    </table>
</form>
<br>
<%------------------------------------<<  A D D       N E W  >>-------------------------------------------------%>
<hr>
<br>
<form action="/add" method="get">
    <button type="submit" style="width: 100%; height:30pt"> add new flight</button>
</form>
<br>

<%------------------------------------<<    T A B L E    >>-------------------------------------------------%>
<table border="2" cellpadding="10" cellspacing="1" align="center">
    <thead><%--  head --%>
    <tr style="background-color: aquamarine">
        <th>Flight Number</th>
        <th>Direction</th>

        <th>From</th>
        <th>Leaving time</th>

        <th>To</th>
        <th>Arrival time</th>

        <th></th>
        <th></th>
    </tr>
    </thead>
    <%------------------------------------<<    R O W    >>-------------------------------------------------%>
    <c:forEach var="flight" items="${list}">
        <jsp:useBean id="flight" scope="page" type="entities.FlightEntity"/>

        <tr align="center" style="background-color:${flight.directionType ? '#dcdcdc' : ''}">
            <td><p>${flight.flightNumber}</p></td>
            <td>
                <p style="color:${flight.directionType ? 'green' : 'red'}"> ${flight.directionType ? 'ПРИЛЁТ': 'ОТЛЁТ'}</p>
            </td>

            <td><p><b>${flight.leavingFrom}</b></p></td>
            <td><p>${flight.leavingTime}</p></td>

            <td><b>${flight.arrivalTo}</b></td>
            <td><p>${flight.arrivalTime}</p></td>

            <td><a href="update?action=update&id=${flight.id}">Update</a></td>
            <td><a href="info?action=delete&id=${flight.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<%--  << TABLE  --%>
</body>
</html>
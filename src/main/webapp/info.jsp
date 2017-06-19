<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Airport-Title</title>
</head>
<body>

<H1 align="center">Распысання лiтачкiв</H1>

<hr>
<table border="2" cellpadding="10" cellspacing="1" align="center">
    <thead>
    <tr style="background-color: aquamarine">
        <th>Flight Number</th>
        <th>Direction</th>

        <th>From</th>
        <th>Leaving time</th>

        <th>To</th>
        <th align="center">Arrival time</th>

        <th></th>
        <th></th>
    </tr>
    </thead>

    <c:forEach var="flight" items="${list}">
        <jsp:useBean id="flight" scope="page" type="entities.FlightEntity"/>

        <tr align="center" style="background-color:${flight.directionType ? '#dcdcdc' : ''}">
            <td><p>${flight.flightNumber}</p></td>
            <td>
                <p style="color:${flight.directionType ? 'green' : 'red'}"> ${flight.directionType ? 'ПРИЛЁТ': 'ОТЛЁТ'}</p>
            </td>

            <td><p><b>${flight.leavingFrom}</b></p></td>
            <td><p>${flight.leavingTime}</p></td>

            <td><p><b>${flight.arrivalTo}</b></p></td>
            <td><p>${flight.arrivalTime}</p></td>

            <td><a href="update?action=update&flightNumber=${flight.flightNumber}">Update</a></td>
            <td><a href="info?action=delete&flightNumber=${flight.flightNumber}">Delete</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
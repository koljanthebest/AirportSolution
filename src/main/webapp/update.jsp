<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update item</title>
</head>
<body>

<%------------------------------------<<    T A B L E    >>-------------------------------------------------%>
<form action="/update" method="post">
    <table border="4" cellpadding="20" cellspacing="2" align="center">
        <thead align="center">
        <tr style="background-color: aquamarine">
            <th>Flight Number</th>
            <th>Direction</th>

            <th>From</th>
            <th>Leaving time</th>

            <th>To</th>
            <th>Arrival time</th>

            <th></th>
        </tr>
        </thead>
        <%------------------------------------<<    R O W    >>-------------------------------------------------%>
        <tr item="${item}" var="item">
            <%--<jsp:useBean id="item" scope="page" type="entities.FlightEntity"/>--%>
            <input type="hidden" name="id" value="${item.id}">
            <td><input type="text" name="flightNumber" value="${item.flightNumber}"></td>

            <td style="color:${item.directionType ? 'green' : 'red'}">
                <select name="type" value="${item.directionType? 'ПРИЛЁТ': 'ОТЛЁТ'}">
                    <option value="true">ПРИЛЁТ</option>
                    <option value="false">ОТЛЁТ</option>
                </select>
            </td>

            <td><b><input type="text" name="leavingFrom" value="${item.leavingFrom}"></b></td>
            <td><b><input type="time" name="leavingTime" value="${item.leavingTime}"></b></td>

            <td><b><input type="text" name="arrivalTo" value="${item.arrivalTo}"></b></td>
            <td><input type="time" name="arrivalTime" value="${item.arrivalTime}"></td>
            <td><input type="submit" value="save"/></td>
        </tr>
    </table>
</form>


</body>
</html>

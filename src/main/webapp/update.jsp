<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update item</title>
</head>
<body>


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

        <tr item="${item}" var="item">
            <%--<jsp:useBean id="item" scope="page" type="entities.FlightEntity"/>--%>

            <td><p>${item.flightNumber}</p></td>

            <td style="color:${item.directionType ? 'green' : 'red'}">
                <input list="type" name="type"
                       value="${item.directionType? 'ПРИЛЁТ': 'ОТЛЁТ'}">
                <datalist id="type">
                    <option value="ПРИЛЁТ"></option>
                    <option value="ОТЛЁТ"></option>
                </datalist>
            </td>

            <td><b><input type="text" name="arrivalTo" value="${item.leavingFrom}"></b></td>
            <td><b><input type="time" name="leavingTime" value="${item.leavingTime}"></b></td>

            <td><b><input type="text" name="arrivalTo" value="${item.arrivalTo}"></b></td>
            <td><input type="time" name="arrivalTime" value="${item.arrivalTime}"></td>
            <td><input type="submit" value="save"/></td>
        </tr>
    </table>
</form>


</body>
</html>

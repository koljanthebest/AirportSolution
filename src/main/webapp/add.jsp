<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new flight</title>
</head>
<body>
<%------------------------------------<<    T A B L E    >>-------------------------------------------------%>
<form action="/add" method="post">
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
        <tr>
            <td><input type="text" name="flightNumber" value=""></td>

            <td>
                <select name="type">
                    <option value="1">ПРИЛЁТ</option>
                    <option value="0">ОТЛЁТ</option>
                </select>
            </td>

            <td><b><input type="text" name="leavingFrom" value=""></b></td>
            <td><b><input type="time" name="leavingTime" value=""></b></td>

            <td><b><input type="text" name="arrivalTo"></b></td>
            <td><input type="time" name="arrivalTime"></td>
            <td><input type="submit" value="add"/></td>
        </tr>
    </table>
</form>

</body>
</html>

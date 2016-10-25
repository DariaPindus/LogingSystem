
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Detail weather </h2>
<table>
    <tr>
        <th>Day time</th>
        <th>Celsiuses</th>
        <th>Fahrenheits</th>
    </tr>
    <c:forEach var="temp" items="${tempList}">
        <tr>
            <td><c:out value="${temp.dayTime}"></c:out> </td>
            <td><c:out value="${temp.celsius}"></c:out> </td>
            <td><c:out value="${temp.fahrenheit}"></c:out> </td>
        </tr>
    </c:forEach>
</table>

<h2>Daily weather</h2>
<table>
    <tr>
        <th>Day</th>
        <th>Temperature in C</th>
        <th>Temperature in F</th>
    </tr>
    <tr>
        <td>${dayTemp.day}</td>
        <td>${dayTemp.celsius}</td>
        <td>${dayTemp.fahrenheit}</td>
    </tr>
</table>

</body>
</html>

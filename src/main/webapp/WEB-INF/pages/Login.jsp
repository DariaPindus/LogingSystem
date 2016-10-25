<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<br> Current temperature in your city: </br>


<h1>Please Login</h1>
<form method="post" action="/login" modelAttribute="user">
    <table>
        <tr>
            <td><p>Login:</td>
            <td><input type="text" name="userName"/>
        </tr>
        <tr>
            <td><p>Password:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>
                <div style="color: red">${error}</div>
            </td>
        </tr>
        <td><input type="submit" value="Log In"></td>
        <td><a href="/registration"> Register </a></td>
        <td><a href="/service/check">Check</a></td>
        <td><a href="/users">View all users</a></td>
        </tr>
    </table>
</form>
<table border="1">
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
</body>
</html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
    <h1>Please Login</h1>
        <form method="post" action="/login" modelAttribute="user">
        <table>
        <tr>
        	<td><p>Login: </td>
        	<td><input type="text" name="userName"/>
        </tr>
        <tr>
            <td><p>Password: </td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td> <div style="color: red">${error}</div> </td>
        </tr>
            <td><input type="submit" value="Log In"></td>
            <td><a href="/registration"> Register </a></td>
            <td><a href="/service/check">Check</a></td>
            <td><a href="/users">View all users</a></td>
        </tr>
        </form>


</body>
</html>
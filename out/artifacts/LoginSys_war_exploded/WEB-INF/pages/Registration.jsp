<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<body>
<h1>Registration form</h1>
<form:errors path="*"/>

<form method="post" action="/registration" modelAttribute="user">
    <table>
        <tr>
            <td><p>Enter your login:</td>
            <td><input type="text" name="userName"/>
        </tr>
        <tr>
            <td><p>Password:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>Enter your address:</tr>
        <tr>
            <td><p> Country:</td>
            <td>
                <select name="address.country">
                    <option value="NONE" selected>Select county</option>
                    <c:forEach var="country" items="${countryList}">
                        <option value="${country}"><c:out value="${country}"/></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><p>City:</td>
            <td><input type="text" name="address.city"/></td>
        </tr>
        <tr>
            <td><p>Street:</td>
            <td><input type="text" name="address.street"/></td>
        </tr>
        <tr>
            <td><p>Building:</td>
            <td><input type="text" name="address.building"/></td>
        </tr>
        <td>
            <div style="color: red">${error}</div>
        </td>
        </tr>
        <td><input type="submit" value="Register"></td>
        </tr>
    </table>
</form>

</body>
</html>
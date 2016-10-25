<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users list</title>
</head>
<body>
<table border="1">
  <tr>
    <th>User Name</th>
    <th>Country</th>
    <th>City</th>
    <th>Street</th>
  </tr>
  <c:forEach var="user" items="${userList}">
    <tr>
      <td><c:out value="${user.userName}"/></td>
      <td><c:out value="${user.address.country}"/></td>
      <td><c:out value="${user.address.city}"/></td>
      <td><c:out value="${user.address.building}"/></td>
    </tr>
  </c:forEach>

  <form method="post" action="/users/showByCity">
  <input type="text" name="city"/>
  <input type="submit" value="Submit">
  </form>
</table>
</body>
</html>
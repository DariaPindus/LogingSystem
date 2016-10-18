<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Success Form</title>
</head>
<body>
	<font color="green"><h1>Hello</h1></font>
	<h2>${msg}</h2> You have successfully logged in.
	<br>Your cookie is : ${cookie} </br>

	<form method="post" action="/service/success" >
	    <br> Enter you favourite color: <input type="text" name="color"> </br>
	    <select >
	        <c:foreach
	    </select>
	    <input type="submit" value="Ok"/>
	</form>
	<br><a href="/logout"> Logout </a></br>
</body>
</html>
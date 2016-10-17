<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer Save Page</title>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
</head>
<body>

	<form:form method="POST" commandName="user" action="/register">
		<table>
			<tr>
				<td>Name:</td>
				<td><form:input path="userName" /></td>
				<td><form:errors path="userName" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password" /></td>
				<td><form:errors path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Country:</td>
				<td><form:select path="user.address.country" /></td>
                    <form:option value="NONE" label="---Select---"/>
                    <form:options items="${countryList}"/>
                    <form:select>
				<td><form:errors path="address.country" cssClass="error" /></td>
			</tr>
			<tr>
				<td>City:</td>
				<td><form:input path="user.address.city"/></td>
				<td><form:errors path="address.city" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Street:</td>
				<td><form:input path="user.address.street" /></td>
				<td><form:errors path="address.street" cssClass="error" /></td>
			</tr>
            <tr>
				<td>Street:</td>
				<td><form:input path="user.address.building" /></td>
				<td><form:errors path="address.building" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Save Customer"></td>
			</tr>
		</table>
	</form:form>

</body>
</html>
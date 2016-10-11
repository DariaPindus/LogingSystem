<html>

<body>
    <h1>Registration form</h1>
        <form method="post" action="/registration">
        <table>
        <tr>
        	<td><p>Enter your login: </td>
        	<td><input type="text" name="userName"/>
        </tr>
        <tr>
            <td><p>Password: </td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td> <div style="color: red">${error}</div> </td>
        </tr>
            <td><input type="submit" value="Register"></td>
        </tr>
        </form>

</body>
</html>
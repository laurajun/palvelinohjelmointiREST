<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css"> 
<meta charset="utf-8">
<title>Vaalikone login</title>
</head>
<body>
<div class="header">
		<h1>Vaalikone</h1>
	</div>
    <div class="action" style="text-align: center">
        <h1>Login</h1>
        <form action="Login" method="post">
            <label for="username">Username:</label>
            <input name="username" size="30" />
            <br><br>
            <label for="password">Password:</label>
            <input type="password" name="password" size="30" />
            <br>${message}
            <br><br>           
            <button class="submit-button" type="submit">Login</button>
        </form>
    </div>
 	<div class="footer">
		<p>&copy; Vaalikone. All rights reserved.</p>
	</div>
</body>
</html>
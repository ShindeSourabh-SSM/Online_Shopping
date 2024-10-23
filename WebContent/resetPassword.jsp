<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reset PASSWORD</title>
</head>
<body>
<form action="ResetPasswordSrv" method="post">
    <h3>Reset Password</h3>
    <input type="hidden" name="mobile" value="${mobile}" />
    <label for="password">New Password:</label>
    <input type="password" name="password" required />
    <br><br>
    <input type="submit" value="Reset Password" />
</form>


</body>
</html>
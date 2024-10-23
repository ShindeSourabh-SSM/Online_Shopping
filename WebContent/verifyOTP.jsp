<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OTP Verification</title>
</head>
<body>
<form action="VerifyOTPSrv" method="post">
    <h3>Verify OTP</h3>
    <label for="otp">Enter OTP sent to your mobile:</label>
    <input type="text" name="otp" required />
    <br><br>
    <input type="submit" value="Verify OTP" />
</form>

</body>
</html>
package com.shashi.srv;

import java.io.IOException;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.service.impl.UserServiceImpl;

@WebServlet("/ForgotPasswordSrv")
public class ForgotPasswordSrv extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String emailOrMobile = request.getParameter("emailOrMobile");
        UserServiceImpl udao = new UserServiceImpl();
        String status = "";

        if (emailOrMobile.contains("@")) { // Email reset
            String resetToken = generateResetToken();
            boolean isEmailSent = udao.sendPasswordResetEmail(emailOrMobile, resetToken); // Assume this method sends the email

            if (isEmailSent) {
                status = "Password reset link sent to your email.";
            } else {
                status = "Failed to send reset link. Please check your email address.";
            }
        } else { // Mobile reset via OTP
            String otp = generateOTP();
            boolean isOTPSent = udao.sendOTP(emailOrMobile, otp); // Assume this method sends the OTP

            if (isOTPSent) {
                request.getSession().setAttribute("otp", otp);
                request.getSession().setAttribute("mobile", emailOrMobile);
                response.sendRedirect("verifyOTP.jsp"); // Redirect to OTP verification page
                return;
            } else {
                status = "Failed to send OTP. Please check your mobile number.";
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("forgotPassword.jsp?message=" + status);
        rd.include(request, response);
    }

    // Generate OTP (6-digit)
    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // Generate a password reset token (for email)
    private String generateResetToken() {
        return java.util.UUID.randomUUID().toString();
    }
}

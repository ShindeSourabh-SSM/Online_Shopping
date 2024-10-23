package com.shashi.srv;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.service.impl.UserServiceImpl;

@WebServlet("/VerifyOTPSrv")
public class VerifyOTPSrv extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String enteredOTP = request.getParameter("otp");
        String sessionOTP = (String) request.getSession().getAttribute("otp");
        String mobile = (String) request.getSession().getAttribute("mobile");
        String status = "";

        if (enteredOTP.equals(sessionOTP)) {
            // Redirect to reset password page
            response.sendRedirect("resetPassword.jsp?mobile=" + mobile);
        } else {
            status = "Invalid OTP. Please try again.";
            RequestDispatcher rd = request.getRequestDispatcher("verifyOTP.jsp?message=" + status);
            rd.include(request, response);
        }
    }
}

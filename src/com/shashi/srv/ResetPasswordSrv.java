package com.shashi.srv;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.service.impl.UserServiceImpl;

@WebServlet("/ResetPasswordSrv")
public class ResetPasswordSrv extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mobile = request.getParameter("mobile");
        String newPassword = request.getParameter("password");
        UserServiceImpl udao = new UserServiceImpl();
        String status = "";

        boolean isUpdated = udao.updatePassword(mobile, newPassword); // Assume this method updates the password in the database

        if (isUpdated) {
            status = "Password reset successfully. Please login with your new password.";
            response.sendRedirect("login.jsp?message=" + status);
        } else {
            status = "Failed to reset password. Please try again.";
            RequestDispatcher rd = request.getRequestDispatcher("resetPassword.jsp?message=" + status);
            rd.include(request, response);
        }
    }
}

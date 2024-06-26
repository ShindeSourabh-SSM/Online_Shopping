package com.shashi.srv;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.UserBean;
import com.shashi.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RegisterSrv
 */
@WebServlet("/RegisterSrv")
public class RegisterSrv extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String userName = request.getParameter("username");
        String mobileNoString = request.getParameter("mobile");
        String emailId = request.getParameter("email");
        String address = request.getParameter("address");
        String pinCodeString = request.getParameter("pincode");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        String status = "";

        if (userName.isEmpty() || mobileNoString.isEmpty() || emailId.isEmpty() || address.isEmpty()
                || pinCodeString.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            status = "All fields are required!";
        } else {
            try {
                Long mobileNo = Long.parseLong(mobileNoString);
                int pinCode = Integer.parseInt(pinCodeString);

                if (!password.equals(confirmPassword)) {
                    status = "Password does not match!";
                } else {
                    UserBean user = new UserBean(userName, mobileNo, emailId, address, pinCode, password);
                    UserServiceImpl dao = new UserServiceImpl();
                    status = dao.registerUser(user);
                }
            } catch (NumberFormatException e) {
                status = "Invalid mobile number or pin code!";
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("register.jsp?message=" + status);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

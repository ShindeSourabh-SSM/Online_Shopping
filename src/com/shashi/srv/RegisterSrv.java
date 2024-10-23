package com.shashi.srv;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.UserBean;
import com.shashi.service.impl.UserServiceImpl;

@WebServlet("/RegisterSrv")
public class RegisterSrv extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        String userName = request.getParameter("username");
        String mobileNo = request.getParameter("mobile");
        String emailId = request.getParameter("email");
        String address = request.getParameter("address");
        int pinCode = Integer.parseInt(request.getParameter("pincode"));
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String status = "";

        // Extract the first word of the username
        String firstWordOfName = getFirstWord(userName);

        // Validate mobile number (10 digits)
        if (!isValidMobileNumber(mobileNo)) {
            status = "Invalid mobile number. Please enter a 10-digit number.";
        } 
        // Validate password complexity
        else if (!isValidPassword(password)) {
            status = "Invalid password. Password must contain at least one uppercase letter, one digit, one special character, and be at least 8 characters long.";
        }
        // Validate password does not contain first word of username
        else if (password.toLowerCase().contains(firstWordOfName.toLowerCase())) {
            status = "Password should not contain your first name.";
        }
        // Check if passwords match
        else if (!password.equals(confirmPassword)) {
            status = "Password and confirm password do not match!";
        } 
        else {
            UserBean user = new UserBean(userName, Long.parseLong(mobileNo), emailId, address, pinCode, password);
            UserServiceImpl dao = new UserServiceImpl();
            status = dao.registerUser(user);
        }

        RequestDispatcher rd = request.getRequestDispatcher("register.jsp?message=" + status);
        rd.forward(request, response);
    }

    // Mobile number validation: 10 digits only
    private boolean isValidMobileNumber(String mobileNo) {
        return mobileNo != null && mobileNo.matches("\\d{10}");
    }

    // Password validation: At least one uppercase letter, one digit, one special character, and minimum 8 characters
    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    // Extract the first word of the username
    private String getFirstWord(String userName) {
        if (userName != null && !userName.isEmpty()) {
            String[] nameParts = userName.split("\\s+");
            return nameParts[0];  // Return the first word
        }
        return "";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

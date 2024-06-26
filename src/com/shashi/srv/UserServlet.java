package com.shashi.srv;

import java.io.IOException;
import java.sql.SQLDataException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.UserBean;
import com.shashi.service.UserService;
import com.shashi.service.impl.UserServiceImpl;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertUser(request, response);
                break;
            case "/delete":
                deleteUser(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateUser(request, response);
                break;
            default:
                listUsers(request, response);
                break;
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("users.jsp");
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the user details from the form
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        // Retrieve other user details as needed

        // Create a new UserBean object with the retrieved details
        UserBean user = new UserBean(name, email);
        // Set other user details as needed

        // Call the UserService to insert the user
        userService.registerUser(user);

        // Redirect to the user list page
        response.sendRedirect("users");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the user ID from the request parameters
        int userId = Integer.parseInt(request.getParameter("id"));

        // Call the UserService to delete the user
        userService.deleteUser(userId);

        // Redirect to the user list page
        response.sendRedirect("users");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the user ID from the request parameters
        int userId = Integer.parseInt(request.getParameter("id"));

        // Call the UserService to get the user details
        UserBean user = userService.getUserById(userId);

        // Set the user object as an attribute in the request
        request.setAttribute("user", user);

        // Forward to the user edit form
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-user.jsp");
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the user ID from the request parameters
        int userId = Integer.parseInt(request.getParameter("id"));

        // Retrieve the updated user details from the form
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        // Retrieve other user details as needed

        // Create a new UserBean object with the updated details
        UserBean updatedUser = new UserBean(userId, name, email);
        // Set other user details as needed

        // Call the UserService to update the user
        userService.updateUser(updatedUser);

        // Redirect to the user list page
        response.sendRedirect("users");
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Call the UserService to get the list of users
        List<UserBean> userList = userService.getAllUsers();

        // Set the user list as an attribute in the request
        request.setAttribute("userList", userList);

        // Forward to the user list page
        RequestDispatcher dispatcher = request.getRequestDispatcher("users.jsp");
        dispatcher.forward(request, response);
    }
}

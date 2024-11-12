package com.uam.controller;

import com.uam.dao.UserDao;
import com.uam.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Process the HTTP POST request for user registration
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user details from the sign-up form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Default role for new users is "Employee"
        String role = "Employee";

        // Create a User object to hold the user data
        User user = new User(0, username, password, role);

        // Instantiate UserDao to interact with the database
        UserDao userDao = new UserDao();

        // Call registerUser method to insert the new user into the database
        boolean isRegistered = userDao.registerUser(user);

        // Check if registration was successful
        if (isRegistered) {
            // If successful, redirect to the login page with a success message
            response.sendRedirect("login.jsp?message=Registration successful! Please log in.");
        } else {
            // If registration fails, redirect back to the sign-up page with an error message
            response.sendRedirect("signup.jsp?error=Registration failed. Please try again.");
        }
    }

    // Process the HTTP GET request for the sign-up page (optional, if you want to display the sign-up form)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the sign-up page
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
}

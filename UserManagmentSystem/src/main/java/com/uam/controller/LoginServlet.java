package com.uam.controller;

import com.uam.dao.UserDao;
import com.uam.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.authenticateUser(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if ("Admin".equals(user.getRole())) {
                response.sendRedirect("createSoftware.jsp");
            } else if ("Manager".equals(user.getRole())) {
                response.sendRedirect("pendingRequests.jsp");
            } else {
                response.sendRedirect("requestAccess.jsp");
            }
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
}

package com.uam.controller;

import com.uam.dao.SoftwareDao;
import com.uam.model.Software;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/createSoftware")
public class SoftwareServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String accessLevels = request.getParameter("accessLevels");

        Software software = new Software(name, description, accessLevels);
        SoftwareDao softwareDao = new SoftwareDao();

        boolean isSuccess = softwareDao.addSoftware(software);

        if (isSuccess) {
            response.sendRedirect("createSoftware.jsp?success=Software added successfully");
        } else {
            response.sendRedirect("createSoftware.jsp?error=Failed to add software");
        }
    }
}

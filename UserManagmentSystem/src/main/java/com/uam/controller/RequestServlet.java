package com.uam.controller;

import com.uam.dao.RequestDao;
import com.uam.model.Request;
import com.uam.model.User;
import com.uam.model.Software;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/requestAccess")
public class RequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int softwareId = Integer.parseInt(request.getParameter("softwareId"));
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Request accessRequest = new Request(user.getId(), softwareId, accessType, reason);
        RequestDao requestDao = new RequestDao();

        boolean isSuccess = requestDao.submitRequest(accessRequest);

        if (isSuccess) {
            response.sendRedirect("requestAccess.jsp?success=Request submitted");
        } else {
            response.sendRedirect("requestAccess.jsp?error=Failed to submit request");
        }
    }
}

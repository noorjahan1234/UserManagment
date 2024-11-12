package com.uam.controller;

import com.uam.dao.RequestDao;
import com.uam.model.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/approveRequest")
public class ApprovalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
       // int id = Integer.parseInt(request.getParameter("id"));

        String status = request.getParameter("status");

        RequestDao requestDao = new RequestDao();
        boolean isSuccess = requestDao.updateRequestStatus(requestId, status);
        
        if (isSuccess) {
            response.sendRedirect("pendingRequests.jsp?success=Request updated");
        } else {
            response.sendRedirect("pendingRequests.jsp?error=Failed to update request");
        }
    }
}

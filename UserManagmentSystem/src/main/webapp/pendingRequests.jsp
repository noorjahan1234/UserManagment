<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pending Access Requests</title>
</head>
<body>
    <h2>Pending Access Requests</h2>

    <!-- Display messages (Success or Error) -->
    <c:if test="${param.success != null}">
        <div style="color: green;">${param.success}</div>
    </c:if>
    <c:if test="${param.error != null}">
        <div style="color: red;">${param.error}</div>
    </c:if>

    <!-- Table to show pending requests -->
    <table border="1">
        <thead>
            <tr>
                <th>Employee Name</th>
                <th>Software Name</th>
                <th>Access Type</th>
                <th>Reason</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through all pending requests -->
            <c:forEach var="request" items="${requests}">
    <tr>
        <td>${request.username}</td>  <!-- Assuming request has a 'username' property -->
        <td>${request.software.name}</td>
        <td>${request.accessType}</td>
        <td>${request.reason}</td>
        <td>${request.status}</td>
        <td>
            <form action="approveRequest" method="post">
                <input type="hidden" name="requestId" value="${request.id}">  <!-- Ensure this is the correct value -->
                <button type="submit" name="status" value="Approved">Approve</button>
                <button type="submit" name="status" value="Rejected">Reject</button>
            </form>
        </td>
    </tr>
</c:forEach>
        </tbody>
    </table>

    <br><br>
    <a href="logout.jsp">Logout</a>
</body>
</html>

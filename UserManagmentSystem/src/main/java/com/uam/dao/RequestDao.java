package com.uam.dao;

import com.uam.model.AccessRequest;
import com.uam.model.Request;
import com.uam.model.User;
import com.uam.model.Software;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {
	 private static final String URL = "jdbc:mysql://localhost:3066/userSystem";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Noor1234@";

    // Method to create a new access request
    public boolean createRequest(AccessRequest request) {
        String query = "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, request.getUser().getId()); // User ID
            stmt.setInt(2, request.getSoftware().getId()); // Software ID
            stmt.setString(3, request.getAccessType()); // Access Type
            stmt.setString(4, request.getReason()); // Reason
            stmt.setString(5, request.getStatus()); // Status (Pending by default)

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the request was successfully created
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Returns false if there was an error
    }

    // Method to get all requests for a specific user (Employee)
    public List<AccessRequest> getRequestsByUser(User user) {
        String query = "SELECT * FROM requests WHERE user_id = ?";
        List<AccessRequest> requests = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, user.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String accessType = rs.getString("access_type");
                String reason = rs.getString("reason");
                String status = rs.getString("status");

                int softwareId = rs.getInt("software_id");
                Software software = getSoftwareById(softwareId); // Retrieve associated software

                AccessRequest request = new AccessRequest(id, user, software, accessType, reason, status);
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests; // Returns the list of access requests for the user
    }

    // Method to get all pending requests (Manager functionality)
    public List<AccessRequest> getPendingRequests() {
        String query = "SELECT * FROM requests WHERE status = 'Pending'";
        List<AccessRequest> requests = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                int softwareId = rs.getInt("software_id");
                String accessType = rs.getString("access_type");
                String reason = rs.getString("reason");
                String status = rs.getString("status");

                User user = getUserById(userId); // Retrieve associated user
                Software software = getSoftwareById(softwareId); // Retrieve associated software

                AccessRequest request = new AccessRequest(id, user, software, accessType, reason, status);
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests; // Returns the list of pending access requests
    }

    // Method to get a request by its ID
    public AccessRequest getRequestById(int id) {
        String query = "SELECT * FROM requests WHERE id = ?";
        AccessRequest request = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                int softwareId = rs.getInt("software_id");
                String accessType = rs.getString("access_type");
                String reason = rs.getString("reason");
                String status = rs.getString("status");

                User user = getUserById(userId); // Retrieve associated user
                Software software = getSoftwareById(softwareId); // Retrieve associated software

                request = new AccessRequest(id, user, software, accessType, reason, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return request; // Returns the access request if found, or null if not found
    }

    // Method to approve or reject a request
    public boolean updateRequestStatus(int requestId, String status) {
        String query = "UPDATE requests SET status = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, status);
            stmt.setInt(2, requestId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the status update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Returns false if there was an error
    }

    // Method to get a User object by ID
    private User getUserById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                user = new User(id, username, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user; // Returns the user object if found, or null if not found
    }

    // Method to get a Software object by ID
    private Software getSoftwareById(int id) {
        String query = "SELECT * FROM software WHERE id = ?";
        Software software = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                String accessLevels = rs.getString("access_levels");
                software = new Software(id, name, description, accessLevels);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return software; // Returns the software object if found, or null if not found
    }

	public boolean submitRequest(Request accessRequest) {
		// TODO Auto-generated method stub
		return false;
	}
}

package com.uam.dao;

import com.uam.model.Software;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoftwareDao {
	 private static final String URL = "jdbc:mysql://localhost:3066/userSystem";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Noor1234@";

    // Method to add a new software record to the database
    public boolean addSoftware(Software software) {
        String query = "INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, software.getName());
            stmt.setString(2, software.getDescription());
            stmt.setString(3, software.getAccessLevels());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Returns false if there was an error
    }

    // Method to get a list of all software in the system
    public List<Software> getAllSoftware() {
        List<Software> softwareList = new ArrayList<>();
        String query = "SELECT * FROM software";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String accessLevels = rs.getString("access_levels");

                Software software = new Software(id, name, description, accessLevels);
                softwareList.add(software);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return softwareList; // Returns the list of software objects
    }

    // Method to get a software by its ID
    public Software getSoftwareById(int id) {
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

        return software; // Returns the software object or null if not found
    }

    // Method to update an existing software record
    public boolean updateSoftware(Software software) {
        String query = "UPDATE software SET name = ?, description = ?, access_levels = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, software.getName());
            stmt.setString(2, software.getDescription());
            stmt.setString(3, software.getAccessLevels());
            stmt.setInt(4, software.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Returns false if there was an error
    }

    // Method to delete a software record by ID
    public boolean deleteSoftware(int id) {
        String query = "DELETE FROM software WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the deletion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Returns false if there was an error
    }
}

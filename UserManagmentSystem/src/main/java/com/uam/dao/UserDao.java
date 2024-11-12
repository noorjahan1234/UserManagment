package com.uam.dao;

import com.uam.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String URL = "jdbc:mysql://localhost:3066/userSystem";
    private static final String USER = "root";
    private static final String PASSWORD = "Noor1234@";

    // Method to register a new user (sign-up)
    public boolean registerUser(User user) {
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the user was successfully registered
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Returns false if there was an error
    }

    // Method to authenticate a user (login)
    public User authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        User user = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String usernameFromDb = rs.getString("username");
                String passwordFromDb = rs.getString("password");
                String role = rs.getString("role");

                user = new User(id, usernameFromDb, passwordFromDb, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user; // Returns the User object if authenticated, or null if credentials are invalid
    }

    // Method to get a user by their ID
    public User getUserById(int id) {
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

    // Method to get all users in the system (for admin)
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                User user = new User(id, username, password, role);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users; // Returns the list of all users
    }

    // Method to update a user's role
    public boolean updateUserRole(int id, String newRole) {
        String query = "UPDATE users SET role = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, newRole);
            stmt.setInt(2, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the role update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Returns false if there was an error
    }

    // Method to delete a user by ID (admin action)
    public boolean deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the user was successfully deleted
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Returns false if there was an error
    }
}

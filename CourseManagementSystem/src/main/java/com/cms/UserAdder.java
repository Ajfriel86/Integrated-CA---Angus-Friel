package com.cms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A utility class for adding new users to the course management system.
 * It checks for the uniqueness of the username before adding the user to the database.
 */
public class UserAdder {
    /**
     * Adds a new user to the database.
     * 
     * @param name     The name of the user.
     * @param email    The email of the user.
     * @param password The password for the user (plaintext, but should ideally be hashed).
     * @param userName The desired username for the user account.
     * @param role     The role of the user (e.g., ADMIN, OFFICE, LECTURER).
     */
    public static void addUser(String name, String email, String password, String userName, UserRole role) {
        Connection conn = null;
        try {
            // Establish database connection
            conn = DatabaseConnection.getConnection();
            // Query to check if the username is already taken
            String checkUserQuery = "SELECT COUNT(*) FROM staff WHERE UserName = ?";
            try (PreparedStatement checkPstmt = conn.prepareStatement(checkUserQuery)) {
                checkPstmt.setString(1, userName);
                ResultSet rs = checkPstmt.executeQuery();
                // If username exists, exit the method and inform the user
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("The username \"" + userName + "\" is already taken. Please choose a different username.");
                    return;
                }
            }
            
            // Query to insert the new user into the database
            String query = "INSERT INTO staff (Name, Email, Password, UserName, Role) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                // Set parameters for the insert statement
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, password); // Note: Password should ideally be hashed
                pstmt.setString(4, userName);
                pstmt.setString(5, role.toString());
                // Execute the update and check if a row was affected
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("User added successfully.");
                } else {
                    System.out.println("No rows affected. User might not have been added.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to add user: " + e.getMessage());
        } finally {
            // Ensure the database connection is closed
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close connection: " + e.getMessage());
                }
            }
        }
    }
}

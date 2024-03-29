package com.cms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;

/**
 * Provides functionality to view details of all users in the system.
 */
public class UserViewer {

    /**
     * Displays information for all users stored in the database.
     * This method queries the staff table and prints out details of each user,
     * including their ID, name, email, username, and role.
     * 
     * @param reader BufferedReader to read input from the console if needed.
     */
    public static void viewAllUsers(BufferedReader reader) {
        // SQL query to select user details from the Staff table
        String query = "SELECT StaffUserID, Name, Email, UserName, Role FROM Staff";
        try (
            // Establish a connection to the database
            Connection conn = DatabaseConnection.getConnection();
            // Prepare the SQL statement for execution
            PreparedStatement stmt = conn.prepareStatement(query);
            // Execute the query and store the result set
            ResultSet rs = stmt.executeQuery()) {
            
            // Iterate over each row in the result set
            while (rs.next()) {
                // Extract user details from the current row
                int id = rs.getInt("StaffUserID");
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                String userName = rs.getString("UserName");
                String role = rs.getString("Role");
                
                // Handle cases where the role might be null
                if (role == null) {
                    role = "No role assigned"; // Default text if no role is assigned
                }
                
                // Print the user details
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Username: " + userName + ", Role: " + role);
            }
        } catch (SQLException e) {
            // Print the stack trace for any SQL exceptions encountered
            e.printStackTrace();
        }
    }
}

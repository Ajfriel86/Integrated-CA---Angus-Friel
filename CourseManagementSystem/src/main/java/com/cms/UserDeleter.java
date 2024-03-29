package com.cms;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Facilitates the deletion of user records from the database.
 * It includes a confirmation step to prevent accidental deletions.
 */
public class UserDeleter {
    /**
     * Prompts for confirmation and deletes a user from the database if confirmed.
     * 
     * @param staffUserID The unique ID of the staff member to delete.
     * @param reader      A BufferedReader to read the confirmation input from the console.
     */
    public static void deleteUser(int staffUserID, BufferedReader reader) {
        // Prompt the user for confirmation to delete the user
        System.out.println("Are you sure you want to permanently delete this user? (yes/no)");
        try {
            String confirmation = reader.readLine().toLowerCase(); // Read and normalize the confirmation response

            if (!confirmation.equals("yes")) { // Check if the user confirmed the deletion
                System.out.println("Deletion aborted."); // Abort if the response is not affirmative
                return;
            }

            // SQL query to delete the user with the specified ID
            String query = "DELETE FROM staff WHERE StaffUserID = ?";
            try (
                // Establish a database connection
                Connection conn = DatabaseConnection.getConnection();
                // Prepare the SQL statement with the provided user ID
                PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setInt(1, staffUserID); // Set the user ID in the query
                // Execute the query and get the number of affected rows
                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) { // Check if the user was successfully deleted
                    System.out.println("User deleted successfully.");
                } else { // Handle cases where the user does not exist
                    System.out.println("No rows affected. User might not exist.");
                }
            } catch (SQLException e) {
                System.err.println("Failed to delete user: " + e.getMessage()); // Log SQL exceptions
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage()); // Log input/output exceptions
        }
    }
}

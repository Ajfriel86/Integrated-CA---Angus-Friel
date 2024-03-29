package com.cms;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordManager {

    /**
     * Changes the password for a specific user, identified by their username.
     * This method can be used by administrators to update the password of any user in the system.
     * 
     * @param userName The username of the user whose password is to be changed.
     * @param newPassword The new password that will be set for the user.
     */
    public static void changePassword(String userName, String newPassword) {
        // SQL query to update the user's password in the database
        String query = "UPDATE staff SET Password = ? WHERE UserName = ?";
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain a database connection
             PreparedStatement pstmt = conn.prepareStatement(query)) { // Prepare the SQL statement

                pstmt.setString(1, newPassword); // Set the new password in the query
                pstmt.setString(2, userName); // Set the username in the query
    
                int affectedRows = pstmt.executeUpdate(); // Execute the update operation
    
            if (affectedRows > 0) {
                System.out.println("Password changed successfully for user: " + userName);
            } else {
                System.out.println("Failed to change password. User may not exist.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to change password: " + e.getMessage());
        }
    }

    /**
     * Allows the current user to change their own password, after successfully verifying their existing password.
     * 
     * @param currentUser The current user attempting to change their password.
     * @param reader A BufferedReader to read the user's input from the console.
     */
    public static void changeOwnPassword(User currentUser, BufferedReader reader) {
        System.out.print("Enter your current password: ");
        try {
                String currentPassword = reader.readLine(); // Read the current password

                System.out.print("Enter new password: "); // Prompt for new password
                String newPassword = reader.readLine(); // Read the new password


                if (verifyCurrentPassword(currentUser.getUsername(), currentPassword)) { // Verify current password
                    changePassword(currentUser.getUsername(), newPassword); // Change to new password if current password is verified
                    ScreenClearer.clearScreen(); // Clear the console screen for clean output
                    System.out.println("Password successfully changed.");
                    // Prompt to return to the previous menu or exit the application
                    System.out.println("Press Enter to return to the previous menu or type 'exit' to quit.");
                    String input = reader.readLine();
                    if (input.equalsIgnoreCase("exit")) {
                        System.out.println("Exiting...");
                        System.exit(0); // Exit the application
                    }
                } else {
                    System.out.println("The current password is incorrect."); // Inform user of incorrect current password
                }
            } catch (IOException e) {
                System.err.println("Error reading input: " + e.getMessage());
            }
        }
    
    /**
     * Changes the password for the user identified by their ID.
     * @param userId The ID of the user whose password is to be changed.
     * @param newPassword The new password that will replace the old one.
     * @param reader The BufferedReader instance for reading user input.
     */
    public static void changeUserPassword(int userId, String newPassword, BufferedReader reader) {
         // SQL query to update the user's password in the database
        String query = "UPDATE staff SET Password = ? WHERE StaffUserID = ?";

        try (Connection conn = DatabaseConnection.getConnection(); // Obtain a database connection
                PreparedStatement pstmt = conn.prepareStatement(query)) { // Prepare the SQL statement

                pstmt.setString(1, newPassword); // Set the password in the query
                pstmt.setInt(2, userId); // Set the ID in the query

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    // Prints a success message
                    System.out.println("Password changed successfully for user with ID: " + userId);
                } else {
                    // Error handling
                    System.out.println("Failed to change password. User with ID: " + userId + " may not exist.");
                }
                // Error handling for SQL statement
        } catch (SQLException e) {
            System.err.println("Failed to change password: " + e.getMessage());
        }
    }

    /**
     * Verifies that the provided password matches the current password stored in the database for a given username.
     * 
     * @param userName The username for which the password needs to be verified.
     * @param password The password to be verified.
     * @return true if the provided password matches the stored password; false otherwise.
     */
    private static boolean verifyCurrentPassword(String userName, String password) {
        String query = "SELECT Password FROM staff WHERE UserName = ?";
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain a database connection
             PreparedStatement pstmt = conn.prepareStatement(query)) { // Prepare the SQL statement

            pstmt.setString(1, userName); // Set the username in the query
            try (ResultSet rs = pstmt.executeQuery()) { // Execute the query
                if (rs.next()) {
                    String currentPassword = rs.getString("Password");
                    return password.equals(currentPassword); // Return true if passwords match
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to verify password: " + e.getMessage());
        }
        return false; // Return false if no matching record found
    }

    /**
     * Prompts an administrator to change the password of any user in the system.
     * Other users can only change their own password.
     * 
     * @param currentUser The current logged-in user initiating the password change.
     * @param reader A BufferedReader to read the administrator's or user's input from the console.
     */
    public static void promptChangePassword(User currentUser, BufferedReader reader) {
        if (currentUser.getRole() == UserRole.ADMIN) {
            System.out.print("Enter username for password change: "); // Prompt for the username whose password is to be changed
            try {
                String userName = reader.readLine(); // Read the username

                if (!userExists(userName)) {
                    // Check if user exists
                    System.out.println("User does not exist.");
                    return;
                }

                System.out.print("Enter new password: "); // Prompt for new password
                String newPassword = reader.readLine(); // Read the new password

                changePassword(userName, newPassword); // Change the password
                System.out.println("Password changed successfully for user: " + userName);
            } catch (IOException e) {
                System.err.println("Error reading input: " + e.getMessage());
            }
        } else {
            changeOwnPassword(currentUser, reader); // Non-admin users can change their own password
        }
    }

    /**
     * Checks if a user exists in the database based on the provided username.
     * 
     * @param userName The username to check in the database.
     * @return true if the user exists; false otherwise.
     */
    private static boolean userExists(String userName) {
        String query = "SELECT COUNT(*) FROM staff WHERE UserName = ?";
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain a database connection
             PreparedStatement pstmt = conn.prepareStatement(query)) { // Prepare the SQL statement

            pstmt.setString(1, userName); // Set the username in the query
            ResultSet rs = pstmt.executeQuery(); // Execute the query
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // User exists
            }
        } catch (SQLException e) {
            System.err.println("Database query failed: " + e.getMessage());
        }
        return false; // User does not exist
    }
}

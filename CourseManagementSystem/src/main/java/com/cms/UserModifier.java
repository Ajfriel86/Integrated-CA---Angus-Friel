package com.cms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Facilitates the modification of existing user records in the database.
 * Allows for updating user details such as name, email, password, and username.
 */
public class UserModifier {

    /**
     * Modifies an existing user's details in the database.
     * 
     * @param staffUserID The unique identifier of the user to be modified.
     * @param name        The new name to be assigned to the user.
     * @param email       The new email address for the user.
     * @param password    The new password for the user's account.
     * @param userName    The new username for the user's account.
     */
    public static void modifyUser(int staffUserID, String name, String email, String password, String userName) {
        // SQL query to update user details in the staff table
        String query = "UPDATE staff SET Name = ?, Email = ?, Password = ?, UserName = ? WHERE StaffUserID = ?";
        try (
            // Establish a connection to the database
            Connection conn = DatabaseConnection.getConnection();
            // Prepare the SQL statement for execution
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Set the parameters for the prepared statement
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password); // Note: In a real application, passwords should be securely hashed
            pstmt.setString(4, userName);
            pstmt.setInt(5, staffUserID);
            // Execute the update and check how many rows were affected
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("User modified successfully.");
            } else {
                System.out.println("No rows affected. User might not exist or data is unchanged.");
            }
        } catch (SQLException e) {
            // Log any SQL exceptions that occur
            System.err.println("Failed to modify user: " + e.getMessage());
        }
    }
}

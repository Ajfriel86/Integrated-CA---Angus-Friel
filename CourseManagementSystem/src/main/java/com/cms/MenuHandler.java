// Instructions for starting the project:
// Start project my running: mvn clean package
// Then run: java -jar target/myproject-1.0-SNAPSHOT-jar-with-dependencies.jar

package com.cms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuHandler {
    // Static variable to hold the current user's session after login
    private static User currentUser;

    /**
     * Main entry point of the program.
     * Initializes the program and handles the login process.
     * If login is successful, it proceeds to run the program based on the user's role.
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Set up a BufferedReader to read from the system input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Attempt to perform login with the provided credentials
        if (performLogin(reader)) { 
            // On successful login, run the program based on the role of the logged-in user
            runProgramBasedOnRole(reader); 
        } else {
            // If login is unsuccessful, print an error message
            System.out.println("Invalid login details.");
        }
    }

    /**
     * Handles the login functionality.
     * Prompts the user for username and password, and verifies them against the database.
     * @param reader BufferedReader to read user input from the console.
     * @return true if login is successful, false otherwise.
     */
    private static boolean performLogin(BufferedReader reader) {
        System.out.println("Enter login details:");
        try {
            // Prompt for username
            System.out.print("Username: ");
            String username = reader.readLine(); 
            // Prompt for password
            System.out.print("Password: ");
            String password = reader.readLine();

            // SQL query to check if the provided credentials exist in the database
            String query = "SELECT Name, Role FROM staff WHERE UserName = ? AND Password = ?";

            // Attempt to establish a connection to the database and prepare the SQL statement
            try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

                // Bind the username and password to the query parameters
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                // Check if a record is found
                if (rs.next()) {
                    // Extract user details from the result set
                    String name = rs.getString("Name");
                    UserRole role = UserRole.valueOf(rs.getString("Role").toUpperCase());
                    // Create a new User object to represent the current user
                    currentUser = new User(username, password, role, name);
                    return true; // Login successful
                } else {
                    return false; // Login failed
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error verifying login details.");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading input.");
            return false;
        }
    }

    /**
     * Determines and runs the appropriate menu based on the role of the logged-in user.
     * @param reader BufferedReader to read user input from the console.
     */
    private static void runProgramBasedOnRole(BufferedReader reader) {
        // Check if a user is logged in
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        // Execute different actions based on the user's role
        switch (currentUser.getRole()) {
            case ADMIN:
                AdminMenu.displayAdminMenu(currentUser, reader); // Display the admin menu
                break;
            case OFFICE:
                OfficeMenu.displayOfficeMenu(currentUser, reader); // Display the office staff menu
                break;
            case LECTURER:
                LecturerMenu.displayLecturerMenu(currentUser, reader); // Display the lecturer menu
                break;
            default:
                System.out.println("Invalid role. Exiting program..."); // Handle unknown roles
                break;
        }
    }
}
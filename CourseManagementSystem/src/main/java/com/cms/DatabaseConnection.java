package com.cms;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the database connection for the Course Management System.
 * This class provides methods to establish and close connections to the database.
 */
public class DatabaseConnection {

    /**
     * Establishes a connection to the database.
     * 
     * @return A Connection object to the database.
     */
    public static Connection getConnection() {
        // Defines the database connection URL, including the database name, 
        // the use of SSL, and public key retrieval settings
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/course_management_system?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "admin";

        // Attempts to establish a connection to the database using the provided URL, user name, and password.
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");
        } 
        
        // Catches and logs SQL exceptions related to the database connection attempt.
        catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
        
        // Returns the established database connection, or null if the connection attempt failed.
        return conn;
    }
    
    /**
     * A simple main method to test the database connection functionality.
     * It attempts to establish a connection to the database and then closes it.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = getConnection();
            if (conn != null) {
                System.out.println("Using the database connection for operations.");
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close(); 
                    System.out.println("Disconnected from the database.");
                } catch (SQLException e) {
                    System.err.println("Failed to close the database connection.");
                    e.printStackTrace();
                }
            }
        }
    }
}

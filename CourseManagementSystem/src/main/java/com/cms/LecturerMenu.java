package com.cms;

import java.io.BufferedReader;
import java.io.IOException;

public class LecturerMenu {

    /**
     * Displays the menu options for a lecturer and handles user input accordingly.
     * @param currentUser The current user logged in.
     * @param reader The BufferedReader instance for reading user input.
     */
    public static void displayLecturerMenu(User currentUser, BufferedReader reader) {
        
        // Keeps the session alive
        boolean keepRunning = true;
        
        // Main loop to keep showing the menu until the user decides to exit 
        while (keepRunning) {
            ScreenClearer.clearScreen(); // Clears the console screen for better readability
            
            // Welcome message personalized with the current user's name
            System.out.println("Welcome, " + currentUser.getName() + "!");
            
            // Displaying the options available for the lecturer
            System.out.println("Lecturer Menu:");
            System.out.println("1. Generate Lecturer Report (CSV)");
            System.out.println("2. Generate Lecturer Report (TXT)");
            System.out.println("3. Generate Lecturer Report (DOC)");
            System.out.println("4. Change Password");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
    
            try {
                String input = reader.readLine(); // Reads the user input
                // Checking if the input is a valid integer
                if (input.matches("\\d+")) {
                    int choice = Integer.parseInt(input);
                    // Switch statement to handle different choices
                    switch (choice) {
                        case 1:
                            LecturerReport.generateReport("csv"); // Generate CSV report
                            break;
                        case 2:
                            LecturerReport.generateReport("txt"); // Generate TXT report
                            break;
                        case 3:
                            LecturerReport.generateReport("doc"); // Generate DOC report
                            break;
                        case 4:
                            // Invoking the change password procedure
                            PasswordManager.promptChangePassword(currentUser, reader);
                            break;
                        case 5:
                            // Exit the application
                            System.out.println("Exiting...");
                            System.exit(0);
                            break;
                        default:
                            // Handling invalid choices
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                } else {
                    System.out.println("Please enter a valid integer.");
                }
            } catch (IOException e) {
                // Handling I/O errors
                System.err.println("Error reading input: " + e.getMessage());
            } catch (NumberFormatException e) {
                // Handling invalid number formats
                System.out.println("Invalid choice. Please enter a valid integer.");
            }
        }
    }
    /**
     * Prompts the user to select the format for generating a lecturer report.
     * This method provides an alternate way of generating reports, not used in the main menu.
     * @param reader The BufferedReader instance for reading user input.
     */
    public static void promptReportFormat(BufferedReader reader) {
        System.out.println("Select the report format: (1) CSV (2) TXT (3) DOC");
        try {
            String input = reader.readLine(); // Read input using BufferedReader
            int choice = Integer.parseInt(input); // Convert the input to an integer
            // Handling the choice made by the user
            switch (choice) {
                case 1:
                    LecturerReport.generateLecturerReportCSV(); // Generate CSV format report
                    break;
                case 2:
                    LecturerReport.generateLecturerReportTXT(); // Generate TXT format report
                    break;
                case 3:
                    LecturerReport.generateLecturerReportDOC(); // Generate DOC format report
                    break;
                default:
                    // Handling invalid choices
                    System.out.println("Invalid choice.");
                    break;
            }
        } catch (IOException e) {
            // Handling I/O exceptions
            System.err.println("Error reading input: " + e.getMessage());
        } catch (NumberFormatException e) {
            // Handling number format exceptions
            System.out.println("Invalid choice. Please enter a valid integer.");
        }
    }
}

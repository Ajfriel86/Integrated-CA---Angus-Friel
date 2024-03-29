package com.cms;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Handles the display and interaction of the Office Menu.
 * Provides options to generate various reports and manage account settings.
 */
public class OfficeMenu {

    /**
     * Displays the menu options specific to office users.
     * Allows the user to select an action such as generating reports or changing passwords.
     * @param currentUser The user currently logged into the system.
     * @param reader A BufferedReader to read the user's input from the console.
     */
    public static void displayOfficeMenu(User currentUser, BufferedReader reader) {
        boolean keepRunning = true; // Flag to keep the menu running

        while (keepRunning) {
            ScreenClearer.clearScreen(); // Clears the screen for better readability
            // Welcome message personalized with the current user's name
            System.out.println("Welcome, " + currentUser.getName() + "!");
            // Displaying the menu options available to the office user
            System.out.println("Office Menu:");
            System.out.println("1. Generate Course Report");
            System.out.println("2. Generate Student Report");
            System.out.println("3. Generate Lecturer Report");
            System.out.println("4. Change Password");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                String input = reader.readLine(); // Read the user's choice from the console
                int choice = Integer.parseInt(input); // Convert the input to an integer
                switch (choice) {
                    case 1:
                        CourseReport.promptReportFormat(reader); // Prompt for course report format
                        break;
                    case 2:
                        StudentReport.promptReportFormat(reader); // Prompt for student report format
                        break;
                    case 3:
                        LecturerMenu.promptReportFormat(reader); // Prompt for lecturer report format
                        break;
                    case 4:
                        PasswordManager.changeOwnPassword(currentUser, reader); // Change the current user's password
                        break;
                    case 5: // Corrected to match the displayed option number for exiting
                        System.out.println("Exiting...");
                        System.exit(0); // Exit the program
                        break;
                    default:
                        // Handle invalid choices
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (IOException e) {
                // Handle errors reading input
                System.err.println("Error reading input: " + e.getMessage());
            } catch (NumberFormatException e) {
                // Handle invalid numeric format
                System.out.println("Invalid choice. Please enter a valid integer.");
            }
        }
    }
}

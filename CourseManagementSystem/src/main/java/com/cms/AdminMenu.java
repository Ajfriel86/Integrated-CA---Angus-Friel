package com.cms;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * The AdminMenu class provides functionality for displaying an administration menu
 * and handling various administrative actions such as user management and password changes.
 */
public class AdminMenu {

    /**
     * Displays the admin menu and handles user interactions for various administrative tasks.
     * 
     * @param currentUser The current user who has logged in and is accessing the admin menu.
     * @param reader A BufferedReader used for reading input from the console.
     */
    public static void displayAdminMenu(User currentUser, BufferedReader reader) {

        boolean keepRunning = true;
        while (keepRunning) {
            ScreenClearer.clearScreen(); // Clears the screen for a clean menu display.

            // Welcomes the current user and displays the admin menu options.
            System.out.println("Welcome, " + currentUser.getName() + "!");
            System.out.println("Admin Menu:");
            System.out.println("1. Add User");
            System.out.println("2. Modify User");
            System.out.println("3. Delete User");
            System.out.println("4. View All Users");
            System.out.println("5. Change Password");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            // Attempts to read a line of input from the console and convert it to an integer.
            // Catches two types of exceptions:
            // IOException could be thrown if an I/O error occurs during reading the input.
            // NumberFormatException could be thrown if the input text cannot be parsed into an integer.
            int choice;
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            // Processes the user's choice and calls the appropriate method.
            switch (choice) {
                // Adds a new user to the database and calls the appropriate  methods
                case 1:
                    System.out.print("Enter Name: ");
                    String name;
                    try {
                        name = reader.readLine();
                    } catch (IOException e) {
                        System.out.println("Error reading input.");
                        break;
                    }
                    System.out.print("Enter Email: ");
                    String email;
                    try {
                        email = reader.readLine();
                    } catch (IOException e) {
                        System.out.println("Error reading input.");
                        break;
                    }
                    System.out.print("Enter Password: ");
                    String password;
                    try {
                        password = reader.readLine();
                    } catch (IOException e) {
                        System.out.println("Error reading input.");
                        break;
                    }
                    System.out.print("Enter UserName: ");
                    String userName;
                    try {
                        userName = reader.readLine();
                    } catch (IOException e) {
                        System.out.println("Error reading input.");
                        break;
                    }
                    System.out.print("Enter Role (ADMIN, OFFICE, LECTURER): ");
                    String roleStr;
                    try {
                        roleStr = reader.readLine();
                    } catch (IOException e) {
                        System.out.println("Error reading input.");
                        break;
                    }
                    UserRole role;
                    try {
                        role = UserRole.valueOf(roleStr.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid role. Please enter a valid role: ADMIN, OFFICE, LECTURER");
                        break;
                    }
                    UserAdder.addUser(name, email, password, userName, role);
                    break;
                    // Modifies a user on the database and calls the appropriate  methods
                case 2:
                    System.out.print("Enter StaffUserID to Modify: ");
                    int modifyId;
                    try {
                        modifyId = Integer.parseInt(reader.readLine());
                    } catch (IOException | NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        break;
                    }
                    System.out.print("Enter New Name: ");
                    String newName;
                    try {
                        newName = reader.readLine();
                    } catch (IOException e) {
                        System.out.println("Error reading input.");
                        break;
                    }
                    System.out.print("Enter New Email: ");
                    String newEmail;
                    try {
                        newEmail = reader.readLine();
                    } catch (IOException e) {
                        System.out.println("Error reading input.");
                        break;
                    }
                    System.out.print("Enter New Password: ");
                    String newPassword;
                    try {
                        newPassword = reader.readLine();
                    } catch (IOException e) {
                        System.out.println("Error reading input.");
                        break;
                    }
                    System.out.print("Enter New UserName: ");
                    String newUserName;
                    try {
                        newUserName = reader.readLine();
                    } catch (IOException e) {
                        System.out.println("Error reading input.");
                        break;
                    }
                    UserModifier.modifyUser(modifyId, newName, newEmail, newPassword, newUserName);
                    break;
                    // Deletes a user from the database and calls the appropriate  methods
                case 3:
                    System.out.print("Enter StaffUserID to Delete: ");
                    int deleteId;
                    try {
                        deleteId = Integer.parseInt(reader.readLine());
                    } catch (IOException | NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        break;
                    }
                    UserDeleter.deleteUser(deleteId, reader);
                    break;
                    // Views all users on the database and calls the appropriate  methods
                case 4:
                    UserViewer.viewAllUsers(reader);
                    break;
                    // Changes passwords and calls the appropriate  methods
                case 5:
                    changePasswordMenu(currentUser, reader);
                    break;
                    // Exits the menu
                case 6:
                    System.exit(0);
                    break;
                    // Error handling
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            // Pauses on the menu and waits for the user to press enter to continue
            System.out.println("Press Enter to continue...");
            // Try/Catch for error handling
            try {
                reader.readLine();
            } catch (IOException e) {
                System.out.println("Error reading input.");
            }
        }
    }

    /**
     * Displays a menu for changing passwords and handles the corresponding user interactions.
     * 
     * @param currentUser The currently logged-in user attempting to change passwords.
     * @param reader A BufferedReader used for reading input from the console.
     */
    private static void changePasswordMenu(User currentUser, BufferedReader reader) {
        ScreenClearer.clearScreen();
        System.out.println("Change Password Menu:");
        System.out.println("1. Change your Password");
        System.out.println("2. Change another user's password");
        System.out.print("Enter your choice: ");
        try {
            int choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1:
                    PasswordManager.changeOwnPassword(currentUser, reader);
                    break;
                case 2:
                    changeUserPassword(currentUser, reader);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    /**
     * Handles the password change for a specific user, ensuring that the current user has admin permissions.
     * 
     * @param currentUser The currently logged-in user attempting to change another user's password.
     * @param reader A BufferedReader used for reading input from the console.
     */
    private static void changeUserPassword(User currentUser, BufferedReader reader) {
        if (currentUser.getRole() != UserRole.ADMIN) {
            System.out.println("You do not have permission to change another user's password.");
            return;
        }
        System.out.print("Enter the user ID whose password you want to change: ");
        try {
            int userId = Integer.parseInt(reader.readLine());
            System.out.print("Enter the new password: ");
            String newPassword = reader.readLine();
            PasswordManager.changeUserPassword(userId, newPassword, reader);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid user ID.");
        }
    }
}

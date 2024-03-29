package com.cms;

/**
 * Manages the session for the current user of the system.
 * This includes tracking who is currently logged in, allowing various parts of the system
 * to access and modify the current user as needed.
 */
public class SessionManager {
    // Static variable to hold the reference to the current user logged into the system.
    private static User currentUser;

    /**
     * Retrieves the current user logged into the system.
     * This allows different parts of the application to access the user's information,
     * such as their role, name, and other credentials.
     * 
     * @return The currently logged-in User object, or null if no user is logged in.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user for the system. This method is typically called
     * during the login process to track which user is currently logged in.
     * 
     * @param user The User object representing the user who has logged in.
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}

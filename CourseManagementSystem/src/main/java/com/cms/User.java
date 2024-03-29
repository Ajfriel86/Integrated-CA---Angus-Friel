package com.cms;

/**
 * Enum defining the possible roles a user can have within the system.
 * This helps in implementing role-based access control throughout the application.
 */
enum UserRole {
    ADMIN, // Represents an administrator with full access to the system
    OFFICE, // Represents office staff with access to certain administrative functions
    LECTURER; // Represents a lecturer with access to academic and student-related functions
}

/**
 * Represents a user of the course management system.
 * This class holds information about the user's credentials, role, and personal details.
 */
class User {
    // Username used for logging into the system
    private String username;
    // Password for authentication, ideally stored in a hashed format for security
    private String password;
    // The role of the user which determines their access level within the system
    private UserRole role;
    // The name of the user for personalization and identification purposes
    private String name;

    /**
     * Constructor to create a new user instance.
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @param role The role of the user, defining their access level.
     * @param name The name of the user.
     */
    public User(String username, String password, UserRole role, String name) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    // Getter and setter methods

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

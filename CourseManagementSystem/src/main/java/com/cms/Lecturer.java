package com.cms;

/**
 * Represents a lecturer within the Course Management System.
 * It encapsulates the lecturer's details such as their ID, name, role, and specialty.
 */
public class Lecturer {
    // Sets unique identifiers
    private int id;
    private String name;
    private String role;
    private String specialty;

    /**
     * Constructs a Lecturer instance with the specified details.
     * 
     * @param id The unique identifier for the lecturer.
     * @param name The name of the lecturer.
     * @param role The role of the lecturer within the institution.
     * @param specialty The lecturer's area of expertise or specialty.
     */
    public Lecturer(int id, String name, String role, String specialty) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.specialty = specialty;
    }

    // Getter methods for accessing the properties of the Lecturer.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getSpecialty() {
        return specialty;
    }

    // Setter methods for modifying the properties of the Lecturer.
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}

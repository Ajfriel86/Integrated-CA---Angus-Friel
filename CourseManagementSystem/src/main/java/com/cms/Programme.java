package com.cms;

/**
 * Represents an academic programme within the course management system.
 * An academic programme is typically a degree or diploma course offered by a university or college.
 */
public class Programme {
    // Unique identifier for the programme
    private int id;
    // Name of the programme
    private String name;

    /**
     * Constructor to create a new Programme instance.
     * 
     * @param id The unique identifier for the programme.
     * @param name The name of the programme.
     */
    public Programme(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the unique identifier of the programme.
     * 
     * @return The id of the programme.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the programme.
     * 
     * @return The name of the programme.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the unique identifier of the programme.
     * 
     * @param id The new id to be set for the programme.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the name of the programme.
     * 
     * @param name The new name to be set for the programme.
     */
    public void setName(String name) {
        this.name = name;
    }
}

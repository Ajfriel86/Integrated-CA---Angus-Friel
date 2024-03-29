package com.cms;

/**
 * Represents a physical room within the educational institution.
 * This could include lecture halls, classrooms, labs, etc., each with its own characteristics.
 */
public class Room {
    // Unique identifier for the room
    private int id;
    // Name of the room (e.g., "Lab A", "101", etc.)
    private String name;
    // Physical location or address of the room within the campus
    private String location;
    // Maximum number of people that the room can accommodate
    private int capacity;

    /**
     * Constructor to initialize a new Room object with its details.
     * 
     * @param id The unique identifier for the room.
     * @param name The name of the room.
     * @param location The location of the room within the campus.
     * @param capacity The maximum capacity of the room.
     */
    public Room(int id, String name, String location, int capacity) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }

    // Getter methods

    /**
     * Gets the unique identifier of the room.
     * 
     * @return The id of the room.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the room.
     * 
     * @return The name of the room.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the location of the room within the campus.
     * 
     * @return The location of the room.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the maximum capacity of the room.
     * 
     * @return The capacity of the room.
     */
    public int getCapacity() {
        return capacity;
    }

    // Setter methods

    /**
     * Sets the unique identifier of the room.
     * 
     * @param id The new id to set for the room.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the name of the room.
     * 
     * @param name The new name to set for the room.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the location of the room within the campus.
     * 
     * @param location The new location to set for the room.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets the maximum capacity of the room.
     * 
     * @param capacity The new capacity to set for the room.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

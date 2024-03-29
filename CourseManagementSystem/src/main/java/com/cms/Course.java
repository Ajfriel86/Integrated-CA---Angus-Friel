package com.cms;

/**
 * Represents a course within the CMS (Course Management System).
 * It includes properties to identify the course and its associations
 * with a programme, lecturer, and room.
 */
public class Course {
    // Sets unique identifiers 
    private int id;
    private String name;
    private int programmeId;
    private int lecturerId;
    private int roomId;

    /**
     * Constructs a Course instance with specified details.
     * 
     * @param id The unique identifier for the course.
     * @param name The name of the course.
     * @param programmeId The identifier of the programme to which the course belongs.
     * @param lecturerId The identifier of the lecturer teaching the course.
     * @param roomId The identifier of the room where the course takes place.
     */
    public Course(int id, String name, int programmeId, int lecturerId, int roomId) {
        this.id = id;
        this.name = name;
        this.programmeId = programmeId;
        this.lecturerId = lecturerId;
        this.roomId = roomId;
    }

    // Getter methods for accessing the properties of the Course.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProgrammeId() {
        return programmeId;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public int getRoomId() {
        return roomId;
    }

    // Setter methods for modifying the properties of the Course.
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProgrammeId(int programmeId) {
        this.programmeId = programmeId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}

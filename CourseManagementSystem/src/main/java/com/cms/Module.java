package com.cms;

/**
 * Represents a Module in the Course Management System.
 * A module is a unit of teaching that covers a specific area of study.
 */
class Module {
    // Module name
    private String name;
    // Programme associated with this module
    private String programme;
    // Number of students enrolled in the module
    private int numOfStudents;
    // Lecturer teaching the module
    private String lecturer;
    // Room where the module is taught
    private String room;

    /**
     * Gets the name of the module.
     * @return The name of the module.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the module.
     * @param name The name to set for the module.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the programme associated with the module.
     * @return The name of the programme.
     */
    public String getProgramme() {
        return programme;
    }

    /**
     * Sets the programme associated with the module.
     * @param programme The programme to set for the module.
     */
    public void setProgramme(String programme) {
        this.programme = programme;
    }

    /**
     * Gets the number of students enrolled in the module.
     * @return The number of students.
     */
    public int getNumOfStudents() {
        return numOfStudents;
    }

    /**
     * Sets the number of students enrolled in the module.
     * @param numOfStudents The number of students to set.
     */
    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }

    /**
     * Gets the lecturer teaching the module.
     * @return The name of the lecturer.
     */
    public String getLecturer() {
        return lecturer;
    }

    /**
     * Sets the lecturer for the module.
     * @param lecturer The lecturer's name.
     */
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    /**
     * Gets the room where the module is taught.
     * @return The room.
     */
    public String getRoom() {
        return room;
    }

    /**
     * Sets the room where the module is taught.
     * @param room The room to set.
     */
    public void setRoom(String room) {
        this.room = room;
    }
}

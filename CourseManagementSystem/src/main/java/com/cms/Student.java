package com.cms;

import java.util.List;

/**
 * Represents a student within the course management system.
 * This class holds information about the student's name, student number,
 * the programme they are enrolled in, and their module enrollment status.
 */
class Student {
    // Student's name
    private String name;
    // Unique student number
    private int studentNumber;
    // Academic programme the student is enrolled in
    private String programme;
    // Modules the student is currently enrolled in
    private List<Module> enrolledModules;
    // Modules the student has completed
    private List<Module> completedModules;
    // Modules the student needs to repeat
    private List<Module> modulesToRepeat;

    // Getters and setters

    /**
     * Gets the student's name.
     * @return The name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the student's name.
     * @param name The name to set for the student.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the student number.
     * @return The student number.
     */
    public int getStudentNumber() {
        return studentNumber;
    }

    /**
     * Sets the student number.
     * @param studentNumber The student number to set.
     */
    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    /**
     * Gets the programme the student is enrolled in.
     * @return The name of the programme.
     */
    public String getProgramme() {
        return programme;
    }

    /**
     * Sets the programme the student is enrolled in.
     * @param programme The programme to set for the student.
     */
    public void setProgramme(String programme) {
        this.programme = programme;
    }

    /**
     * Gets the list of modules the student is currently enrolled in.
     * @return The list of enrolled modules.
     */
    public List<Module> getEnrolledModules() {
        return enrolledModules;
    }

    /**
     * Sets the list of modules the student is currently enrolled in.
     * @param enrolledModules The list of modules to set as enrolled.
     */
    public void setEnrolledModules(List<Module> enrolledModules) {
        this.enrolledModules = enrolledModules;
    }

    /**
     * Gets the list of modules the student has completed.
     * @return The list of completed modules.
     */
    public List<Module> getCompletedModules() {
        return completedModules;
    }

    /**
     * Sets the list of modules the student has completed.
     * @param completedModules The list of modules to set as completed.
     */
    public void setCompletedModules(List<Module> completedModules) {
        this.completedModules = completedModules;
    }

    /**
     * Gets the list of modules the student needs to repeat.
     * @return The list of modules to repeat.
     */
    public List<Module> getModulesToRepeat() {
        return modulesToRepeat;
    }

    /**
     * Sets the list of modules the student needs to repeat.
     * @param modulesToRepeat The list of modules to set as needing to be repeated.
     */
    public void setModulesToRepeat(List<Module> modulesToRepeat) {
        this.modulesToRepeat = modulesToRepeat;
    }
}

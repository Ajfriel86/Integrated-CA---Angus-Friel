package com.cms;

/**
 * Represents an enrollment record in the Course Management System.
 * Each enrollment record links a student to a specific course and grade.
 */
public class Enrollment {
    // Sets unique identifiers
    private int id;
    private int courseId;
    private int studentId;
    private int gradeId;

    /**
     * Constructs an Enrollment instance with specified details.
     * 
     * @param id The unique identifier for the enrollment.
     * @param courseId The identifier of the course associated with this enrollment.
     * @param studentId The identifier of the student associated with this enrollment.
     * @param gradeId The identifier of the grade associated with this enrollment.
     */
    public Enrollment(int id, int courseId, int studentId, int gradeId) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.gradeId = gradeId;
    }

    
    // Getter methods for accessing the properties of the Enrollment.
    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getGradeId() {
        return gradeId;
    }

    // Setter methods for modifying the properties of the Enrollment.
    public void setId(int id) {
        this.id = id;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }
}


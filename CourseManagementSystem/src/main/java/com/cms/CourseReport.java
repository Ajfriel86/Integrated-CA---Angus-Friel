package com.cms;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.BufferedReader;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import java.io.FileOutputStream;

/**
 * Generates and exports course reports in various formats (CSV, TXT, DOC) based on course data retrieved from a database.
 */
public class CourseReport {

    // SQL query to fetch course information including course name, programme name, lecturer name, room name, and number of students enrolled.
    private static final String query = "SELECT m.CourseName, p.Name AS ProgrammeName, " + 
        "l.Name AS LecturerName, " + 
        "CASE WHEN m.RoomID = 0 THEN 'Online' ELSE r.Name END AS RoomName, " + 
        "(SELECT COUNT(DISTINCT g.StudentID) " + 
        "FROM grades g WHERE g.ModuleID = m.ModuleID) AS StudentsEnrolled " + 
        "FROM module m " + 
        "LEFT JOIN programmes p ON m.ProgrammeID = p.ProgrammeID " + 
        "LEFT JOIN lecturers l ON m.LecturerID = l.LecturerID " + 
        "LEFT JOIN rooms r ON m.RoomID = r.RoomID";

    /**
    * Generates a course report in CSV format.
    */
    public static void generateCourseReportCSV() {
        String reportFilePath = Paths.get(System.getProperty("user.dir"), "CourseReport.csv").toString();
        generateReport(reportFilePath, "csv");
        System.out.println("Course report generated in CSV format. File saved at: " + reportFilePath);
    }

    /**
     * Generates a course report in TXT format.
     */
    public static void generateCourseReportTXT() {
        String reportFilePath = Paths.get(System.getProperty("user.dir"), "CourseReport.txt").toString();
        generateReport(reportFilePath, "txt");
        System.out.println("Course report generated in TXT format. File saved at: " + reportFilePath);
    }

    /**
     * Generates a course report in DOC format using Apache POI.
     */
    public static void generateCourseReportDOC() {
        String reportFilePath = Paths.get(System.getProperty("user.dir"), "CourseReport.docx").toString();
        generateReport(reportFilePath, "doc");
        System.out.println("Course report generated in DOC format. File saved at: " + reportFilePath);
    }

    /**
     * Handles the creation of the course report in specified format by querying the database and writing the results to a file.
     * 
     * @param filePath The path where the report file will be saved.
     * @param format The format of the report (csv, txt, doc).
     */
    private static void generateReport(String filePath, String format) {
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()) {

            switch (format) {
                case "csv":
                case "txt":
                    // Writing to a CSV or TXT file.
                    try (FileWriter fileWriter = new FileWriter(filePath);
                        PrintWriter writer = new PrintWriter(fileWriter)) {
                        writer.println("Course Name,Programme Name,Lecturer Name,Room Name,Students Enrolled");
                        while (rs.next()) {
                            writer.printf("%s,%s,%s,%s,%d%n",
                                    rs.getString("CourseName"), rs.getString("ProgrammeName"),
                                    rs.getString("LecturerName"), rs.getString("RoomName"),
                                    rs.getInt("StudentsEnrolled"));
                        }
                    }
                    break;
                case "doc":
                    // Writing to a DOCX file using Apache POI.
                    try (XWPFDocument document = new XWPFDocument();
                        FileOutputStream out = new FileOutputStream(filePath)) {
                        XWPFParagraph pTitle = document.createParagraph();
                        pTitle.createRun().setText("Course Report");
                        while (rs.next()) {
                            String line = String.format("%s, %s, %s, %s, %d",
                                    rs.getString("CourseName"), rs.getString("ProgrammeName"),
                                    rs.getString("LecturerName"), rs.getString("RoomName"),
                                    rs.getInt("StudentsEnrolled"));
                            XWPFParagraph p = document.createParagraph();
                            p.createRun().setText(line);
                        }
                        document.write(out);
                    }
                    break;
            }
            // Error handling
        } catch (Exception e) {
            System.err.println("Error generating course report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Prompts the user to select a report format and generates the report in the selected format.
     * 
     * @param reader BufferedReader to read user input from console.
     */
    public static void promptReportFormat(BufferedReader reader) {
        System.out.println("Select the report format: (1) CSV (2) TXT (3) DOC");
        int choice;
        try {
            choice = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            System.out.println("Invalid input.");
            return;
        }

        switch (choice) {
            case 1:
                generateCourseReportCSV();
                break;
            case 2:
                generateCourseReportTXT();
                break;
            case 3:
                generateCourseReportDOC();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
}

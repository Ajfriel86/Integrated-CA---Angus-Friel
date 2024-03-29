package com.cms;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class LecturerReport {

    // SQL query to fetch lecturer report data
    private static final String query = "SELECT l.Name AS LecturerName, l.Role, m.CourseName AS ModuleName, " +
            "(SELECT COUNT(DISTINCT g.StudentID) FROM grades g WHERE g.ModuleID = m.ModuleID) AS StudentsEnrolled, " +
            "GROUP_CONCAT(DISTINCT m.CourseName SEPARATOR ', ') AS TeachingSubjects " +
            "FROM lecturers l " +
            "LEFT JOIN module m ON l.LecturerID = m.LecturerID " +
            "LEFT JOIN grades g ON m.ModuleID = g.ModuleID " +
            "GROUP BY l.LecturerID, m.ModuleID";
    
            /**
            * Method to generate a report in different formats (CSV, TXT, DOC).
            * @param format The format in which the report is to be generated.
            */
            public static void generateReport(String format) {
                switch (format.toLowerCase()) {
                    case "csv":
                        generateLecturerReportCSV(); // Generate report in CSV format
                        break;
                    case "txt":
                        generateLecturerReportTXT(); // Generate report in TXT format
                        break;
                    case "doc":
                        generateLecturerReportDOC(); // Generate report in DOC format
                        break;
                    default:
                        System.out.println("Invalid format. Please try again.");
                        break;
                }
            }

    // Generates a lecturer report in CSV format
    public static void generateLecturerReportCSV() {
        String reportFilePath = Paths.get(System.getProperty("user.dir"), "LecturerReport.csv").toString();
        generateReport(reportFilePath, "csv");
    }

    // Generates a lecturer report in TXT format
    public static void generateLecturerReportTXT() {
        String reportFilePath = Paths.get(System.getProperty("user.dir"), "LecturerReport.txt").toString();
        generateReport(reportFilePath, "txt");
    }

    // Generates a lecturer report in DOC format
    public static void generateLecturerReportDOC() {
        String reportFilePath = Paths.get(System.getProperty("user.dir"), "LecturerReport.docx").toString();
        try (Connection conn = DatabaseConnection.getConnection(); // Database connection
             XWPFDocument document = new XWPFDocument(); // New Word document
             FileOutputStream out = new FileOutputStream(reportFilePath); // File output stream
             PreparedStatement pstmt = conn.prepareStatement(query); // Prepared statement
             ResultSet rs = pstmt.executeQuery()) { // Execute query

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("Lecturer Name, Role, Module Name, Students Enrolled, Teaching Subjects");

            // Loop through the result set and populate the document
            while (rs.next()) {
                String record = String.format("%s, %s, %s, %d, %s", 
                    rs.getString("LecturerName"), rs.getString("Role"), 
                    rs.getString("ModuleName"), rs.getInt("StudentsEnrolled"), 
                    rs.getString("TeachingSubjects"));
                run.addBreak();
                run.addBreak();
                run.setText(record);
            }

            document.write(out); // Write to the file
            System.out.println("Lecturer report generated successfully in DOC format. File saved at: " + reportFilePath);
        } catch (Exception e) {
            System.err.println("Error generating lecturer report in DOC format: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // General method for generating reports in CSV and TXT formats
    private static void generateReport(String reportFilePath, String format) {
        try (Connection conn = DatabaseConnection.getConnection(); // Database connection
             FileWriter fileWriter = new FileWriter(reportFilePath); // File writer
             PrintWriter writer = new PrintWriter(fileWriter); // Print writer for formatting
             PreparedStatement pstmt = conn.prepareStatement(query); // Prepared statement
             ResultSet rs = pstmt.executeQuery()) { // Execute query

            writer.println("Lecturer Name, Role, Module Name, Students Enrolled, Teaching Subjects");

            // Loop through the result set and write each record
            while (rs.next()) {
                writer.printf("%s, %s, %s, %d, %s%n", 
                    rs.getString("LecturerName"), rs.getString("Role"), 
                    rs.getString("ModuleName"), rs.getInt("StudentsEnrolled"), 
                    rs.getString("TeachingSubjects"));
            }

            // Message to display if generation was successful
            System.out.println("Lecturer report generated successfully in " + format.toUpperCase() + " format. File saved at: " + reportFilePath);
        } 
        // Error handling
        catch (Exception e) {
            System.err.println("Error generating lecturer report in " + format.toUpperCase() + " format: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
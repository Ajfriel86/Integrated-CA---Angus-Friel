package com.cms;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import java.io.FileOutputStream;


/**
 * Handles the generation and exporting of student reports in various formats
 * such as CSV, TXT, and DOCX. It fetches data from a database using a predefined query.
 */
public class StudentReport {

    // SQL query to fetch student data along with their programme and grades
    private static final String query = "SELECT s.StudentID, s.StudentName, s.ProgrammeID, " +
    "m.CourseName, g.Status, g.GradeValue " +
    "FROM students s " +
    "LEFT JOIN module m ON s.ProgrammeID = m.ProgrammeID " +
    "LEFT JOIN grades g ON s.StudentID = g.StudentID";

    /**
    * Generates a student report in CSV format.
    */
    public static void generateStudentReportCSV() {
        String reportFilePath = Paths.get(System.getProperty("user.dir"), "StudentReport.csv").toString();
        generateReport(reportFilePath, "csv");
        System.out.println("Student report generated in CSV format. File saved at: " + reportFilePath);
    }

    /**
     * Generates a student report in TXT format.
     */
    public static void generateStudentReportTXT() {
        String reportFilePath = Paths.get(System.getProperty("user.dir"), "StudentReport.txt").toString();
        generateReport(reportFilePath, "txt");
        System.out.println("Student report generated in TXT format. File saved at: " + reportFilePath);
    }
    
    /**
     * Generates a student report in DOC format.
     */
    public static void generateStudentReportDOC() {
        String reportFilePath = Paths.get(System.getProperty("user.dir"), "StudentReport.docx").toString();
        generateReport(reportFilePath, "doc");
        System.out.println("Student report generated in DOC format. File saved at: " + reportFilePath);
    }

    /**
     * Generates a report for student data in specified formats (CSV, TXT, or DOC) and saves it to a file.
     * This method connects to the database, executes a predefined query to fetch student data,
     * and formats the data according to the specified report format before saving it to a file.
     * 
     * @param filePath The path where the generated report will be saved.
     * @param format   The format of the report (csv, txt, or doc).
     */
    private static void generateReport(String filePath, String format) {
        try (
            // Establish a connection to the database
            Connection conn = DatabaseConnection.getConnection();
            // Prepare the SQL statement for execution
            PreparedStatement pstmt = conn.prepareStatement(query);
            // Execute the query and store the results
            ResultSet rs = pstmt.executeQuery()) {

            // Switch based on the desired format of the report
            switch (format) {
                case "csv":
                case "txt":
                    // For CSV and TXT formats, use FileWriter and PrintWriter
                    try (
                        FileWriter fileWriter = new FileWriter(filePath);
                        PrintWriter writer = new PrintWriter(fileWriter)) {
                        // Write the header row for the report
                        writer.println("Student ID,Student Name,Programme ID,Course Name,Status,Grade Value");
                        // Iterate through each row in the result set
                        while (rs.next()) {
                            // Format and write the data for each student
                            writer.printf("%s,%s,%s,%s,%s,%s%n",
                                    rs.getString("StudentID"), rs.getString("StudentName"),
                                    rs.getString("ProgrammeID"), rs.getString("CourseName"),
                                    rs.getString("Status"), rs.getString("GradeValue"));
                        }
                    }
                    break;
                case "doc":
                    // For DOC format, use Apache POI library to create a Word document
                    try (
                        XWPFDocument document = new XWPFDocument();
                        FileOutputStream out = new FileOutputStream(filePath)) {
                        // Create a title for the report
                        XWPFParagraph pTitle = document.createParagraph();
                        pTitle.createRun().setText("Student Report");
                        // Add a paragraph for the header row
                        XWPFParagraph paragraph = document.createParagraph();
                        paragraph.createRun().setText("Student ID,Student Name,Programme ID,Course Name,Status,Grade Value");
                        // Iterate through each row in the result set
                        while (rs.next()) {
                            // Format the data for each student
                            String line = String.format("%s,%s,%s,%s,%s,%s",
                                    rs.getString("StudentID"), rs.getString("StudentName"),
                                    rs.getString("ProgrammeID"), rs.getString("CourseName"),
                                    rs.getString("Status"), rs.getString("GradeValue"));
                            // Add a new paragraph for each student's data
                            XWPFParagraph p = document.createParagraph();
                            p.createRun().setText(line);
                        }
                        // Write the document to the file
                        document.write(out);
                    }
                    break;
            }
        } catch (Exception e) {
            // Log any exceptions that occur during report generation
            System.err.println("Error generating student report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    /**
     * Prompts the user to select the format for the student report generation.
     * 
     * @param reader BufferedReader to read the user input from console.
     */
    public static void promptReportFormat(BufferedReader reader) {
        System.out.println("Select the report format: (1) CSV (2) TXT (3) DOC");
        try {
            int choice = Integer.parseInt(reader.readLine());
    
            switch (choice) {
                case 1:
                    generateStudentReportCSV();
                    break;
                case 2:
                    generateStudentReportTXT();
                    break;
                case 3:
                    generateStudentReportDOC();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading input or invalid choice: " + e.getMessage());
        }
    }
}

package com.cms;

/**
 * A utility class that provides a method to clear the console screen.
 * It supports different commands based on the underlying operating system.
 */
public class ScreenClearer {

    /**
     * Clears the console screen. This method checks the operating system
     * and executes the appropriate command to clear the screen.
     * 
     * For Windows, it uses the 'cls' command through the command prompt.
     * For Unix-based systems (like Linux and macOS), it prints special control characters
     * to achieve the screen clear effect.
     */
    public static void clearScreen() {
        try {
            // Get the name of the operating system
            String operatingSystem = System.getProperty("os.name");

            // Check if the operating system is Windows
            if (operatingSystem.contains("Windows")) {
                // If so, use the ProcessBuilder to execute the 'cls' command in a new command prompt process
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For non-Windows systems, use ANSI escape codes to clear the screen
                System.out.print("\033[H\033[2J");
                System.out.flush(); // Ensure the output buffer is flushed to the console
            }
        } catch (Exception e) {
            // If any error occurs during the process, print an error message
            System.err.println("Failed to clear the screen");
        }
    }
}

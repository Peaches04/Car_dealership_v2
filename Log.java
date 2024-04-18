import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides logging for recording actions performed by users.
 * Logs are written to a text file called Log.txt with a timestamp, username, and action description.
 */
public class Log {

    private static final String LOG_FILE_PATH = "Log.txt"; // The path to the log file

    /**
     * Writes a log entry to the log file for a specified user action. Each log entry includes
     * a timestamp, the username of the user who performed the action, and a description of the action.
     * Log entries are appended. 
     *
     * @param username The username of the user performing the action.
     * @param action A description of the action being logged (print, logged in, etc.)
     */
    public static void log(String username, String action) {
        try (FileWriter fw = new FileWriter(LOG_FILE_PATH, true);
             PrintWriter pw = new PrintWriter(fw)) {
            // Get the current date and time in the specified format
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss MM/dd/yy");
            String formattedDateTime = now.format(formatter);
            
            // Prepare the log message according to the provided format
            String logMessage = String.format("%s - %s %s", formattedDateTime, username, action);
            
            // Write the log message to file
            pw.println(logMessage);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the log file: " + e.getMessage());
        }
    }
}
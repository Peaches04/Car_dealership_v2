import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for managing CSV files. Provides functionality to read from, write to,
 * and update CSV files.
 */
public class CSVManager {


    /**
     * Writes a list of string arrays to a CSV file at the specified path. Appends the data
     * to the file if it already exists.
     *
     * @param filePath The path of the CSV file to write to.
     * @param data The data to write to the CSV file.
     */
    public static void writeToCSV(String filePath, List<String[]> data) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File(filePath), true))) {
            for (String[] rowData : data) {
                writer.println(String.join(",", rowData));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while writing to the CSV file: " + e.getMessage());
        }
    }

    /**
     * Reads data from a CSV file at the specified path into a list of string arrays.
     *
     * @param filePath The path of the CSV file to read from.
     * @return A list of {@code String[]} where each {@code String[]} represents a row from the CSV file.
     *      
     */
    public static List<String[]> readFromCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(",");
                data.add(rowData);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        return data;
    }

    /**
     * Update function that is in charge of updating the information in the CSV's after the user exits/signs out
     *
     * @param filePath The path of the CSV file to update.
     * @param updatedData The new data to write to the CSV file, replacing its current contents.
     *                    
     */
    public static void updateCSV(String filePath, List<String[]> updatedData) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            for (String[] rowData : updatedData) {
                writer.println(String.join(",", rowData));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while writing to the CSV file: " + e.getMessage());
        }
    }
}
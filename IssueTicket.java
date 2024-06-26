import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;


/**
 * Represents a ticket for a purchasing a car, encapsulates details ID, username,
 * car type, model, year, and color.
 */
public class IssueTicket {
	
	 /**
     * The file path where ticket data is stored in CSV format.
     */
    private static final String FILE_PATH = "issued_tickets.csv";

    /**
     * The unique identifier for the ticket.
     */
    private String ID;

    /**
     * The username of the user associated with this ticket.
     */
    private String username;

    /**
     * The type of car associated with this ticket.
     */
    private String carType;

    /**
     * The model of the car associated with this ticket.
     */
    private String model;

    /**
     * The year of manufacture of the car associated with this ticket.
     */
    private int year;

    /**
     * The color of the car associated with this ticket.
     */
    private String color;

    /**
     * The purchase price of the car associated with this ticket.
     */
    private float Price;

    /**
     * A unique identifier that is generated to ensure each ticket is distinctly identifiable.
     */
    private String UniqueID;
    
    /**
     * Constructs an IssueTicket with the specified details.
     * 
     * @param ID The unique identifier for the ticket.
     * @param username The username of the user associated with the ticket.
     * @param carType The type of car associated with the ticket.
     * @param model The model of the car.
     * @param year The year of manufacture of the car.
     * @param color The color of the car.
     * @param Price the price of the car. 
     */
    public IssueTicket(String ID, String username, String carType, String model, int year, String color, float Price) {
        this.ID = ID;
        this.username = username;
        this.carType = carType;
        this.model = model;
        this.year = year;
        this.color = color;
        this.Price = Price;

        this.UniqueID = generateUniqueID();
        
    }
    
    
    /**
     * Generates a unique identifier for the ticket using a UUID.
     * This ID is intended for ensuring each ticket is distinctly identifiable beyond its normal ID.
     *
     * @return A unique identifier as a String.
     */
    private String generateUniqueID() {
        return UUID.randomUUID().toString();
    }
    
    /**
     * Displays tickets for a specified user from a CSV file.
     * 
     * @param username The username to filter tickets by.
     * @param filePath The path of the CSV file containing tickets data.
     */
    public static void viewTicketsForUser(String username, String filePath) throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] ticketData = line.split(",");
                if (ticketData.length > 1 && ticketData[1].trim().equalsIgnoreCase(username.trim())) {
                    System.out.println(" Car ID: " + ticketData[0] +
                        "\n Username: " + ticketData[1] +
                        "\n Car Type: " + ticketData[2] +
                        "\n Model: " + ticketData[3] +
                        "\n Year: " + ticketData[4] +
                        "\n Color: " + ticketData[5] +
                        "\n Price: " + ticketData[6] +
                    	"\n UniqueID " + ticketData[7]);
                    	
                    System.out.println();
                }
            }
        }

        
    }
    
    // Getters
    
    /**
     * @return The ID of the ticket.
     */
    public String getID() {
        return ID;
    }

    /**
     * @return The username associated with the ticket.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return The car type associated with the ticket.
     */
    public String getCarType() {
        return carType;
    }

    /**
     * @return The model of the car associated with the ticket.
     */
    public String getModel() {
        return model;
    }

    /**
     * @return The year of manufacture of the car associated with the ticket.
     */
    public int getYear() {
        return year;
    }

    /**
     * @return The color of the car associated with the ticket.
     */
    public String getColor() {
        return color;
    }
    /**
     * @return The price of the car associated with the ticket.
     */
    public float getPrice() {
        return Price;
    }

    // Setters
    
    /**
     * Sets the ID of the ticket.
     * @param ID The new ID.
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Sets the username associated with the ticket.
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the car type for the ticket.
     * @param carType The new car type.
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * Sets the model of the car for the ticket.
     * @param model The new model.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Sets the year of manufacture of the car for the ticket.
     * @param year The new year.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Sets the color of the car for the ticket.
     * @param color The new color.
     */
    public void setColor(String color) {
        this.color = color;
    }
    /**
     * Sets the price of the car for the ticket.
     * @param Price The price of the car.
     */
    public void setPrice(float Price) {
        this.Price = Price;
    }
    
    /**
     * Prints the details of the ticket.
     */
    public void getDetails() {
        System.out.println(username + " car ticket: \n" + "Car Type: " + carType + "\nModel: " + model + "\nYear: " + year + "\nColor: " + color + "\nPrice: " + Price +  "\nUnique ID:" + UniqueID);
    }

    /**
     * Parses the ticket details into String[] type for CSV storage.
     * 
     * @return A String array representing the ticket details.
     */
    public String[] CSVparser() {
        return new String[]{ID, username, carType, model, String.valueOf(year), color, String.valueOf(Price), UniqueID};
    }
    
    /**
     * Returns the unique identifier of the ticket.
     * This getter provides access to the ticket's unique UUID.
     *
     * @return The unique identifier of the ticket.
     */
    public String getUniqueID() {
        return UniqueID;
    }

    
    /**
     * Sets the unique identifier of the ticket.
     * This method allows for the manual setting of the unique identifier, though typically it is generated automatically.
     *
     * @param uniqueID The new unique identifier for the ticket.
     */
    public void setUniqueID(String uniqueID) {
        UniqueID = uniqueID;
    }
    
    /**
     * Deletes the first occurrence of a ticket with the specified ID and username and returns a boolean
     * indicating whether the deletion was successful.
     *
     * @param ticketID The ID of the ticket to delete (integer type).
     * @param username The username associated with the ticket.
     * @return true if the ticket was successfully deleted, false otherwise.
     * @throws IOException If there's an issue reading or writing to the CSV file.
     */
    public static boolean deleteTicket(int ticketID, String username) throws IOException {
        String idString = String.valueOf(ticketID);  
        File originalFile = new File(FILE_PATH);
        File tempFile = new File("temp_" + FILE_PATH);

        boolean found = false; 

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] ticketData = line.split(",");
                // Check if the current line's ticket matches the ID and username
                if (!found && ticketData.length > 1 && ticketData[0].trim().equals(idString) && ticketData[1].trim().equalsIgnoreCase(username)) {
                    found = true; // Mark that we've found and are deleting the matching ticket
                } else {
                    writer.write(line + System.lineSeparator()); // Write non-matching lines to the temp file
                }
            }
        }

        // Only perform file operations if a matching ticket was found and deleted
        if (found) {
            boolean deleteSuccessful = originalFile.delete();
            boolean renameSuccessful = tempFile.renameTo(originalFile);

            if (!deleteSuccessful || !renameSuccessful) {
                System.out.println("Could not complete the delete operation properly.");
                if (!deleteSuccessful) {
    
                    tempFile.renameTo(originalFile);
                }
                if (!renameSuccessful) {
       
                    System.out.println("Failed to rename the temporary file.");
                    
                }
            } else {
                System.out.println("Ticket with ID: " + idString + " and username: " + username + " deleted successfully.");
                return true;
            }
        } else {
            System.out.println("No ticket found with ID: " + idString + " and username: " + username + ".");
          
            tempFile.delete(); 
            return false;
        }
        return false;
    }
}
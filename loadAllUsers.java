


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides functionality to load user data from a CSV file and create User objects.
 * This class reads user data, interprets it, and constructs User objects based on the specified properties.
 */
public class loadAllUsers {
    /**
     * Stores the headers from the user data CSV file. This array is used to map CSV columns to user attributes.
     */
    public static String[] header;

    /**
     * Reads user data from a CSV file and creates a list of User objects.
     * The method maps CSV columns to user attributes and handles data conversion.
     *
     * @return A list of User objects loaded from the CSV file.
     */
    public static List<User> loadUsersFromCSV() {
        List<String[]> userData = CSVManager.readFromCSV("user_data_part2.csv");
        List<User> users = new ArrayList<>();
        Map<String, Integer> columnMap = new HashMap<>();
        
        if (!userData.isEmpty()) {
            header = userData.get(0); // Get the header row which should contain column names
            for (int i = 0; i < header.length; i++) {
                columnMap.put(header[i], i);
            }

            for (int j = 1; j < userData.size(); j++) {
                String[] userInfo = userData.get(j);
                Map<String, String> userProperties = new HashMap<>();

                for (String key : columnMap.keySet()) {
                    int columnIndex = columnMap.get(key);
                    String value = columnIndex < userInfo.length ? userInfo[columnIndex].trim() : "";
                    userProperties.put(key, value.isEmpty() ? "false" : value);
                }

                try {
                    User user = createUserFromProperties(userProperties);
                    users.add(user);
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing user data: " + e.getMessage());
                }
            }
        }

        return users;
    }

    /**
     * Creates a User object from a map of properties.
     * This method parses the properties and constructs a User object accordingly.
     *
     * @param properties A map containing user attributes as key-value pairs.
     * @return A new User object constructed from the provided properties.
     */
    private static User createUserFromProperties(Map<String, String> properties) {
        int ID = ParseInt(properties.getOrDefault("ID", "0"));
        String firstName = properties.getOrDefault("First Name", "Unknown");
        String lastName = properties.getOrDefault("Last Name", "Unknown");
        float moneyAvailable = ParseFloat(properties.getOrDefault("Money Available", "0.0"));
        int carsPurchased = ParseInt(properties.getOrDefault("Cars Purchased", "0"));
        boolean minerCarsMembership = Boolean.parseBoolean(properties.getOrDefault("MinerCars Membership", "false"));
        String username = properties.getOrDefault("Username", "Unknown");
        String password = properties.getOrDefault("Password", "Unknown");

        return new User(ID, firstName, lastName, moneyAvailable, carsPurchased, minerCarsMembership, username, password);
    }

    /**
     * Parses an integer from a string with error handling.
     * If parsing fails, returns a default value of 0.
     *
     * @param value The string to parse into an integer.
     * @return The parsed integer, or 0 if parsing fails.
     */
    private static int ParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;  
        }
    }


    /**
     * Parses a float from a string with error handling.
     * If parsing fails, returns a default value of 0.0f.
     *
     * @param value The string to parse into a float.
     * @return The parsed float, or 0.0f if parsing fails.
     */
    private static float ParseFloat(String value) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return 0.0f;  
        }
    }
}
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides functionality to load car data from a CSV file and create Car objects.
 * This class reads car data, interprets it, and constructs Car objects based on the specified properties.
 */
public class loadAllCars {
	
	/**
	 * Stores the headers from the car data CSV file. This array is used to map CSV columns to car attributes.
	 */
    public static String[] header;
    
    
    
    /**
     * Reads car data from a CSV file and creates a list of Car objects.
     * The method maps CSV columns to car attributes and handles data conversion.
     *
     * @return A list of Car objects loaded from the CSV file.
     */
    public static List<Car> loadCarsFromCSV() {
        List<String[]> carData = CSVManager.readFromCSV("car_data_part2.csv");
        List<Car> cars = new ArrayList<>();
        Map<String, Integer> columnMap = new HashMap<>();

        if (!carData.isEmpty()) {
            header = carData.get(0); // Get the header row which should contain column names
            
            for (int i = 0; i < header.length; i++) {
                columnMap.put(header[i], i);
                
            }
            
            
            for (int j = 1; j < carData.size(); j++) {
                Map<String, String> carProperties = new HashMap<>();
                String[] carInfo = carData.get(j);
                for (String key : columnMap.keySet()) {
                	
                	int columnIndex = columnMap.get(key);
                	
                	String value = columnIndex < carInfo.length ? carInfo[columnIndex].trim() : "";
                    if ("Has Turbo".equals(key) && value.isEmpty()) {
                        carProperties.put(key, "false"); // Explicitly set empty turbo fields to "false"
                    } else {
                        carProperties.put(key, value);
                    }
                }
                try {
                    Car car = createCarFromProperties(carProperties);
                    cars.add(car);
                    
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing car data: " + e.getMessage());
                }
            }
        }

        return cars;
    }

    /**
     * Creates a Car object from a map of properties.
     * Depending on the car type, a specific subclass of Car may be instantiated.
     *
     * @param properties A map containing car attributes as key-value pairs.
     * @return A new Car object constructed from the provided properties.
     */
    private static Car createCarFromProperties(Map<String, String> properties) {
        int id = ParseInt(properties.getOrDefault("ID", "0"));

        
        int capacity = ParseInt(properties.getOrDefault("Capacity", "0"));
        
        float price = ParseFloat(properties.getOrDefault("Price", "0"));
        
 
        int carsAvailable = Integer.parseInt(properties.getOrDefault("Cars Available", "0"));
        boolean hasTurbo = parseBoolean(properties.getOrDefault("hasTurbo", "false"));
        int year = Integer.parseInt(properties.getOrDefault("Year", "0"));
        
        String type = properties.getOrDefault("Car Type", "No");
        String model = properties.getOrDefault("Model", "No");
        String condition = properties.getOrDefault("Condition", "No");
        String color = properties.getOrDefault("Color", "No");
        String transmission = properties.getOrDefault("Transmission", "No");
        String vin = properties.getOrDefault("VIN", "No");
        String fuelType = properties.getOrDefault("Fuel Type", "No");


        switch (type) {
            case "Sedan":
                return new Sedan(id, type, model, condition, color, capacity, price, transmission, vin, fuelType, year, carsAvailable, hasTurbo);
            case "SUV":
                return new SUV(id, type, model, condition, color, capacity, price, transmission, vin, fuelType, year, carsAvailable, hasTurbo);
            case "Hatchback":
                return new Hatchback(id, type, model, condition, color, capacity, price, transmission, vin, fuelType, year, carsAvailable, hasTurbo);
            case "Pickup":
                return new Pickup(id, type, model, condition, color, capacity, price, transmission, vin, fuelType, year, carsAvailable, hasTurbo);
            default:
                return new Car(id, type, model, condition, color, capacity, price, transmission, vin, fuelType, year, carsAvailable, hasTurbo);
        }
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
            return 0;  // Default to 0 if parsing fails
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
            return 0.0f;  // Default to 0.0 if parsing fails
        }
    }
    
    /**
     * Parses a boolean from a string.
     * Recognizes "true" or "yes" (case insensitive) as true, anything else as false.
     *
     * @param value The string to parse into a boolean.
     * @return The parsed boolean.
     */
    private static boolean parseBoolean(String value) {
        return "true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value);
    }
}
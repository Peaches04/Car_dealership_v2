import java.util.HashMap;
import java.util.Map;

/**
 * Represents a car with various details such as type, model, condition, and more.
 * Provides both getters and setters for manipulating car properties.
 */
public class Car implements ICSVSerializable {
	   private int id;
	    private String type;
	    private String model;
	    private String condition;
	    private String color;
	    private int capacity;
	    private float price;
	    private String transmission;
	    private String vin;
	    private String fuelType;
	    private int year;
	    private int carsAvailable;
	    private boolean hasTurbo;

	    /**
	     * Constructs a new Car instance with detailed specifications.
	     *
	     * @param id The unique identifier for the car.
	     * @param type The type of the car (e.g., Sedan, SUV).
	     * @param model The model of the car.
	     * @param condition The condition of the car (e.g., New, Used).
	     * @param color The color of the car.
	     * @param capacity The seating capacity of the car.
	     * @param price The price of the car.
	     * @param transmission The transmission type of the car (e.g., Automatic, Manual).
	     * @param vin The Vehicle Identification Number.
	     * @param fuelType The type of fuel the car uses (e.g., Gasoline, Electric).
	     * @param year The model year of the car.
	     * @param carsAvailable The number of cars available for this model.
	     * @param hasTurbo Indicates whether the car is equipped with a turbocharger.
	     */
	    public Car(int id, String type, String model, String condition, String color, int capacity,
	               float price, String transmission, String vin, String fuelType, int year, 
	               int carsAvailable, boolean hasTurbo) {
	        this.id = id;
	        this.type = type;
	        this.model = model;
	        this.condition = condition;
	        this.color = color;
	        this.capacity = capacity;
	        this.price = price;
	        this.transmission = transmission;
	        this.vin = vin;
	        this.fuelType = fuelType;
	        this.year = year;
	        this.carsAvailable = carsAvailable;
	        this.hasTurbo = hasTurbo;
	    }

	    /**
	     * Displays the complete details of the car.
	     */
	    public void displayDetails() {
	        System.out.println("ID: " + id +
	                           ", Type: " + type +
	                           ", Model: " + model +
	                           ", Condition: " + condition +
	                           ", Color: " + color +
	                           ", Capacity: " + capacity +
	                           ", Price: $" + price +
	                           ", Transmission: " + transmission +
	                           ", VIN: " + vin +
	                           ", Fuel Type: " + fuelType +
	                           ", Year: " + year +
	                           ", Cars Available: " + carsAvailable +
	                           ", Turbo: " + (hasTurbo ? "Yes" : "No"));
	    }

	    /**
	     * Converts car details into a String array suitable for CSV file storage.
	     *
	     * @return A String array containing all car details for CSV storage.
	     */
	    public String[] ArrayListToCSV() {
	        Map<String, String> carValues = new HashMap<>();
	        carValues.put("ID", String.valueOf(id));
	        carValues.put("Car Type", type);
	        carValues.put("Model", model);
	        carValues.put("Condition", condition);
	        carValues.put("Color", color);
	        carValues.put("Capacity", String.valueOf(capacity));
	        carValues.put("Year", String.valueOf(year));

	        carValues.put("Transmission", transmission);
	        carValues.put("VIN", vin);
	        carValues.put("Fuel Type", fuelType);
	        carValues.put("Price", String.format("%.2f", price));
	        carValues.put("Cars Available", String.valueOf(carsAvailable));
	        carValues.put("hasTurbo", hasTurbo ? "Yes" : "No");
	       

	        // Create an array matching the order of the header
	        
	        String[] csvValues = new String[loadAllCars.header.length];
	        for (int i = 0; i < loadAllCars.header.length; i++) {
	            csvValues[i] = carValues.getOrDefault(loadAllCars.header[i], "");
	            

	        }
	        

	        return csvValues;
	    }


    // Getters

    /**
     * @return The ID of the car.
     */
    public int getId() {
        return id;
    }

    /**
     * @return The type of the car.
     */
    public String getType() {
        return type;
    }

    /**
     * @return The model of the car.
     */
    public String getModel() {
        return model;
    }

    /**
     * @return The condition of the car.
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @return The color of the car.
     */
    public String getColor() {
        return color;
    }

    /**
     * @return The capacity of the car.
     */
    public int getCapacity() {
        return capacity;
    }


    /**
     * @return The fuel type of the car.
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * @return The transmission of the car.
     */
    public String getTransmission() {
        return transmission;
    }

    /**
     * @return The Vehicle Identification Number of the car.
     */
    public String getVin() {
        return vin;
    }

    /**
     * @return The price of the car.
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return The number of available cars of this model.
     */
    public int getCarsAvailable() {
        return carsAvailable;
    }

    // Setters
    
 
    /**
     * Sets the ID of the car.
     * @param id The new ID for the car.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the type of the car.
     * @param type The new type for the car.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the model of the car.
     * @param model The new model for the car.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Sets the condition of the car.
     * @param condition The new condition of the car (e.g., New, Used).
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Sets the color of the car.
     * @param color The new color for the car.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Sets the seating capacity of the car.
     * @param capacity The new seating capacity for the car.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    /**
     * Sets the fuel type of the car.
     * @param fuelType The new fuel type for the car (e.g., Gasoline, Electric).
     */
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * Sets the transmission type of the car.
     * @param transmission The new transmission type for the car (e.g., Automatic, Manual).
     */
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * Sets the Vehicle Identification Number (VIN) of the car.
     * @param vin The new VIN for the car.
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * Sets the price of the car.
     * @param price The new price for the car.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Sets the number of cars available of this model.
     * @param carsAvailable The new number of available cars.
     */
    public void setCarsAvailable(int carsAvailable) {
        this.carsAvailable = carsAvailable;
    }
    
    /**
     * Checks if the car is equipped with a turbocharger.
     * <p>
     * This method returns a boolean indicating whether the car has a turbocharger.
     * Turbochargers can enhance the engine's performance by allowing more air to flow,
     * thus increasing the power output.
     *
     * @return {@code true} if the car has a turbocharger, {@code false} otherwise.
     */
    public boolean getHasTurbo() {
        return hasTurbo;
    }

    /**
     * Sets the turbocharger status for the car.
     * <p>
     * This method allows the modification of the car's turbocharger status.
     * Enabling a turbocharger can significantly boost the car's performance.
     *
     * @param hasTurbo A boolean value indicating whether the car should have a turbocharger.
     */
    public void setHasTurbo(boolean hasTurbo) {
        this.hasTurbo = hasTurbo;
    }
    /**
     * Retrieves the model year of the car.
     * <p>
     * This method returns the year that the car was modeled. The model year
     * is important for understanding the generation and the features of the car.
     *
     * @return The model year of the car.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the model year of the car.
     * <p>
     * This method allows setting a new model year for the car. Model years
     * typically correspond to the calendar year in which the car was manufactured
     * and can affect the valuation of the car.
     *
     * @param year The new model year to set for the car.
     */
    public void setYear(int year) {
        this.year = year;
    }
}
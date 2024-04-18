/**
 * Represents a Sedan, extending the Car class with additional trunk feature.
 */
public class Sedan extends Car {

    /**
     * Constructs a new Sedan instance with specified details.
     * 
     * @param id The unique identifier for the car.
     * @param type The type of the car.
     * @param model The model of the car.
     * @param condition The condition of the car.
     * @param color The color of the car.
     * @param capacity The seating capacity of the car.
     * @param fuelType The type of fuel the car uses.
     * @param transmission The transmission type of the car.
     * @param vin The Vehicle Identification Number.
     * @param year The manufacturing year of the vehicle
     * @param price The price of the car.
     * @param carsAvailable The number of cars available for this model.
     * @param hasTurbo If the sedan has a turbo or not
     */
    
    public Sedan(int id, String type, String model, String condition, String color, int capacity,
                 float price, String transmission, String vin, String fuelType, int year, 
                 int carsAvailable, boolean hasTurbo) {
    	
    	
        super(id, type, model, condition, color, capacity, price, transmission, vin, fuelType, year, carsAvailable, hasTurbo);
        
    }

    /**
     * Displays the details of the Sedan.
     */
    @Override
    public void displayDetails() {
        super.displayDetails(); 
    }
    
    /**
     * Converts the sedan's data into an array format suitable for CSV storage.
     *
     * @return A string array containing the sedan attributes formatted for CSV.
     */
    @Override
    public String[] ArrayListToCSV() {
        return super.ArrayListToCSV();
    }

}
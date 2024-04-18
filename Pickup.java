/**
 * Represents a Pickup truck, extending the Car class with additional towing capacity parameter.
 */
public class Pickup extends Car {

    /**
     * Constructs a new Pickup instance with specified details, including towing capacity.
     * Additional parameters for the year and turbocharger presence are included.
     *
     * @param id The unique identifier.
     * @param type The type of the car (e.g., Pickup).
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
    public Pickup(int id, String type, String model, String condition, String color, int capacity,
                  float price, String transmission, String vin, String fuelType, int year, 
                  int carsAvailable, boolean hasTurbo) {
        super(id, type, model, condition, color, capacity, price, transmission, vin, fuelType, year, carsAvailable, hasTurbo);
    }

    /**
     * Displays the details of the Pickup.
     */
    @Override
    public void displayDetails() {
        super.displayDetails(); 

    }
    
    /**
     * Converts the Pickup truck's data into an array format suitable for CSV storage.
     *
     * @return A string array containing the Pickup truck's attributes formatted for CSV.
     */
    @Override
    public String[] ArrayListToCSV() {
        return super.ArrayListToCSV();
    }
}
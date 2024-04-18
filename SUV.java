/**
 * Represents an SUV, extending the {@code Car} class. This class includes additional features like year of manufacture and turbocharged option.
 */
public class SUV extends Car {

    /**
     * Constructs a new SUV instance with specified details, including year of manufacture and turbocharger presence.
     * 
     * @param id The unique identifier for the car.
     * @param type The type of the car.
     * @param model The model of the car.
     * @param condition The condition of the car.
     * @param color The color of the car.
     * @param capacity The seating capacity of the car.
     * @param price The price of the car.
     * @param transmission The transmission type of the car.
     * @param vin The Vehicle Identification Number.
     * @param fuelType The type of fuel the car uses.
     * @param year The model year of the car.
     * @param carsAvailable The number of cars available for this model.
     * @param hasTurbo Indicates whether the car is equipped with a turbocharger.
     */
    public SUV(int id, String type, String model, String condition, String color, int capacity,
               float price, String transmission, String vin, String fuelType, int year, 
               int carsAvailable, boolean hasTurbo) {
        super(id, type, model, condition, color, capacity, price, transmission, vin, fuelType, year, carsAvailable, hasTurbo);
    }

    /**
     * Displays the details of the SUV.
     */
    @Override
    public void displayDetails() {
        super.displayDetails();

    }
    
    /**
     * Converts the SUV's data into an array format suitable for CSV storage.
     *
     * @return A string array containing the SUV attributes formatted for CSV.
     */
    @Override
    public String[] ArrayListToCSV() {
        return super.ArrayListToCSV();
    }
}
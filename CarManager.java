import java.time.Year;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Manages car-related operations including loading cars, processing purchases, and handling inventory.
 * This class is responsible for interfacing with car data stored in CSV files and managing the car purchasing process.
 */
public class CarManager {
	
    /**
     * A list of all cars currently available in the inventory
     */
    private List<Car> cars;
    /**
     * Stores ticket information for each car purchase. Each ticket is represented as an array of strings
     * containing detailed data about the transaction. This list is read from "issued_tickets.csv".
     */
    private List<String[]> ticketData;

    
    /**
     * Initializes a CarManager object, loading cars from a CSV file and reading ticket data.
     */
    public CarManager() {
        this.cars = loadAllCars.loadCarsFromCSV();
        this.ticketData = CSVManager.readFromCSV("issued_tickets.csv");
       
    }

    /**
     * Prints details of all cars available in the shop.
     */
    public void printAllCars() {
        for (Car car : cars) {
            car.displayDetails();
            System.out.println();
        }
    }

    /**
     * Prints details of cars filtered by their condition (new or used).
     * 
     * @param usedOrNew The condition of the cars to display. Should be either "New" or "Used".
     */
    public void printConditionCars(String usedOrNew) {
        for (Car car : cars) {
            if (usedOrNew.equals(car.getCondition())) {
                car.displayDetails();
                System.out.println(); // Adds an empty line between car details for readability
            }
        }
    }

    /**
     * Attempts to purchase a car with the given ID for the currently logged-in user. If the user has enough funds
     * and there is enough cars available, the car is purchased. Then it issues a ticket for the customer and saves it in the 
     * issue_tickets.csv file. 
     * 
     * @param username The username of the buyer.
     * @param ID The ID of the car to purchase.
     * @param userManager an instance of the UserManager object. 
     * @return true if the purchase is successful; false otherwise.
     */
    public boolean purchaseCar(String username, String ID, UserManager userManager)throws Exception {
    	
    	float discount = (float) .9;
    	

    	
        User currentUser = userManager.findUserByUsername(username);
        if (currentUser == null) {
            System.out.println("No user is currently logged in or user not found.");
            return false;
        }

        float userFunds = currentUser.getMoneyAvailable();

            int carId = Integer.parseInt(ID); 

            for (Car car : cars) {
                if (carId == car.getId()) {
                	
                    if (userFunds >= car.getPrice()) {
                        if (car.getCarsAvailable() > 0) {
                        	
                        	float taxes = (float) .0625;
                        	float price = car.getPrice()*(1+taxes);
                        	if(currentUser.isMinerCarsMembership()) {
                        		price *= discount;
                        	}
                        	
                            currentUser.setMoneyAvailable(userFunds - price);
                            IssueTicket ticket = new IssueTicket(ID, username, car.getType(), car.getModel(), Year.now().getValue(), car.getColor(), car.getPrice());
                            
                            currentUser.setCarsPurchased(currentUser.getCarsPurchased() + 1);
                            
                            List<String[]> dataToWrite = new ArrayList<>();
                            dataToWrite.add(ticket.CSVparser());
                            
                        
                            CSVManager.writeToCSV("issued_tickets.csv", dataToWrite);
                            
                            car.setCarsAvailable(car.getCarsAvailable() - 1);
                  

                            return true;
                        } else {
                            System.out.println("No cars available.");
                            return false;
                        }
                    } else {
                        System.out.println("Insufficient funds.");
                        return false;
                    }
                }
            }
 

        System.out.println("Car with ID " + ID + " not found.");
        return false;
    }
    
    
    /**
     * Saves the current state of car inventory to a CSV file.
     */
    public void saveCarsToCSV() {
        List<String[]> carDataWrite = new ArrayList<>();
        carDataWrite.add(loadAllCars.header);  // Ensure the header is included
        for (Car car : cars) {
            carDataWrite.add(car.ArrayListToCSV());
            
        }
        CSVManager.updateCSV("car_data_part2.csv", carDataWrite);
    }
    
    
    /**
     * 
     * gets the next available ID in the Car CSV file. 
     * @return the next ID 
     */
    private int getNextId() {
        int maxId = 0;
        for (Car car : cars) {
            if (car.getId() > maxId) {
                maxId = car.getId(); // Continuously find the highest ID
            }
        }
        return maxId + 1; // Always return one more than the maximum found ID
    }
    
    

	/**
	 * Adds a new car to the inventory or updates the availability if it exists.
	 *
	 * @param type The type of the car.
	 * @param model The model of the car.
	 * @param condition The condition of the car (new or used).
	 * @param color The color of the car.
	 * @param capacity The seating capacity of the car.
	 * @param year The manufacture year of the car.
	 * @param fuelType The type of fuel the car uses.
	 * @param transmission The transmission type of the car.
	 * @param vin The Vehicle Identification Number, used as a unique identifier.
	 * @param price The price of the car.
	 * @param carsAvailable The number of such cars available.
	 * @param hasTurbo Whether the car has a turbo feature.
	 */
    public void addCar(String type, String model, String condition, String color, int capacity, int year, String fuelType, String transmission, String vin, float price, int carsAvailable, boolean hasTurbo) {
    	
    	
    	int id = getNextId();

       
        for (Car car : cars) {
            if (car.getVin().equals(vin)) {
                System.out.println("Updating Cars Available for VIN: " + vin);
                car.setCarsAvailable(car.getCarsAvailable() + carsAvailable); // Update cars available count
                saveCarsToCSV(); // Save changes to CSV
                return;
            }
        }

        // If no car with the same VIN exists, create a new car
        Car newCar = new Car(id, type, model, condition, color, capacity, price, transmission, vin, fuelType, year, carsAvailable, hasTurbo);

        cars.add(newCar);

        
        saveCarsToCSV(); // Save changes to CSV
        System.out.println("New car added with ID: " + id + " and VIN: " + vin);
    }

    /**
     * Removes a car from the inventory based on its VIN.
     *
     * @param vin The Vehicle Identification Number of the car to remove.
     * @return true if the car was successfully removed; false otherwise.
     */
    public boolean removeCar(String vin) {
        boolean removed = cars.removeIf(car -> car.getVin().equals(vin));
        if (removed) {
            saveCarsToCSV(); // Update CSV file
            System.out.println("Car removed: VIN " + vin);
        } else {
            System.out.println("Car not found with VIN: " + vin);
        }
        return removed;
    }
    
    
    /**
     * Finds and returns a car in the inventory by its ID.
     *
     * @param carId The ID of the car to find.
     * @return The Car object if found; null otherwise.
     */
    public Car findCarById(int carId) {
        for (Car car : cars) {
            if (car.getId() == carId) {
                return car;  // Return the car if its ID matches the provided carId
            }
        }
        return null;  // Return null if no car is found with the specified ID
    }
    
    /**
     * Calculates and returns the total revenue by car type.
     * 
     * @param type The car type to calculate revenue for.
     * @return Total revenue for the given car type.
     */
    public double getRevenueByType(String type) {
        double totalRevenue = 0.0;
        for (String[] ticket : ticketData) {
            if (ticket[2].equalsIgnoreCase(type)) { // Using index 2 for car type
                totalRevenue += Double.parseDouble(ticket[6]); // Using index 6 for price
            }
        }
        return totalRevenue;
    }

    /**
     * Calculates and returns the total revenue by car ID.
     * 
     * @param id The car ID to calculate revenue for.
     * @return Total revenue for the given car ID.
     */
    public double getRevenueById(String id) {
        double totalRevenue = 0.0;
        for (String[] ticket : ticketData) {
            if (ticket.length > 6 && ticket[0].equals(id)) { // Using index 0 for ID
                totalRevenue += Double.parseDouble(ticket[6]); // Using index 6 for price
            }
        }
        return totalRevenue;
    }
    
    /**
     * Displays the total revenue for a car type or ID, printing a corresponding message.
     *
     * @param identifier The car type or ID to display revenue for.
     */
    public void displayRevenue(String identifier) {
        // Attempt to calculate revenue as type first
        double revenueByType = getRevenueByType(identifier);
        if (revenueByType > 0) {
            System.out.println("Total revenue for type " + identifier + ": " + revenueByType);
            return;
        }

        // If no revenue was found by type, try ID
        double revenueById = getRevenueById(identifier);
        if (revenueById > 0) {
            System.out.println("Total revenue for ID " + identifier + ": " + revenueById);
        } else {
            System.out.println("No revenue found for " + identifier);
        }
    }


}
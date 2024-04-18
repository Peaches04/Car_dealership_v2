/**
 * Represents an admin with abilities to manage cars and users.
 * Inherits from the Person class and utilizes CarManager and UserManager for operations.
 */
public class Admin extends Person {
	/**
	 * Manages all aspects of car operations including loading, purchasing, and inventory management.
	 * This instance is responsible for interfacing with car data and executing operations specific to car management.
	 */
	private CarManager carManager;

	/**
	 * Manages user operations such as authentication, session management, and user data handling.
	 * This instance is crucial for integrating user functionalities with the car operations, allowing a unified management environment.
	 */
	private UserManager userManager;

    
    /**
     * Constructs an Admin instance with specified name, last name, car manager, and user manager.
     *
     * @param name The first name of the admin.
     * @param lastName The last name of the admin.
     * @param carManager The CarManager instance used to manage car operations.
     * @param userManager The UserManager instance used to manage user operations.
     */
    public Admin(String name, String lastName, CarManager carManager, UserManager userManager) {
        super(name, lastName);
        this.carManager = carManager;
        this.userManager = userManager;
    }

    /**
     * Displays basic information about the admin.
     */
    public void displayInfo() {
        System.out.println("Admin Name: " + getName() + ", Last Name: " + getLastName());
    }

    
    
    /**
     * Addition of a new car to the CarManager.
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
        carManager.addCar(type, model, condition, color, capacity, year, fuelType, transmission, vin, price, carsAvailable, hasTurbo);
        System.out.println("Car added successfully with VIN: " + vin);
    }
    
    
    /**
     * Delegates the removal of a car by its VIN to the CarManager.
     *
     * @param vin The Vehicle Identification Number of the car to remove.
     */
    public void removeCar(String vin) {
        carManager.removeCar(vin);
    }

    /**
     * Displays the revenue generated either by car type or car ID.
     *
     * @param value The car type or ID to calculate revenue for.
     */
    public void getRevenueByIDOrType(String value) {
        carManager.displayRevenue(value);
    }

    
    /**
     * Adds a new user to the system.
     *
     * @param id The user's ID.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param moneyAvailable The amount of money the user has available.
     * @param carsPurchased The number of cars the user has purchased.
     * @param minerCarsMembership Indicates whether the user has a MinerCars membership.
     * @param username The username for the user's account.
     * @param password The password for the user's account.
     */
    public void addUser(int id, String firstName, String lastName, float moneyAvailable, int carsPurchased, boolean minerCarsMembership, String username, String password) {
    	User newUser = new User(id, firstName, lastName, moneyAvailable, carsPurchased, minerCarsMembership, username, password);
        userManager.addUser(newUser);
    }
}
import java.util.ArrayList;
import java.util.List;


/**
 * Manages user operations for a car shop application. This includes user authentication,
 * user data management, and integration with car management functionalities through {@link CarManager}.
 */
public class UserManager {
	/**
	 * A list of users managed by this class. T
	 */
	private List<User> users;

	/**
	 * A temporary session token that stores current user session details. This token is used to maintain session
	 * state across different operations within the system
	 */
	private String[] tempToken = null;

	/**
	 * An instance of CarManager used to handle car-related operations.
	 */
	private CarManager carManager;


    /**
     * Constructs a UserManager with reference to a CarManager.
     * Loads all user data from a CSV file upon instantiation.
     *
     * @param carManager The CarManager to be used for car-related operations linked with users.
     */
    public UserManager(CarManager carManager) {
        this.users = loadAllUsers.loadUsersFromCSV();
        this.carManager = carManager;
    }


    /**
     * Authenticates a user and initializes their session token.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                // Initialize the tempToken with user details
                tempToken = new String[]{
                        String.valueOf(user.getID()), 
                        user.getFirstName(), 
                        user.getLastName(), 
                        String.valueOf(user.getMoneyAvailable()), 
                        String.valueOf(user.getCarsPurchased()), 
                        String.valueOf(user.isMinerCarsMembership()), 
                        user.getUsername(), 
                        user.getPassword()
                };
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the current session token.
     * @return The current session token if available; null otherwise.
     */
    public String[] getTempToken() {
        return tempToken;
    }

    /**
     * Clears the session token when the user logs out or the session ends.
     */
    public void clearTempToken() {
        tempToken = null;
    }
    
    /**
     * Logs out the current user by clearing their session token.
     */
    public void logout() {
        System.out.println("User " + (tempToken != null ? tempToken[6] : "Unknown") + " signed out.");
        clearTempToken();
        // Any additional cleanup can be done here
    }
    
    
    /**
     * Finds a user by their username.
     * <p>
     * This method searches through the list of users and returns the user
     * object if a matching username is found. It is case-sensitive and expects
     * an exact match of the username. If no user is found with the specified
     * username, this method returns {@code null}.
     * 
     * @param username The username of the user to find.
     * @return User object if found, {@code null} otherwise.
     */
    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; 
    }

    
    /**
     * Saves the current state of all users to a CSV file, including any changes to user data.
     */
    public void saveUsersToCSV() {
        List<String[]> userDataWrite = new ArrayList<>();
        userDataWrite.add(loadAllUsers.header);  

        for (User user : users) {
            String[] userData = user.ArrayListToCSV();  
            userDataWrite.add(userData);  
        }

        CSVManager.updateCSV("user_data_part2.csv", userDataWrite);  
    }
    
    /**
     * Adds a new user to the system and saves the updated list of users to a CSV file.
     *
     * @param user The new User object to add to the system.
     */
    public void addUser(User user) {
        users.add(user);  // Add user to the list
        saveUsersToCSV(); // Update the CSV file
        System.out.println("User added: " + user.getUsername());
    }
    
    

    /**
     * Handles the process of returning a car. It finds the user and car, processes the refund,
     * and updates the car availability.
     *
     * @param username The username of the user returning the car.
     * @param carId The ID of the car being returned.
     * @return true if the car is successfully returned, false otherwise.
     */
    public boolean returnCar(String username, int carId) throws Exception {
        User user = findUserByUsername(username);
        if (user == null) {
            System.out.println("User not found.");
            return false;
        }
        
       
		Car car = carManager.findCarById(carId);
        
		if (car == null) {
            System.out.println("Car not found.");

            return true;
        }

		if (IssueTicket.deleteTicket(carId, username)) {
			float tax = (float) .0625;
			float refundAmount = car.getPrice() * (1+tax);
			user.setMoneyAvailable(user.getMoneyAvailable() + refundAmount);

	        
			user.setCarsPurchased(user.getCarsPurchased() > 0 ? user.getCarsPurchased() - 1 : 0);

        
			car.setCarsAvailable(car.getCarsAvailable() + 1);
		}
        


        // Save updated user and car data to CSV
        saveUsersToCSV();
        carManager.saveCarsToCSV();

   
        return true;
    }
}
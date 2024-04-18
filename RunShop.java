
import java.io.IOException;
import java.util.Scanner;


/**
 * The {@code RunShop} class implements a simple console-based car shop application.
 * It allows users to log in, view available cars, filter cars by condition (new or used),
 * purchase cars, and view their issued tickets for purchases. Car and user data are managed
 * through CSV files.
 */
public class RunShop {
    
	
	/**
	 * Manages all car-related operations including loading cars, processing car purchases, and managing inventory.
	 * This instance of {@link CarManager} is used throughout the application to interface with car data.
	 */
    private static CarManager carManager = new CarManager();
    
    /**
     * Manages user-related operations, user authentication and user data management.
     * This instance of {@link UserManager} is initialized with a reference to {@code carManager} to allow
     * user operations that may affect car data, such as car purchases.
     */
    private static UserManager userManager = new UserManager(carManager);
    
  
    
    /**
     * Represents the administrator panel that displays the main menu for the admins. 
     */
    private static AdminPanel adminPanel = new AdminPanel(carManager, userManager); // Ensure AdminPanel is initialized

 

    /**
     * The main method to run the car shop application. It handles user login and redirects
     * to the main menu upon successful authentication.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args){
        int tries = 0;
        Scanner scanner = new Scanner(System.in);
        final int MAX_TRIES = 3;
        
        
        System.out.print("Use Admin Panel? (yes/no): ");
        String useAdminPanel = scanner.nextLine().trim().toLowerCase();

  
        if (useAdminPanel.equals("yes")) {
            try {
   
                adminPanel.showMenu("admin");
                Log.log("Admin", "Accessed admin panel");
            } catch (Exception e) {
                System.out.println("Error accessing Admin Panel: " + e.getMessage());
            }
        }
        else {
        	System.out.println("Admin Panel skipped.");
        }
        
        while(tries < MAX_TRIES) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
    
            if (userManager.authenticateUser(username, password)) {
                System.out.println("Welcome " + username);
                Log.log(username, "logged in");
               
                
                mainmenu(username);
                break;
            } else {
                System.out.println("User information not found in system.");
            }
            
            tries++;
            
            if(tries > 0)
                System.out.println("Login attempts left: " + (MAX_TRIES - tries));
            
            if(tries == MAX_TRIES)
                System.out.println("Maximum login attempts achieved. Please try again some other time.");
                Log.log(username, "attempted to log in for more than 3 times");
        }
        
        scanner.close();
    }
    
 
    
    /**
     * Displays the main menu of the application and handles user navigation.
     * 
     * @param username The username of the currently logged-in user.
     */
    public static void mainmenu(String username) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Miner Cars main menu");
        System.out.println("Please input the corresponding number for the menu you wish to go to:");
        System.out.println("1. Display all cars.\n"
                + "2. Filter Cars (used / new)\n"
                + "    1) New\n"
                + "        (Print info)\n"
                + "    2) Used\n"
                + "        (Print info)\n"
                + "    3) Go back\n"
                + "3. Purchase a car\n"
                + "4. View Tickets\n"
                + "5. Sign out and Exit\n"
                + "6. Return Car\n"
                + (username.equals("admin") ? "7. Access Admin Panel\n" : ""));

        String input = scanner.nextLine();

        try {
        switch (input) {
        
        
        
            case "1":
                System.out.println("Displaying all cars...");
                carManager.printAllCars();
                Log.log(username, "printed all cars");
                mainmenu(username);
                break;
                
            case "2":
                System.out.println("Filter options:");
                System.out.println("1) New\n2) Used\n3) Go back");
                String filterInput = scanner.nextLine();
                
                switch (filterInput) {
                    case "1":
                        System.out.println("New Cars:");
                        carManager.printConditionCars("New");
                        Log.log(username, "printed new cars");
                        mainmenu(username);
                        break;
                        
                    case "2":
                        System.out.println("Used Cars:");
                        carManager.printConditionCars("Used");
                        Log.log(username, "printed used cars");
                        mainmenu(username);
                        break;
                        
                    case "3":
                        System.out.println("Going back to the main menu...");
                        mainmenu(username); 
                        break;
                        
                    default:
                        System.out.println("Invalid option");
                        mainmenu(username);
                        break;
                }
                break;
                
            case "3":
                System.out.println("Enter ID of car: ");
                String IDInput = scanner.nextLine();
                if(carManager.purchaseCar(username, IDInput, userManager)) {
                    System.out.println("Car Purchased. Thank you for your business!");
                    Log.log(username, "purchased a car");
                }
                else {
                    System.out.println("Unable to purchase the car");
                    Log.log(username,"failed to purchase a car");
                }
                mainmenu(username);
                break;
                
            case "4":
                System.out.println("Viewing tickets...");
                IssueTicket.viewTicketsForUser(username, "issued_tickets.csv");
                Log.log(username, "viewed tickets");
                mainmenu(username);
                break;
                
            case "5":

            	
                carManager.saveCarsToCSV();           
                userManager.saveUsersToCSV();                 
                userManager.logout();
                
                Log.log(username, "signed out");
                
                userManager.clearTempToken();
                
                break;
            case "6":
            	
            	System.out.println("Enter Car ID to return: ");
            	int carID = scanner.nextInt();
            	
            	
            	userManager.returnCar(username, carID);
            	Log.log(username, "Returned Car");
            	mainmenu(username);
            	break;
            	
            case "7":
                if (username.equals("admin")) {
                    adminPanel.showMenu("admin");
                    Log.log(username, "accessed admin panel");
                    mainmenu(username);
                } else {
                    System.out.println("Unauthorized access attempt to admin panel.");
                    mainmenu(username);
                }
                break;
                
            default:
                System.out.println("Invalid option. Please try again.");
                mainmenu(username);
                
                break;
        }
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numeric values.");
            mainmenu(username);
        }  catch (IOException e) {
                System.err.println("Failed to read input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            mainmenu(username);
        }
        

        scanner.close(); 
    }
    
 
    
}
import java.util.Scanner;



/**
 * The AdminPanel class provides an interface for administrative tasks such as managing cars and users.
 */
public class AdminPanel {

    private Admin admin;
    private Scanner scanner;

 
    

    /**
     * Constructor for AdminPanel.
     * Initializes a new instance of Admin with dependency injection for car and user managers.
     *
     * @param carManager A reference to the CarManager object managing car data.
     * @param userManager A reference to the UserManager object managing user data.
     */
    public AdminPanel(CarManager carManager, UserManager userManager) {
        this.admin = new Admin("admin", "admin", carManager, userManager);
    }
    
    
    /**
     * Retrieves boolean input from the user in response to a specific prompt. This method ensures that
     * the input is correctly parsed to a boolean by accepting only 'yes' or 'no' answers.
     *
     * @param prompt The prompt to display to the user, asking for a 'yes' or 'no' response.
     * @return The boolean value corresponding to the user's input ('yes' returns true, 'no' returns false).
     */
    private boolean getBooleanInput(String prompt) {
        System.out.println(prompt);
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if ("yes".equals(input)) {
                return true;
            } else if ("no".equals(input)) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    /**
     * Displays the main menu for the Admin Panel and handles user selection of administrative tasks.
     *
     * @param username The username of the administrator.
     * @throws Exception Generic exception to capture and handle any errors that occur during operation.
     */
    public void showMenu(String username) throws Exception {
        System.out.println("Accessing Admin Panel...");
        System.out.println("Select admin option: \n"
                + "1. Add Car\n"
                + "2. Get Revenue by Id and by Car Type \n"
                + "3. Remove Car\n"
                + "4. Add User\n"
                + "5. Main Menu");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                addCar(scanner);
                showMenu(username);
                break;
            case "2":
                getRevenue(scanner);
                showMenu(username);
                break;
            case "3":
                removeCar(scanner);
                showMenu(username);
                break;
            case "4":
                addUser(scanner);
                showMenu(username);
                break;
            case "5":
                RunShop.mainmenu(username);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                showMenu(username);
        }
    }

    /**
     * Adds a new car to the system based on user input. This method prompts the user for car details
     * and invokes the Admin's addCar method to add the car into the system.
     *
     * @throws NumberFormatException If the input for numeric fields (like price or capacity) is not a valid integer.
     */
    private void addCar(Scanner scanner) throws NumberFormatException {
        System.out.println("Enter Car Type:");
		String type = scanner.nextLine();
        System.out.println("Enter Model:");
        String model = scanner.nextLine();
        System.out.println("Enter Condition (new/used):");
        String condition = scanner.nextLine();
        System.out.println("Enter Color:");
        String color = scanner.nextLine();
        System.out.println("Enter Capacity:");
        int capacity = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Year:");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Fuel Type:");
        String fuelType = scanner.nextLine();
        System.out.println("Enter Transmission:");
        String transmission = scanner.nextLine();
        System.out.println("Enter VIN:");
        String vin = scanner.nextLine();
        System.out.println("Enter Price:");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Cars Available:");
        int carsAvailable = Integer.parseInt(scanner.nextLine());
        boolean hasTurbo = getBooleanInput("Has Turbo (yes/no):");
        admin.addCar(type, model, condition, color, capacity, year, fuelType, transmission, vin, price, carsAvailable, hasTurbo);
    }

    /**
     * Retrieves revenue information based on the car type or ID provided by the user.
     * This method utilizes the Admin's getRevenueByIDOrType to display revenue details.
     *
     * @throws NumberFormatException If the ID input is not a valid integer.
     */
    private void getRevenue(Scanner scanner) throws NumberFormatException {
        System.out.println("Enter Car Type or ID:");
        String identifier = scanner.nextLine();
        admin.getRevenueByIDOrType(identifier);
    }

    /**
     * Removes a car from the system based on the VIN provided by the user.
     * This method uses the Admin's removeCar method to delete the car from the inventory.
     *
     * @throws NumberFormatException If the VIN input is not a valid format.
     */
    private void removeCar(Scanner scanner) throws NumberFormatException {
        System.out.println("Enter the VIN of the car you wish to remove:");
        String removeVin = scanner.nextLine();
        admin.removeCar(removeVin);
    }

    /**
     * Adds a new user to the system based on user input. This method collects user details
     * and uses the Admin's addUser method to add the user into the system.
     *
     * @throws NumberFormatException If numeric inputs (like ID or money available) are not valid integers.
     */
    private void addUser(Scanner scanner) throws NumberFormatException {
        System.out.println("Enter User ID:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter First Name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter Last Name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter Money Available:");
        float moneyAvailable = Float.parseFloat(scanner.nextLine());
        System.out.println("Enter Cars Purchased:");
        int carsPurchased = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter MinerCars Membership (true/false):");
        System.out.println("Note: any input other than true will be false");
        boolean minerCarsMembership = Boolean.parseBoolean(scanner.nextLine());
        System.out.println("Enter Username:");
        String username_two = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        admin.addUser(id, firstName, lastName, moneyAvailable, carsPurchased, minerCarsMembership, username_two, password);
        System.out.println("User added successfully.");
    }
    



}

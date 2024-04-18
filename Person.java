/**
 * Represents a person with a first name and a last name.
 * This abstract class provides the framework for entities that have these attributes
 * and requires implementing classes to define how information about the person is displayed.
 */
public abstract class Person {
    
    /**
     * The first name of the person.
     */
    protected String name;

    /**
     * The last name of the person.
     */
    protected String lastName;

    /**
     * Constructs a new Person instance with the given first name and last name.
     * 
     * @param name The first name of the person.
     * @param lastName The last name of the person.
     */
    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }


    
    /**
     * Retrieves the first name of the person.
     *
     * @return The first name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the last name of the person.
     *
     * @return The last name of the person.
     */
    public String getLastName() {
        return lastName;
    }
}
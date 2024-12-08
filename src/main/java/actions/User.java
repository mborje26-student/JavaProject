package main.java.actions;

public class User {
    private int id; // User ID
    private String username;
    private String firstname;
    private String lastname;
    private int favoriteNumber;
    private String password;
    private String emailAddress;
    private DateOfBirth dateOfBirth;

    // Constructor
    // Below "this" is used as a keyword to refer to the object and assign the value of the instance variable
    public User(int id, String firstname, String lastname, int favoriteNumber, DateOfBirth dateOfBirth, String username, String password, String emailAddress) {
        this.id = id; // Use id passed from UserRegistration
        this.firstname = firstname;
        this.lastname = lastname;
        this.favoriteNumber = favoriteNumber;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }



    // Getters
    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getFavoriteNumber() {
        return favoriteNumber;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}

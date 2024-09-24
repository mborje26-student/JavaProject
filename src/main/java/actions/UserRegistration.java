package main.java.actions;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

// Class to handle date of birth input and validation
class DateOfBirth {
    private LocalDate dateOfBirth;

    public DateOfBirth(String dob) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfBirth = LocalDate.parse(dob, formatter);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateOfBirth.format(formatter);
    }


    public static DateOfBirth inputDateOfBirth(Scanner scanner) {
        while (true) {
            System.out.print("Enter your date of birth (dd/mm/yyyy): ");
            String dobInput = scanner.nextLine();

            try {
                return new DateOfBirth(dobInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please try again.");
            }
        }
    }
}
class User {
    private static int idCounter = 0; // Static counter for generating unique IDs

    private int id; // User ID
    private String username;
    private String firstname;

    private String lastname;
    private String password;
    private String emailAddress;
    private DateOfBirth dateOfBirth;

    // Constructor
    public User(String firstname, String lastname, DateOfBirth dateOfBirth, String username, String password, String emailAddress) {
        this.id = ++idCounter; // Increment and assign ID
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;

    }

//    public User(String username, String password, DateOfBirth dateOfBirth) {
//    }

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

public class UserRegistration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        System.out.println("Create New User Account!");
        // Form that will create all user details based on the information entered by user

        // Enter firstname
        System.out.print("Enter your firstname: ");
        String firstname = scanner.nextLine();

        // Enter lastname
        System.out.print("Enter your lastname: ");
        String lastname = scanner.nextLine();


        // Enter username
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();



        // Get and confirm the password
        String password = "";
        while (true) {
            System.out.print("Enter a password: ");
            password = scanner.nextLine();

            System.out.print("Confirm your password: ");
            String confirmPassword = scanner.nextLine();

            // Check if the passwords match
            if (password.equals(confirmPassword)) {
                break;
            } else {
                System.out.println("Passwords entered does not match. Please try again.");
            }
        }

        // Enter username
        String emailAddress = "";
        while (true) {
            System.out.print("Enter a emailAddress: ");
            emailAddress = scanner.nextLine();

            System.out.print("Confirm your emailAddress: ");
            String confirmEmailAddress = scanner.nextLine();

            // Check if the passwords match
            if (emailAddress.equals(confirmEmailAddress)) {
                break;
            } else {
                System.out.println("Email addresses entered does not match. Please try again.");
            }
        }


        // Get the date of birth
        DateOfBirth dateOfBirth = DateOfBirth.inputDateOfBirth(scanner);

        // Create a new user account
        User newUser = new User(username, password, dateOfBirth, firstname, lastname,emailAddress);

        // Save the user information in a file (in a specified location)
        saveUserToFile(newUser);

        System.out.println("\nRegistration Successful!");
        System.out.println("Account created with username: " + newUser.getUsername());
        System.out.println("Your account has been created!");

        scanner.close();
    }

    // Method to save user information to a file in a specific directory
    private static void saveUserToFile(User user) {
        try {
/*
            // Specify the directory where the file will be saved
            File directory = new File("C:/Users/SZ16M7/JavaProject/src/data/");  // 'data' directory inside the project folder

            // Create the directory if it does not exist
            if (!directory.exists()) {
                directory.mkdir();  // Create the 'data' folder
            }
*/

            // Create FileWriter with the specified path
            FileWriter writer = new FileWriter("C:/Users/SZ16M7/JavaProject/src/data/users.txt", true);  // Write to data/users.txt
            writer.write("ID: " + user.getId() + "|" +
                             "Username: " + user.getUsername() + "|" +
                             "1Password: " + user.getPassword() + "|" +
                             "DateOfBirth: " + user.getDateOfBirth() + "\n");

            writer.close();

            System.out.println("New user information has been saved to 'data/users.txt'.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the user information.");
            e.printStackTrace();
        }
    }
}

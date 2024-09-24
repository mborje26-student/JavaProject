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
            if (dobInput.trim().isEmpty()) {
                System.out.println("Date of birth cannot be empty! Please try again.");
                continue;
            }

            try {
                return new DateOfBirth(dobInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please try again.");
            }
        }
    }
}

// User class to store user information
class User {
    private static int idCounter = 0; // Static counter for generating unique IDs
    private int id; // User ID
    private String username;
    private String firstname;
    private String lastname;
    private int favoriteNumber;
    private String password;
    private String emailAddress;
    private DateOfBirth dateOfBirth;

    // Constructor
    public User(String firstname, String lastname, int favoriteNumber, DateOfBirth dateOfBirth, String username, String password, String emailAddress) {
        this.id = ++idCounter; // Increment and assign ID
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

public class UserRegistration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create New User Account!");

        // Enter first name
        String firstname = inputMandatoryField(scanner, "Enter your firstname: ");

        // Enter last name
        String lastname = inputMandatoryField(scanner, "Enter your lastname: ");

        // Enter favorite number between 1-10
        int favoriteNumber = inputFavoriteNumber(scanner);

        // Enter username
        String username = inputMandatoryField(scanner, "Enter a username: ");

        // Get and confirm the password
        String password = inputConfirmedPassword(scanner);

        // Enter email address
        String emailAddress = inputConfirmedEmail(scanner);

        // Get the date of birth
        DateOfBirth dateOfBirth = DateOfBirth.inputDateOfBirth(scanner);

        // Create a new user account
        User newUser = new User(firstname, lastname, favoriteNumber, dateOfBirth, username, password, emailAddress);

        // Save the user information in a file (in a specified location)
        saveUserToFile(newUser);

        System.out.println("\nRegistration Successful!");
        System.out.println("Account created with username: " + newUser.getUsername());
        System.out.println("Your account has been created!");

        scanner.close();
    }

    // Method to input a mandatory field
    private static String inputMandatoryField(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("This field cannot be empty! Please try again.");
            }
        } while (input.trim().isEmpty());
        return input;
    }

    // Method to input favorite number and validate input
    private static int inputFavoriteNumber(Scanner scanner) {
        int favoriteNumber = 0;
        while (true) {
            System.out.print("Enter your favorite number between 1-10: ");
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Favorite number cannot be empty! Please try again.");
                continue;
            }

            try {
                favoriteNumber = Integer.parseInt(input);
                // Check if the number is in the range 1-10
                if (favoriteNumber >= 1 && favoriteNumber <= 10) {
                    break; // Valid input, exit loop
                } else {
                    System.out.println("Please enter a number between 1 and 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
        return favoriteNumber;
    }

    // Method to get and confirm the password
    private static String inputConfirmedPassword(Scanner scanner) {
        String password;
        while (true) {
            System.out.print("Enter a password: ");
            password = scanner.nextLine();
            if (password.trim().isEmpty()) {
                System.out.println("Password cannot be empty! Please try again.");
                continue;
            }

            System.out.print("Confirm your password: ");
            String confirmPassword = scanner.nextLine();
            if (confirmPassword.trim().isEmpty()) {
                System.out.println("Confirmation password cannot be empty! Please try again.");
                continue;
            }

            // Check if the passwords match
            if (password.equals(confirmPassword)) {
                return password;
            } else {
                System.out.println("Passwords entered do not match. Please try again.");
            }
        }
    }

    // Method to get and confirm the email address
    private static String inputConfirmedEmail(Scanner scanner) {
        String emailAddress;
        while (true) {
            System.out.print("Enter an email address: ");
            emailAddress = scanner.nextLine();
            if (emailAddress.trim().isEmpty()) {
                System.out.println("Email address cannot be empty! Please try again.");
                continue;
            }

            System.out.print("Confirm your email address: ");
            String confirmEmailAddress = scanner.nextLine();
            if (confirmEmailAddress.trim().isEmpty()) {
                System.out.println("Confirmation email address cannot be empty! Please try again.");
                continue;
            }

            // Check if the email addresses match
            if (emailAddress.equals(confirmEmailAddress)) {
                return emailAddress;
            } else {
                System.out.println("Email addresses entered do not match. Please try again.");
            }
        }
    }

    // Method to save user information to a file in a specific directory
    private static void saveUserToFile(User user) {
        try {
            // Create FileWriter with the specified path
            FileWriter writer = new FileWriter("C:/Users/SZ16M7/JavaProject/JavaProject/src/data/users.txt", true);  // Write to data/users.txt
            writer.write("ID: " + user.getId() + "|" +
                    "Username: " + user.getUsername() + "|" +
                    "Password: " + user.getPassword() + "|" +
                    "EmailAddress: " + user.getEmailAddress() + "|" +
                    "FavoriteNumber: " + user.getFavoriteNumber() + "|" +
                    "DateOfBirth: " + user.getDateOfBirth().getFormattedDate() + "\n");

            writer.close();
            System.out.println("New user information has been saved to 'data/users.txt'.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the user information.");
            e.printStackTrace();
        }
    }
}

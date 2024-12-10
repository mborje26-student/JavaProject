package main.java.actions;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Scanner;

public class UserRegistration extends User {

    private static final String ID_FILE_PATH = "C:/Users/nique/JavaProject (2)/JavaProject/JavaProject/src/data/userIdCounter.txt";
    private static final String USER_FILE_PATH = "C:/Users/nique/JavaProject (2)/JavaProject/JavaProject/src/data/users.txt";



    // Constructor to initialize fields using the User class constructor
    public UserRegistration(int id, String firstname, String lastname, int favoriteNumber,
                            DateOfBirth dateOfBirth, String username, String password, String emailAddress) {
        super(id, firstname, lastname, favoriteNumber, dateOfBirth, username, password, emailAddress);
    }

    public int getId(){
        return super.getId();
    }

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

        // Load current ID counter from the file
        int id = loadCurrentId();

        // Create a new user account with the current ID using the inherited User constructor
        UserRegistration newUser = new UserRegistration(id, firstname, lastname, favoriteNumber,
                dateOfBirth, username, password, emailAddress);

        // Save the user information in a file
        saveUserToFile(newUser);

        // Increment and save the next ID to the file
        saveNextId(id + 1);

        System.out.println("\nRegistration Successful!");
        System.out.println("Account created with username: " + newUser.getUsername());
        System.out.println("Your account has been created!");

        scanner.close();
    }

    // Other methods remain unchanged


    // Method to input a mandatory field
    public static String inputMandatoryField(Scanner scanner, String prompt) {
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
                if (favoriteNumber >= 1 && favoriteNumber <= 10) {
                    break;
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
            if (password.trim().isEmpty()) {                               // use of trim() eliminates the whitespaces on both at the start and end of a string and isEmpty() checks if string is empty or not
                System.out.println("Password cannot be empty! Please try again.");
                continue;
            }

            System.out.print("Confirm your password: ");
            String confirmPassword = scanner.nextLine();
            if (confirmPassword.trim().isEmpty()) {
                System.out.println("Confirmation password cannot be empty! Please try again.");
                continue;
            }

            if (password.equals(confirmPassword)) {  //equals compares the String value of password and confirmPassword
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

            // Check if the email already exists in users.txt
            if (isEmailAlreadyRegistered(emailAddress)) {
                System.out.println("This email address is already registered. Please use a different email.");
                continue; // Ask for a different email
            }

            System.out.print("Confirm your email address: ");
            String confirmEmailAddress = scanner.nextLine();
            if (confirmEmailAddress.trim().isEmpty()) {
                System.out.println("Confirmation email address cannot be empty! Please try again.");
                continue;
            }

            if (emailAddress.equals(confirmEmailAddress)) {
                return emailAddress; // Valid email and confirmed
            } else {
                System.out.println("Email addresses entered do not match. Please try again.");
            }
        }
    }

    // Helper method to check if email is already registered in the file
    private static boolean isEmailAlreadyRegistered(String emailAddress) {
        File userFile = new File("C:/Users/nique/JavaProject (2)/JavaProject/JavaProject/src/data/users.txt"); // Path to users.txt
        if (!userFile.exists()) {
            return false; // If file doesn't exist, no email can be registered
        }

        try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming the email is stored in the format: "EmailAddress: email@domain.com"
                if (line.contains("EmailAddress: " + emailAddress)) {
                    return true; // Email is found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Email is not found
    }


    // Method to save user information to a file
    private static void saveUserToFile(User user) {
        try (FileWriter writer = new FileWriter(USER_FILE_PATH, true)) {
            LocalTime localTime = LocalTime.now();
            writer.write("ID: " + user.getId() + "|" +
                    "Username: " + user.getUsername() + "|" +
                    "Password: " + user.getPassword() + "|" +
                    "EmailAddress: " + user.getEmailAddress() + "|" +
                    "FavoriteNumber: " + user.getFavoriteNumber() + "|" +
                    "DateOfBirth: " + user.getDateOfBirth().getFormattedDate() + "|" +
                    "CreatedOn: " + localTime + "\n");
            System.out.println("New user information has been saved to 'data/users.txt'.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the user information.");
            e.printStackTrace();
        }
    }

    // Method to load the current ID from the file
    private static int loadCurrentId() {
        try {
            File idFile = new File(ID_FILE_PATH);
            if (!idFile.exists()) {
                saveNextId(1); // If the file does not exist, initialize the ID to 1
                return 1;
            }

            String content = new String(Files.readAllBytes(Paths.get(ID_FILE_PATH)));
            return Integer.parseInt(content.trim());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 1; // Default to 1 if there's an error
        }
    }

    // Method to save the next ID to the file
    private static void saveNextId(int nextId) {
        try (FileWriter writer = new FileWriter(ID_FILE_PATH, false)) {
            writer.write(Integer.toString(nextId));
        } catch (IOException e) {
            System.out.println("An error occurred while saving the next user ID.");
            e.printStackTrace();
        }
    }
}

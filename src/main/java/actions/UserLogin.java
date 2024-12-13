package main.java.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;


interface LoginService {
    String login(Scanner scanner); // Non-static

    static void defaultMessage() {
        System.out.println("Default Login Service Message.");
    }
}

public class UserLogin implements LoginService {

    // Overloaded constructors for demonstration
    public UserLogin() {
        System.out.println("Enter you username and password to login");
    }
    public UserLogin(String message) {
        System.out.println(message);
    }

    // Method to log in a user
    @Override
    public String login(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (this.isUserValid(username, password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
            return username;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }

    // Method to check if the username and password are valid
    private boolean isUserValid(String username, String password) {
        File userFile = new File("C:/Users/nique/JavaProject (2)/JavaProject/JavaProject/src/data/users.txt");
        if (!userFile.exists()) {
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split("\\|");
                String fileUsername = null;
                String filePassword = null;

                for (String detail : userDetails) {
                    if (detail.startsWith("Username: ")) {
                        fileUsername = detail.substring("Username: ".length());
                    }
                    if (detail.startsWith("Password: ")) {
                        filePassword = detail.substring("Password: ".length());
                    }
                }

                if (fileUsername != null && filePassword != null) {
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    // Enum for User Roles
    enum UserRole {
        ADMIN, USER, GUEST
    }

    // Custom immutable type
    record User(String username, String password, UserRole role) {}

    // Main method to test the login functionality
    public static void main(String[] args) {
        UserLogin userLogin = new UserLogin("Welcome to the enhanced User Login System");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your credentials.");

        String loggedInUser = userLogin.login(scanner);
        if (loggedInUser != null) {
            System.out.println("You are now logged in as: " + loggedInUser);

            // Example of Lambda and Predicate
            Predicate<String> isAdmin = username -> username.equalsIgnoreCase("admin");
            if (isAdmin.test(loggedInUser)) {
                System.out.println("You have ADMIN privileges.");
            } else {
                System.out.println("You are a regular user.");
            }

            // Example of defensive copying with List
            List<User> users = new ArrayList<>();
            users.add(new User("admin", "password123", UserRole.ADMIN));
            users.add(new User("user", "user123", UserRole.USER));
            users.add(new User("guest", "guest123", UserRole.GUEST));

            //unmodifiable copy of users list
            List<User> copiedUsers = List.copyOf(users);


            // Start the game after login
            StartGame.startGame(scanner, "Pumpkin");

        } else {
            System.out.println("Login attempt failed. Please try again.");
        }

        scanner.close();
    }
}

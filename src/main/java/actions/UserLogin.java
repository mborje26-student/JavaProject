package main.java.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserLogin {

    // Method to log in a user
    public static String login(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Check if the username and password match in the users.txt file
        if (isUserValid(username, password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
            return username;
        } else {
            System.out.println("Invalid username or password.");
            return null;  // Return null if login fails
        }
    }

    // Method to check if the username and password are valid
    private static boolean isUserValid(String username, String password) {
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
}

package main.java.actions;

import java.io.*;
import java.util.Scanner;

public class SelectPet {
    private static String petName;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an instance of UserLogin
        UserLogin userLogin = new UserLogin();

        // Check if the user successfully logs in before proceeding to pet selection
        String username = userLogin.login(scanner); // Call login on the instance
        if (username != null) {
            if (hasExistingPet(username)) {
                loadExistingPet(username);
            } else {
                selectPet(scanner, username);
            }

            // Start the game after pet selection or loading an existing pet
            StartGame.startGame(scanner, petName); // Now passing the petName

        } else {
            System.out.println("Login failed. Please try again.");
        }

        scanner.close();
    }


    // Method to allow the user to select a pet
    public static void selectPet(Scanner scanner, String username) {
        System.out.println("Please select a pet from the options below:");

        String[] petOptions = {
                "1. Dog",
                "2. Cat",
                "3. Rabbit",
                "4. Hamster",
                "5. Bird"
        };

        // Display pet options
        for (String option : petOptions) {
            System.out.println(option);
        }

        // Get user's selection
        int selectedPet = -1;
        while (selectedPet < 1 || selectedPet > petOptions.length) {
            System.out.print("Enter the number corresponding to your preferred pet: ");
            String input = scanner.nextLine();
            try {
                selectedPet = Integer.parseInt(input);
                if (selectedPet >= 1 && selectedPet <= petOptions.length) {
                    System.out.println("You have selected: " + petOptions[selectedPet - 1].substring(3));
                    petName = inputMandatoryField(scanner, "Enter pet name: ");
                    saveUserPet(username, petOptions[selectedPet - 1].substring(3), petName);
                } else {
                    System.out.println("Invalid selection. Please select a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    // Add a public getter to return the petName
    public static String getPetName() {
        return petName;
    }

    // Method to input mandatory field
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

    // Method to save the user's selected pet into petProfile.txt
    private static void saveUserPet(String username, String pet, String petName) {
        File petFile = new File("C:/Users/nique/JavaProject (2)/JavaProject/JavaProject/src/data/petProfile.txt");
        try (FileWriter writer = new FileWriter(petFile, true)) {  // Append mode
            writer.write("Username: " + username + " | Selected Pet: " + pet + " | Pet name: " + petName + "\n");
            System.out.println("Your selection has been saved to 'petProfile.txt'.");

        } catch (IOException e) { // Checked type exception
            System.out.println("An error occurred while saving the pet selection.");
            e.printStackTrace();
        }
    }

    // Method to check if the user has an existing pet in petProfile.txt
    private static boolean hasExistingPet(String username) {
        File petFile = new File("C:/Users/nique/JavaProject (2)/JavaProject/JavaProject/src/data/petProfile.txt");
        if (!petFile.exists()) {
            return false; // If the file doesn't exist, no pet has been registered
        }

        try (BufferedReader br = new BufferedReader(new FileReader(petFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Username: " + username)) {
                    return true; // If the username exists in petProfile.txt, the user has an existing pet
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // User has no existing pet
    }

    // Method to display the user's existing pet from petProfile.txt
    private static void loadExistingPet(String username) {
        File petFile = new File("C:/Users/nique/JavaProject (2)/JavaProject/JavaProject/src/data/petProfile.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(petFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Username: " + username)) {
                    // Extract and display the pet details
                    String[] userPetDetails = line.split("\\|");
                    for (String detail : userPetDetails) {
                        if (detail.trim().startsWith("Selected Pet")) {
                            System.out.println(detail.trim()); // Display the selected pet
                        }
                        if (detail.trim().startsWith("Pet name")) {
                            System.out.println(detail.trim()); // Display the pet name
                            petName = detail.split(":")[1].trim(); // Set the petName
                        }
                    }
                    return; // Stop searching once the pet information is found
                }
            }
            // If username is not found, throw a NullPointerException - Unchecked  type exception
            throw new NullPointerException("Username not found in petProfile.txt: " + username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package main.java.actions;

import java.util.Arrays;
import java.util.Scanner;

public class StartGame {
    public StartGame() {
        System.out.println("Default StartGame Constructor");
    }

    public StartGame(String customMessage) {
        System.out.println("Custom Message: " + customMessage);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Assume the petName is being passed from the SelectPet class
        System.out.println("Welcome to the Pet Adventure Game!");

        // Start the game for the user with their pet
        String petName = SelectPet.getPetName();  // Assuming this method exists in SelectPet class
        startGame(scanner, petName);

        scanner.close();
    }

    // Method to start the game, assuming the user has already selected a pet
    public static void startGame(Scanner scanner, String petName) {
        PetMonitor petMonitor = new PetMonitor(); // Initialize the pet monitor

        System.out.println("Let's begin the adventure with your pet " + petName + "!");

        // Provide some simple game options
        String[] gameOptions = {
                "1. Feed " + petName,
                "2. Play with " + petName,
                "3. Give water to " + petName,
                "4. Take " + petName + " to potty",
                "5. Give a treat to " + petName,
                "6. Exit the game"
        };

        boolean gameRunning = true;

        while (gameRunning) {
            // Display the game options
            System.out.println("What would you like to do?");

            // Use a method reference with a stream
            Arrays.stream(gameOptions).forEach(System.out::println);

            // Get the user's choice
            System.out.print("Enter your choice: \n");
            System.out.print("************************\n");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Ask the user if they want to specify an amount for feeding
                    System.out.print("Enter amount to feed (or press Enter for default): ");
                    String feedAmountInput = scanner.nextLine();
                    if (!feedAmountInput.isEmpty()) {
                        int feedAmount = Integer.parseInt(feedAmountInput);
                        PetMonitor.feed(feedAmount); // Use the overloaded feed method
                    } else {
                        PetMonitor.feed(); // Use the default feed method
                    }
                    break;

                case "2":
                    // Ask the user if they want to specify amounts for playing
                    System.out.print("Enter attention increase (or press Enter for default): ");
                    String attentionInput = scanner.nextLine();
                    System.out.print("Enter happiness increase (or press Enter for default): ");
                    String happinessInput = scanner.nextLine();

                    if (!attentionInput.isEmpty() && !happinessInput.isEmpty()) {
                        int attentionIncrease = Integer.parseInt(attentionInput);
                        int happinessIncrease = Integer.parseInt(happinessInput);
                        PetMonitor.play(); // Use the overloaded play method
                    } else {
                        PetMonitor.play(); // Use the default play method
                    }
                    break;

                case "3":
                    // Ask the user if they want to specify an amount for giving water
                    System.out.print("Enter amount to give water (or press Enter for default): ");
                    String waterAmountInput = scanner.nextLine();
                    if (!waterAmountInput.isEmpty()) {
                        int waterAmount = Integer.parseInt(waterAmountInput);
                        PetMonitor.giveWater(waterAmount); // Pass the amount using varargs
                    } else {
                        PetMonitor.giveWater(); // No arguments passed, uses the default value in varargs method
                    }
                    break;


                case "4":
                    PetMonitor.potty();
                    break;

                case "5":
                    PetMonitor.giveTreat();
                    break;

                case "6":
                    System.out.println("Exiting the game. Goodbye!");
                    System.out.println("  / \\__\n" +
                            " (    @\\___\n" +
                            " /         O\n" +
                            "/   (_____/\n" +
                            "/_____/   U\n");
                    PetMonitor.stopMonitor();
                    gameRunning = false;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }


            // Check if pet is in critical condition
            if (PetMonitor.isCriticalCondition()) {
                System.out.println("Your pet is in critical condition! Please attend to them immediately!");
            }
        }
    }
}
//class PetMonitor {
//
//    public void feed() {
//        System.out.println("Pet has been fed with the default amount.");
//    }
//
//    public void feed(int amount) {
//        System.out.println("Pet has been fed with " + amount + " units of food.");
//    }
//
//    public void play() {
//        System.out.println("You played with your pet using default settings.");
//    }
//
//    public void play(int attentionIncrease, int happinessIncrease) {
//        System.out.println("You played with your pet. Attention: " + attentionIncrease + ", Happiness: " + happinessIncrease);
//    }
//
//    public void giveWater() {
//        System.out.println("Pet has been given water with the default amount.");
//    }
//
//    public void giveWater(int amount) {
//        System.out.println("Pet has been given " + amount + " units of water.");
//    }
//
//    public void potty() {
//        System.out.println("Pet has gone to potty.");
//    }
//
//    public void giveTreat() {
//        System.out.println("Pet has received a treat.");
//    }
//
//    public boolean isCriticalCondition() {
//        return false; // Placeholder implementation
//    }
//
//    public void stopMonitor() {
//        System.out.println("Pet monitor stopped.");
//    }
//}

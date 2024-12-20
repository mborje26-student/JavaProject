package main.java.actions;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

// Encapsulation - Private fields cannot be directly accessed or modified
public class PetMonitor {
    private static int hungerLevel;
    private static int attentionLevel;
    private static int thirstLevel;
    private static int bladderLevel;
    private static int happinessLevel;

    private static final int MAX_LEVEL = 5;
    private static final int MIN_LEVEL = 1;

    private static boolean decayPaused = false;  // Flag to control whether the decay is paused or not
    private static Timer timer;

    // Constructor to initialize the pet's levels
    public PetMonitor() {
        hungerLevel = MAX_LEVEL;
        attentionLevel = MAX_LEVEL;
        thirstLevel = MAX_LEVEL;
        bladderLevel = MAX_LEVEL;
        happinessLevel = MAX_LEVEL;

        startLevelDecay();
    }

    //Public methods to prevent code from making changes to the fields
    // Method to simulate feeding the pet with overloading
    public static void feed() {
        hungerLevel = Math.min(hungerLevel + 2, MAX_LEVEL);
        System.out.println("Feeding the pet. Hunger level is now: " + hungerLevel);
        pauseDecay();
    }

    // Overloaded method to feed the pet by a specified amount
    public static void feed(int amount) {
        hungerLevel = Math.min(hungerLevel + amount, MAX_LEVEL);
        System.out.println("Feeding the pet by " + amount + ". Hunger level is now: " + hungerLevel);
        pauseDecay();
    }


    // Method to play with the pet
    public static void play() {
        attentionLevel = Math.min(attentionLevel + 2, MAX_LEVEL);
        happinessLevel = Math.min(happinessLevel + 2, MAX_LEVEL);
        System.out.println("Playing with the pet. Attention level is now: " + attentionLevel);
        System.out.println("Happiness level is now: " + happinessLevel);
        pauseDecay();
    }

    // Overloaded method to play with specified attention and happiness increments
    public void play(int attentionIncrease, int happinessIncrease) {
        attentionLevel = Math.min(attentionLevel + attentionIncrease, MAX_LEVEL);
        happinessLevel = Math.min(happinessLevel + happinessIncrease, MAX_LEVEL);
        System.out.println("Playing with the pet. Attention level is now: " + attentionLevel);
        System.out.println("Happiness level is now: " + happinessLevel);
        pauseDecay();
    }

    // Method to give water using varargs
    public static void giveWater(int... amounts) {
        int waterAmount = 2;  // Default amount of water

        // If an amount is provided, use the first value
        if (amounts.length > 0) {
            waterAmount = amounts[0];
        }

        // Update the thirst level
        thirstLevel = Math.min(thirstLevel + waterAmount, MAX_LEVEL);
        System.out.println("Giving water by " + waterAmount + ". Thirst level is now: " + thirstLevel);

        // Pause decay (not shown in the code)
        pauseDecay();
    }

    // Method to let the pet go potty
    public static void potty() {
        bladderLevel = Math.min(bladderLevel + 2, MAX_LEVEL); // Reset bladder level after potty
        System.out.println("The pet went potty. Bladder level is now: " + bladderLevel);
        pauseDecay();  // Pause the decay after an action
    }

    // Method to give the pet a treat
    public static void giveTreat() {
        happinessLevel = Math.min(happinessLevel + 2, MAX_LEVEL);
        System.out.println("Giving a treat to the pet. Happiness level is now: " + happinessLevel);
        pauseDecay();  // Pause the decay after an action
    }

    // Method to start decreasing levels over time
    private void startLevelDecay() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!decayPaused) {
                    // Decrease the levels periodically
                    hungerLevel = Math.max(hungerLevel - 1, MIN_LEVEL);
                    attentionLevel = Math.max(attentionLevel - 1, MIN_LEVEL);
                    thirstLevel = Math.max(thirstLevel - 1, MIN_LEVEL);
                    bladderLevel = Math.max(bladderLevel - 1, MIN_LEVEL);
                    happinessLevel = Math.max(happinessLevel - 1, MIN_LEVEL);

                    // Display the current levels
                    displayPetStatus();

                    // Check for critical conditions
                    if (isCriticalCondition()) {
                        System.out.println("Warning: Your pet is in critical condition!");
                    }
                }
            }
        }, 0, 10000); // Decay every 5 seconds for demonstration purposes
    }

    // Method to display the current levels
    private void displayPetStatus() {
        System.out.println("\n--- Pet Status ---");
        System.out.println("[1] Hunger Level: " + hungerLevel);
        System.out.println("[2] Attention Level: " + attentionLevel);
        System.out.println("[3] Thirst Level: " + thirstLevel);
        System.out.println("[4] Bladder Level: " + bladderLevel);
        System.out.println("[5] Happiness Level: " + happinessLevel);
        System.out.println("------------------\n");
    }

    // Method to pause the level decay
    private static void pauseDecay() {
        decayPaused = true;
        System.out.println("Decay paused. Awaiting your response...");

        // Restart the decay after user input (simulate a wait)
        Scanner scanner = new Scanner(System.in);

        System.out.print("Press Enter to resume the game... ");
        scanner.nextLine();

        decayPaused = false;
        System.out.println("Decay resumed.");
    }

    // Method to check if the pet is in critical condition (i.e., any level is at its minimum)
    public static boolean isCriticalCondition() {
        return hungerLevel == MIN_LEVEL || attentionLevel == MIN_LEVEL || thirstLevel == MIN_LEVEL || happinessLevel == MIN_LEVEL;
    }

    // Method to stop the pet monitor (optional for stopping the Timer)
    public static void stopMonitor() {
        System.out.println("Stopping the pet monitor.");
        timer.cancel();
        System.exit(0);
    }
}

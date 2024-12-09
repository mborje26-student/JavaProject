//package main.java.actions;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class PetMonitor {
//    private int hungerLevel;
//    private int attentionLevel;
//    private int thirstLevel;
//    private int bladderLevel;
//    private int happinessLevel;
//
//    private static final int MAX_LEVEL = 10;
//    private static final int MIN_LEVEL = 1;
//
//    // Constructor to initialize the pet's levels
//    public PetMonitor() {
//        hungerLevel = MAX_LEVEL;
//        attentionLevel = MAX_LEVEL;
//        thirstLevel = MAX_LEVEL;
//        bladderLevel = MIN_LEVEL;
//        happinessLevel = MAX_LEVEL;
//
//        // Automatically start decreasing levels over time
//        startLevelDecay();
//    }
//
//    // Method to simulate feeding the pet
//    public void feed() {
//        hungerLevel = Math.min(hungerLevel + 2, MAX_LEVEL);
//        System.out.println("Feeding the pet. Hunger level is now: " + hungerLevel);
//    }
//
//    // Method to play with the pet
//    public void play() {
//        attentionLevel = Math.min(attentionLevel + 2, MAX_LEVEL);
//        happinessLevel = Math.min(happinessLevel + 2, MAX_LEVEL);
//        System.out.println("Playing with the pet. Attention level is now: " + attentionLevel);
//        System.out.println("Happiness level is now: " + happinessLevel);
//    }
//
//    // Method to give water to the pet
//    public void giveWater() {
//        thirstLevel = Math.min(thirstLevel + 2, MAX_LEVEL);
//        System.out.println("Giving water to the pet. Thirst level is now: " + thirstLevel);
//    }
//
//    // Method to let the pet go potty
//    public void potty() {
//        bladderLevel = MIN_LEVEL; // Reset bladder level after potty
//        System.out.println("The pet went potty. Bladder level is now: " + bladderLevel);
//    }
//
//    // Method to give the pet a treat
//    public void giveTreat() {
//        happinessLevel = Math.min(happinessLevel + 2, MAX_LEVEL);
//        System.out.println("Giving a treat to the pet. Happiness level is now: " + happinessLevel);
//    }
//
//    // Method to start decreasing levels over time
//    private void startLevelDecay() {
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                // Decrease the levels periodically
//                hungerLevel = Math.max(hungerLevel - 1, MIN_LEVEL);
//                attentionLevel = Math.max(attentionLevel - 1, MIN_LEVEL);
//                thirstLevel = Math.max(thirstLevel - 1, MIN_LEVEL);
//                bladderLevel = Math.min(bladderLevel + 1, MAX_LEVEL); // Bladder level increases over time
//                happinessLevel = Math.max(happinessLevel - 1, MIN_LEVEL);
//
//                // Display the current levels
//                displayPetStatus();
//            }
//        }, 0, 5000); // Decay every 5 seconds for demonstration purposes
//    }
//
//    // Method to display the current levels
//    private void displayPetStatus() {
//        System.out.println("\n--- Pet Status ---");
//        System.out.println("Hunger Level: " + hungerLevel);
//        System.out.println("Attention Level: " + attentionLevel);
//        System.out.println("Thirst Level: " + thirstLevel);
//        System.out.println("Bladder Level: " + bladderLevel);
//        System.out.println("Happiness Level: " + happinessLevel);
//        System.out.println("------------------\n");
//    }
//
//    // Method to check if the pet is in critical condition (i.e., any level is at its minimum)
//    public boolean isCriticalCondition() {
//        return hungerLevel == MIN_LEVEL || attentionLevel == MIN_LEVEL || thirstLevel == MIN_LEVEL || happinessLevel == MIN_LEVEL;
//    }
//
//    // Method to stop the pet monitor (optional for stopping the Timer)
//    public void stopMonitor() {
//        System.out.println("Stopping the pet monitor.");
//        System.exit(0);
//    }
//}







package main.java.actions;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

// Encapsulation - Private fields cannot be directly accessed or modified
public class PetMonitor {
    private int hungerLevel;
    private int attentionLevel;
    private int thirstLevel;
    private int bladderLevel;
    private int happinessLevel;

    private static final int MAX_LEVEL = 5;
    private static final int MIN_LEVEL = 1;

    private boolean decayPaused = false;  // Flag to control whether the decay is paused or not
    private Timer timer;

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
    public void feed() {
        hungerLevel = Math.min(hungerLevel + 2, MAX_LEVEL);
        System.out.println("Feeding the pet. Hunger level is now: " + hungerLevel);
        pauseDecay();
    }

    // Overloaded method to feed the pet by a specified amount
    public void feed(int amount) {
        hungerLevel = Math.min(hungerLevel + amount, MAX_LEVEL);
        System.out.println("Feeding the pet by " + amount + ". Hunger level is now: " + hungerLevel);
        pauseDecay();
    }


    // Method to play with the pet
    public void play() {
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
    public void giveWater(int... amounts) {

        int waterAmount = 2;

        if (amounts.length > 0) {
            waterAmount = amounts[0];
        }

        thirstLevel = Math.min(thirstLevel + waterAmount, MAX_LEVEL);
        System.out.println("Giving water by " + waterAmount + ". Thirst level is now: " + thirstLevel);

        pauseDecay();
    }

    // Method to let the pet go potty
    public void potty() {
        bladderLevel = Math.min(bladderLevel + 2, MAX_LEVEL); // Reset bladder level after potty
        System.out.println("The pet went potty. Bladder level is now: " + bladderLevel);
        pauseDecay();  // Pause the decay after an action
    }

    // Method to give the pet a treat
    public void giveTreat() {
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
    private void pauseDecay() {
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
    public boolean isCriticalCondition() {
        return hungerLevel == MIN_LEVEL || attentionLevel == MIN_LEVEL || thirstLevel == MIN_LEVEL || happinessLevel == MIN_LEVEL;
    }

    // Method to stop the pet monitor (optional for stopping the Timer)
    public void stopMonitor() {
        System.out.println("Stopping the pet monitor.");
        timer.cancel();
        System.exit(0);
    }
}

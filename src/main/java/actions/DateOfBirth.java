package main.java.actions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateOfBirth {
    private final LocalDate dateOfBirth;

    public DateOfBirth(String dob) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfBirth = LocalDate.parse(dob, formatter);
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

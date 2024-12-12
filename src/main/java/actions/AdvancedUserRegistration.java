package main.java.actions;

import java.util.function.Predicate;

public class AdvancedUserRegistration extends UserRegistration implements UserActions {
    public AdvancedUserRegistration(int id, String firstname, String lastname, int favoriteNumber,
                                    DateOfBirth dateOfBirth, String username, String password, String emailAddress) {
        super(id, firstname, lastname, favoriteNumber, dateOfBirth, username, password, emailAddress);
    }

    @Override
    public void login(String username, String password) {
        Predicate<String> validCredentials = cred -> cred != null && !cred.isEmpty();
        if (validCredentials.test(username) && validCredentials.test(password)) {
            System.out.println("User " + username + " logged in successfully!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    public static void main(String[] args) {
        DateOfBirth dob = new DateOfBirth("23/01/1996");
        AdvancedUserRegistration user = new AdvancedUserRegistration(
                1, "Mon", "Bor", 7, dob, "monique", "password123", "monique@example.com");

        user.login("monique", "password123");
        user.logout();
        UserActions.resetPassword("monique@example.com");
        UserType.displayUserTypes();
    }
}

package main.java.actions;

public interface UserActions {
    void login(String username, String password);

    default void logout() {
        System.out.println("Logging out...");
    }

    static void resetPassword(String email) {
        System.out.println("Reset link sent to: " + email);
    }
}

package main.java.actions;

public enum UserType {
    ADMIN("Administrator with full access"),
    USER("Regular user with limited access"),
    GUEST("Guest user with view-only access");

    private final String description;

    UserType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void displayUserTypes() {
        System.out.println("Available user types:");
        for (UserType type : UserType.values()) {
            System.out.println(type + ": " + type.getDescription());
        }
    }
}

package main.java.window.models;

public class LoggedInUser {

    private static String firstName;
    private static String lastName;
    private static String email;
    private static int userId;

    

    public static void clear() {
        firstName = null;
        lastName = null;
        email = null;
        userId = 0;
    }

    public static void setUser(String fName, String lName, String userEmail) {
        firstName = fName;
        lastName = lName;
        email = userEmail;
    }

    public static void setUserId(int id) {
        userId = id;
    }

    public static String getFirstName() { return firstName; }
    public static String getLastName() { return lastName; }
    public static String getEmail() { return email; }
    public static int getUserId() { return userId; }
}

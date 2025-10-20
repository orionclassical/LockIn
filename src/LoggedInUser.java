public class LoggedInUser {
    public static String firstName;
    public static String lastName;
    public static String email;
    // --- SETTERS ---
    public static void setUser(String fName, String lName, String userEmail) {
        firstName = fName;
        lastName = lName;
        email = userEmail;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getEmail() {
        return email;
    }

    // Optional: clear data when user logs out
    public static void clear() {
        firstName = null;
        lastName = null;
        email = null;
    }
}

package main.java.window.utils;

import main.java.window.database.DatabaseConnection;
import main.java.window.models.LoggedInUser;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Update {

    public boolean updateEmail(String newEmail) {
        String currentEmail = LoggedInUser.getEmail();

        String sql = "UPDATE users SET email = ? WHERE email = ?";

        try (Connection connect = DatabaseConnection.connectDB();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, newEmail);
            prepare.setString(2, currentEmail);

            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                LoggedInUser.setUser(
                        LoggedInUser.getFirstName(),
                        LoggedInUser.getLastName(),
                        newEmail
                );
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updatePassword(String newPassword) {
        String currentEmail = LoggedInUser.getEmail();

        String sql = "UPDATE users SET password = ? WHERE email = ?";

        try (Connection connect = DatabaseConnection.connectDB();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, newPassword);
            prepare.setString(2, currentEmail);

            return prepare.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateName(String firstName, String lastName) {
        String email = LoggedInUser.getEmail();

        String sql = "UPDATE users SET first_name = ?, last_name = ? WHERE email = ?";

        try (Connection connect = DatabaseConnection.connectDB();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, firstName);
            prepare.setString(2, lastName);
            prepare.setString(3, email);

            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                LoggedInUser.setUser(firstName, lastName, email);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}

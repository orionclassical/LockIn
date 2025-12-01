package main.java.window.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.java.window.database.DatabaseConnection;
import main.java.window.models.User;

public class Account {
    
    private AlertMessage alert = new AlertMessage();
    private Connection connect = DatabaseConnection.connectDB();
    private PreparedStatement prepare;
    private ResultSet result;

    public boolean checkErrorsCreating(String firstName, String lastName, String email, String password, String confirmPassword, boolean termsAndConditions){
        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            alert.errorAlert("All blank fields are required!");
            return false;
        }else if(!termsAndConditions){
            alert.errorAlert("Please agree to the Terms and Conditions before signing up.");
            return false; 
        }else if(!email.endsWith("@gmail.com")){
            alert.errorAlert("Your email must contain @gmail.com");
            return false;
        }else if(!password.equals(confirmPassword)){
            alert.errorAlert("Password does not match! Try again");
            return false; 
        }else if(password.length() < 8){
            alert.errorAlert("Invalid Password! at least 8 characters needed");
            return false; 
        }else{
            return true;
        }
    }

    public void createAccount(String firstName, String lastName, String email, String password){
        String checkEmail = "SELECT * FROM users WHERE email = ?";
                
            try{
                prepare = connect.prepareStatement(checkEmail);
                prepare.setString(1, email);
                result = prepare.executeQuery();

                if(result.next()){
                    alert.errorAlert(email + " already exist!");
                }else {
                    String insertData = "INSERT INTO users (first_name, last_name, email, password) VALUES(?, ?, ?, ?)";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, firstName);
                    prepare.setString(2, lastName);
                    prepare.setString(3, email);
                    prepare.setString(4, password);
                    prepare.executeUpdate();
                    alert.succesfulAlert("Registration is successful!");
                }
            }catch (Exception e) {e.printStackTrace();}
    }

    public boolean checkErrorsSigning(String email, String password){
        if (email.isEmpty() && password.isEmpty()) {
            alert.errorAlert("Fill in your email and password");
            return false;
        } else if (email.isEmpty()) {
            alert.errorAlert("Enter an email");
            return false ;
        } else if (password.isEmpty()) {
            alert.errorAlert("Enter a password");
            return false;
        }else{
            return true;
        }
    }

    public User signInAccount(String email, String password) {
        String checkLogin = "SELECT * FROM users WHERE email = ? AND password = ?";

        try {
            prepare = connect.prepareStatement(checkLogin);
            prepare.setString(1, email);
            prepare.setString(2, password);
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                return new User(
                    result.getInt("id"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getString("email")
                );
            } else {
                alert.errorAlert("Incorrect email or password!");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

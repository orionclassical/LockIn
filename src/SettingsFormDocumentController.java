import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class SettingsFormDocumentController implements Initializable{

    @FXML
    private AnchorPane settings_main_form;

    @FXML
    private Button mainform_settings_changeEmail_applyChangesBtn;

    @FXML
    private Button mainform_settings_changeEmail_cancelBtn;

    @FXML
    private TextField mainform_settings_changeEmail_emailFillText;

    @FXML
    private Label mainform_settings_changeEmail_fullName;

    @FXML
    private Button mainform_settings_changeEmail_fullNameBtn;

    @FXML
    private Label mainform_settings_changeEmail_password;

    @FXML
    private Button mainform_settings_changeEmail_passwordBtn;

    @FXML
    private Button mainform_settings_changeName_applyChangesBtn;

    @FXML
    private Button mainform_settings_changeName_cancelBtn;

    @FXML
    private Label mainform_settings_changeName_email;

    @FXML
    private Button mainform_settings_changeName_emailBtn;

    @FXML
    private TextField mainform_settings_changeName_firstNameFillText;

    @FXML
    private TextField mainform_settings_changeName_lastNameFillText;

    @FXML
    private Label mainform_settings_changeName_password;

    @FXML
    private Button mainform_settings_changeName_passwordBtn;

    @FXML
    private Button mainform_settings_changePassword_applyChangesBtn;

    @FXML
    private Button mainform_settings_changePassword_cancelBtn;

    @FXML
    private PasswordField mainform_settings_changePassword_confirmPasswordFillText;

    @FXML
    private Label mainform_settings_changePassword_email;

    @FXML
    private Button mainform_settings_changePassword_emailBtn;

    @FXML
    private Label mainform_settings_changePassword_fullName;

    @FXML
    private Button mainform_settings_changePassword_fullNameBtn;

    @FXML
    private PasswordField mainform_settings_changePassword_passwordFillText;

    @FXML
    private Label mainform_settings_default_email;

    @FXML
    private Button mainform_settings_default_emailBtn;

    @FXML
    private Label mainform_settings_default_fullName;

    @FXML
    private Button mainform_settings_default_fullNameBtn;

    @FXML
    private Label mainform_settings_default_password;

    @FXML
    private Button mainform_settings_default_passwordBtn;

    @FXML
    private BorderPane sidebar_settings_changeEmail;

    @FXML
    private Label sidebar_settings_changeEmail_fullName;

    @FXML
    private Button sidebar_settings_changeEmail_logOut;

    @FXML
    private Button sidebar_settings_changeEmail_notifications;

    @FXML
    private Button sidebar_settings_changeEmail_settings;

    @FXML
    private Button sidebar_settings_changeEmail_today;

    @FXML
    private Button sidebar_settings_changeEmail_yourStats;

    @FXML
    private BorderPane sidebar_settings_changeName;

    @FXML
    private Label sidebar_settings_changeName_fullName;

    @FXML
    private Button sidebar_settings_changeName_logOut;

    @FXML
    private Button sidebar_settings_changeName_notifications;

    @FXML
    private Button sidebar_settings_changeName_settings;

    @FXML
    private Button sidebar_settings_changeName_today;

    @FXML
    private Button sidebar_settings_changeName_yourStats;

    @FXML
    private BorderPane sidebar_settings_changePassword;

    @FXML
    private Label sidebar_settings_changePassword_fullName;

    @FXML
    private Button sidebar_settings_changePassword_logOut;

    @FXML
    private Button sidebar_settings_changePassword_notifications;

    @FXML
    private Button sidebar_settings_changePassword_settings;

    @FXML
    private Button sidebar_settings_changePassword_today;

    @FXML
    private Button sidebar_settings_changePassword_yourStats;

    @FXML
    private BorderPane sidebar_settings_default;

    @FXML
    private Label sidebar_settings_default_fullName;

    @FXML
    private Button sidebar_settings_default_logOut;

    @FXML
    private Button sidebar_settings_default_notifications;

    @FXML
    private Button sidebar_settings_default_settings;

    @FXML
    private Button sidebar_settings_default_today;

    @FXML
    private Button sidebar_settings_default_yourStats;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    
    public void updateName(){
        String newFirstName = mainform_settings_changeName_firstNameFillText.getText();
        String newLastName = mainform_settings_changeName_lastNameFillText.getText();
        String userEmail = LoggedInUser.getEmail();

        AlertMessage alert = new AlertMessage();
        String currentFirstName = LoggedInUser.getFirstName();
        String currentLastName = LoggedInUser.getLastName();

        if(newFirstName.isEmpty() || newLastName.isEmpty()){
            alert.errorAlert("Must fill up the fields");
            return;
        }else if(newFirstName.equals(currentFirstName) && newLastName.equals(currentLastName)){
            return;
        }

        String sql = "UPDATE users SET first_name = ?, last_name = ? WHERE email = ?";

        try (Connection connect = DatabaseConnection.connectDB();
            PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, newFirstName);
            prepare.setString(2, newLastName);
            prepare.setString(3, userEmail);

            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                alert.succesfulAlert("User name updated successfully!");

                LoggedInUser.setUser(newFirstName, newLastName, userEmail);
                sidebar_settings_default.setVisible(true);
                sidebar_settings_changeName.setVisible(false);
                sidebar_settings_changeEmail.setVisible(false);
                sidebar_settings_changePassword.setVisible(false);
                displayUserInformation();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmail(){
        String newEmail = mainform_settings_changeEmail_emailFillText.getText();
        String userFirstName = LoggedInUser.getFirstName();
        String userLastName = LoggedInUser.getLastName();

        AlertMessage alert = new AlertMessage();
        String currentEmail = LoggedInUser.getEmail();

        if(newEmail.isEmpty()){
            alert.errorAlert("Must fill up the field");
            return;
        }else if (!newEmail.endsWith("@gmail.com")) {
            alert.errorAlert("Please enter a valid email address");
            return;
        }else if(newEmail.equals(currentEmail)){
            return;
        }

        String sql = "UPDATE users SET email = ? WHERE email = ?";

        try (Connection connect = DatabaseConnection.connectDB();
            PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, newEmail);
            prepare.setString(2, currentEmail);


            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                alert.succesfulAlert("Email updated successfully!");

                LoggedInUser.setUser(userFirstName, userLastName, newEmail);
                sidebar_settings_default.setVisible(true);
                sidebar_settings_changeName.setVisible(false);
                sidebar_settings_changeEmail.setVisible(false);
                sidebar_settings_changePassword.setVisible(false);
                displayUserInformation();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(){
        String newPassword = mainform_settings_changePassword_passwordFillText.getText();
        String newConfirmPassword = mainform_settings_changePassword_confirmPasswordFillText.getText();
        String currentEmail = LoggedInUser.getEmail();

        AlertMessage alert = new AlertMessage();

        if (newPassword.isEmpty() || newConfirmPassword.isEmpty()) {
            alert.errorAlert("Please fill up both password fields");
            return;
        }else if(!newPassword.equals(newConfirmPassword)){
            alert.errorAlert("Passwords do not match! Try again");
            return;
        }else if(newPassword.length() < 8){
            alert.errorAlert("Invalid Password! at least 8 characters needed");
            return;
        }

        String sql = "UPDATE users SET password = ? WHERE email = ?";

        try(Connection connect = DatabaseConnection.connectDB(); 
            PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, newPassword);
            prepare.setString(2, currentEmail);

            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                alert.succesfulAlert("Password updated successfully!");

                sidebar_settings_default.setVisible(true);
                sidebar_settings_changeName.setVisible(false);
                sidebar_settings_changeEmail.setVisible(false);
                sidebar_settings_changePassword.setVisible(false);
                displayUserInformation();
            }
        }catch (Exception e) {e.printStackTrace();}
    }

    public void displayUserInformation() {
        String sql = "SELECT first_name, last_name, email, password FROM users WHERE email = ?";

        connect = DatabaseConnection.connectDB();

        try{
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, LoggedInUser.getEmail());
            result = prepare.executeQuery();

            if (result.next()) {
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String email = result.getString("email");
                String password = "********";

                LoggedInUser.setUser(firstName, lastName, email);

                sidebar_settings_default_fullName.setText(firstName + " " + lastName);
                mainform_settings_default_fullName.setText(firstName + " " + lastName);
                mainform_settings_default_email.setText(email);
                mainform_settings_default_password.setText(password);

                sidebar_settings_changeName_fullName.setText(firstName + " " + lastName);
                mainform_settings_changeName_firstNameFillText.setText(firstName);
                mainform_settings_changeName_lastNameFillText.setText(lastName);
                mainform_settings_changeName_email.setText(email);
                mainform_settings_changeName_password.setText(password);

                sidebar_settings_changeEmail_fullName.setText(firstName + " " + lastName);
                mainform_settings_changeEmail_fullName.setText(firstName + " " + lastName);
                mainform_settings_changeEmail_emailFillText.setText(email);
                mainform_settings_changeEmail_password.setText(password);

                sidebar_settings_changePassword_fullName.setText(firstName + " " + lastName);
                mainform_settings_changePassword_fullName.setText(firstName + " " + lastName);
                mainform_settings_changePassword_email.setText(email);
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void logoutForm(ActionEvent event){
        if(event.getSource() == sidebar_settings_default_logOut || event.getSource() == sidebar_settings_changeName_logOut || event.getSource() == sidebar_settings_changeEmail_logOut || event.getSource() == sidebar_settings_changePassword_logOut){
            AlertMessage alert = new AlertMessage();

            if(alert.logOutAlert(event)){
                ChangeWindow.loginStage.show();
                ChangeWindow.settingsStage = (Stage) settings_main_form.getScene().getWindow();
                ChangeWindow.settingsStage.hide();
            }
        }
    }

    public void switchForm(ActionEvent event){
        if(event.getSource() == mainform_settings_default_fullNameBtn || event.getSource() == mainform_settings_changeEmail_fullNameBtn || event.getSource() == mainform_settings_changePassword_fullNameBtn){
            sidebar_settings_default.setVisible(false);
            sidebar_settings_changeName.setVisible(true);
            sidebar_settings_changeEmail.setVisible(false);
            sidebar_settings_changePassword.setVisible(false);
            loadCurrentName();
        }else if (event.getSource() == mainform_settings_default_emailBtn || event.getSource() == mainform_settings_changeName_emailBtn || event.getSource() == mainform_settings_changePassword_emailBtn) {
            sidebar_settings_default.setVisible(false);
            sidebar_settings_changeName.setVisible(false);
            sidebar_settings_changeEmail.setVisible(true);
            sidebar_settings_changePassword.setVisible(false);
            loadCurrentEmail();
        }else if (event.getSource() == mainform_settings_default_passwordBtn || event.getSource() == mainform_settings_changeName_passwordBtn || event.getSource() == mainform_settings_changeEmail_passwordBtn) {
            sidebar_settings_default.setVisible(false);
            sidebar_settings_changeName.setVisible(false);
            sidebar_settings_changeEmail.setVisible(false);
            sidebar_settings_changePassword.setVisible(true);
        }else if (event.getSource() == mainform_settings_changeName_cancelBtn || event.getSource() == mainform_settings_changeEmail_cancelBtn || event.getSource() == mainform_settings_changePassword_cancelBtn){
            sidebar_settings_default.setVisible(true);
            sidebar_settings_changeName.setVisible(false);
            sidebar_settings_changeEmail.setVisible(false);
            sidebar_settings_changePassword.setVisible(false);
        }
    }

    public void loadCurrentName() {
        mainform_settings_changeName_firstNameFillText.setText(LoggedInUser.getFirstName());
        mainform_settings_changeName_lastNameFillText.setText(LoggedInUser.getLastName());
    }

    public void loadCurrentEmail() {
        mainform_settings_changeEmail_emailFillText.setText(LoggedInUser.getEmail());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        displayUserInformation();
    }
}




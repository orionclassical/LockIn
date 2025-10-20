import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLDocumentController {

    @FXML
    private Hyperlink login_createOne;

    @FXML
    private TextField login_email;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password;

    @FXML
    private CheckBox login_rememberMe;

    @FXML
    private Button login_signUpBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField signup_confirmPassword;

    @FXML
    private Button signup_createAccountBtn;

    @FXML
    private TextField signup_email;

    @FXML
    private TextField signup_firstName;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private TextField signup_lastName;

    @FXML
    private PasswordField signup_password;

    @FXML
    private Hyperlink signup_signInHere;

    @FXML
    private CheckBox signup_termsAndConditions;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    public void createAccount(){
        String firstName = signup_firstName.getText();
        String lastName = signup_lastName.getText();
        String email = signup_email.getText();
        String password = signup_password.getText();
        String confirmPassword = signup_confirmPassword.getText();
        
        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            alert.errorAlert("All blank fields are required!");
            return;
        }else if(!signup_termsAndConditions.isSelected()){
            alert.errorAlert("Please agree to the Terms and Conditions before signing up.");
            return;
        }else if(!email.endsWith("@gmail.com")){
            alert.errorAlert("Your email must contain @gmail.com");
        }else if(!password.equals(confirmPassword)){
            alert.errorAlert("Password does not match! Try again");
            return;
        }else if(password.length() < 8){
            alert.errorAlert("Invalid Password! at least 8 characters needed");
            return;
        }else{
            String checkEmail = "SELECT * FROM users WHERE email = ?";

            connect = DatabaseConnection.connectDB();
                
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
                    signup_firstName.setText("");
                    signup_lastName.setText("");
                    signup_email.setText("");
                    signup_password.setText("");
                    signup_confirmPassword.setText("");
                    signup_termsAndConditions.setSelected(false);
                    login_form.setVisible(true);
                    signup_form.setVisible(false);

                }
            } catch (Exception e) {e.printStackTrace();}
        }
    }
    
    public void signInAccount(){
        String email = login_email.getText();
        String password = login_password.getText();

        if(email.isEmpty() && password.isEmpty()){
            alert.errorAlert("Fill in your email and password");
        }else if(email.isEmpty()){
            alert.errorAlert("Enter an email");
        }else if(password.isEmpty()){
            alert.errorAlert("Enter a password");
        }else{
            String checkLogin = "SELECT * FROM users WHERE email = ? AND password = ?";

            connect = DatabaseConnection.connectDB();

            try {
                prepare = connect.prepareStatement(checkLogin);
                prepare.setString(1, email);
                prepare.setString(2, password);
                result = prepare.executeQuery();

                if (result.next()) {
                    LoggedInUser.email = email;

                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("SettingsFormDocument.fxml"));
                        Stage stage = new Stage();

                        stage.setTitle("LockIn | Settings");
                        Image image = new Image("img/LinLogo.png");
                        stage.getIcons().add(image);
                        stage.setScene(new Scene(root));
                        stage.show();

                        if(!login_rememberMe.isSelected()){
                            login_email.setText("");
                            login_password.setText("");
                        }
                        ChangeWindow.loginStage = (Stage) login_signUpBtn.getScene().getWindow(); 
                        ChangeWindow.loginStage.hide();
                    } catch (IOException e) {}

                } else {
                    alert.errorAlert("Incorrect email or password!");
                }
            } catch (Exception e) {e.printStackTrace();}
        }
    }

    public void switchForm(ActionEvent event){
        if(event.getSource() == login_createOne){
            login_email.setText("");
            login_password.setText("");
            login_rememberMe.setSelected(false);
            login_form.setVisible(false);
            signup_form.setVisible(true);
        }else if (event.getSource() == signup_signInHere) {
            signup_firstName.setText("");
            signup_lastName.setText("");
            signup_email.setText("");
            signup_password.setText("");
            signup_confirmPassword.setText("");
            signup_termsAndConditions.setSelected(false);
            login_form.setVisible(true);
            signup_form.setVisible(false);
        }
    }

}


package main.java.window.controllers;
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
import main.java.window.models.ChangeWindow;
import main.java.window.models.LoggedInUser;
import main.java.window.models.User;
import main.java.window.utils.Account;

public class LoginAndSignUp {
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

    private Account account = new Account();

    public void createAccount(){
        String firstName = signup_firstName.getText();
        String lastName = signup_lastName.getText();
        String email = signup_email.getText();
        String password = signup_password.getText();
        String confirmPassword = signup_confirmPassword.getText();
        boolean termsAndConditions = signup_termsAndConditions.isSelected();        
        boolean confirm = account.checkErrorsCreating(firstName, lastName, email, password, confirmPassword, termsAndConditions);

        if(confirm){
            account.createAccount(firstName, lastName, email, password);
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
    
    public void signInAccount() {
        String email = login_email.getText();
        String password = login_password.getText();
        boolean rememberMe = login_rememberMe.isSelected();

        boolean confirm = account.checkErrorsSigning(email, password);

        if(confirm){
            User user = account.signInAccount(email, password);
            if(user != null){
                try{
                    LoggedInUser.setUser(user.getFirstName(), user.getLastName(), user.getEmail());
                    LoggedInUser.setUserId(user.getId());

                    Parent root = FXMLLoader.load(getClass().getResource("/main/resources/fxml/MainDocument.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("LockIn");
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/assets/images/LinLogo.png")));
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.show();
                    if (!rememberMe) {
                        login_email.setText("");
                        login_password.setText("");
                    }
                    ChangeWindow.loginStage = (Stage) login_signUpBtn.getScene().getWindow();
                    ChangeWindow.loginStage.hide();
                }catch (Exception e) {e.printStackTrace();}
            }  
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


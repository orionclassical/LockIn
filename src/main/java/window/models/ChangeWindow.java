package main.java.window.models;
import javafx.stage.Stage;

public class ChangeWindow {
    public static Stage loginStage;
    public static Stage currentStage;

    public static Stage getLoginStage() { return loginStage; }
    public static Stage getCurrentStage() { return currentStage; }

    public static void setLoginStage(Stage loginStage) { ChangeWindow.loginStage = loginStage; }
    public static void setCurrentStage(Stage currentStage) { ChangeWindow.currentStage = currentStage; }    
}
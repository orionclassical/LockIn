import javafx.stage.Stage;

public class ChangeWindow {
    public static Stage loginStage;
    public static Stage settingsStage;

    public static Stage getSettingsStage() {
        return settingsStage;
    }

    public static void setSettingsStage(Stage settingsStage) {
        ChangeWindow.settingsStage = settingsStage;
    }

    public static Stage getLoginStage() {
        return loginStage;
    }

    public static void setLoginStage(Stage loginStage) {
        ChangeWindow.loginStage = loginStage;
    }    
}

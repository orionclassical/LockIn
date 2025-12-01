import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/fxml/LoginAndSignUp.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("LockIn");

        Image image = new Image(
            getClass().getResourceAsStream("/main/resources/assets/images/LinLogo.png")
        );

        primaryStage.setResizable(false);
        primaryStage.getIcons().add(image);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

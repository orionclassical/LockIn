import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LockIn extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setWidth(450);
            primaryStage.setHeight(550);
            primaryStage.setTitle("LockIn");

            Image image = new Image("img/LinLogo.png");
            primaryStage.setFullScreen(false);
            primaryStage.setResizable(false);
            primaryStage.setFullScreenExitKeyCombination(javafx.scene.input.KeyCombination.NO_MATCH);
            primaryStage.fullScreenProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal) primaryStage.setFullScreen(false);
            });
            primaryStage.getIcons().add(image);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {}
    }
     public static void main(String[] args) {
        launch(args);
    }
}   



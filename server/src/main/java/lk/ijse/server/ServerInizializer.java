package lk.ijse.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ServerInizializer  extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/ServerForm.fxml"))));
        stage.setTitle("Server");
        stage.centerOnScreen();
        // stage.setFullScreen(true);
        stage.show();
    }
}

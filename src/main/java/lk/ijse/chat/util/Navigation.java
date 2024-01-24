package lk.ijse.chat.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static Stage stage;
    private static Scene scene;
    private static Parent parent;

    public static void switchNavigation(String link, ActionEvent event) throws IOException {
        parent = FXMLLoader.load(Navigation.class.getResource("/view/" + link));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void switchNavigation(String link, javafx.scene.input.MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Navigation.class.getResource("/view/" + link));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}

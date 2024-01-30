package lk.ijse.client1.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.client1.controller.ClientController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static String timeNow() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm");
        return dateFormat.format(new Date()) ;
    }
    public static void minimize() {
        if(stage!=null){
            stage.setIconified(true);
        }
    }
}

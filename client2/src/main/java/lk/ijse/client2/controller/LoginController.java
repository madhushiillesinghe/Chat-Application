package lk.ijse.client2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.ijse.client2.util.Navigation;

import java.io.IOException;


public class LoginController {

    @FXML
    private TextField txtUserName;
    public static String userName;


    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        userName = txtUserName.getText();
        Navigation.switchNavigation("ClientForm.fxml", event);
        // System.out.println("navigation success");
    }
}

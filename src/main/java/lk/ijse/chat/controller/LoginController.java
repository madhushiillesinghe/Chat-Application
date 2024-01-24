package lk.ijse.chat.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.ijse.chat.util.Navigation;


import java.io.IOException;
import java.sql.SQLException;


public class LoginController {

    @FXML
    private TextField txtUserName;

    public void btnLoginOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Navigation.switchNavigation("ChatForm.fxml",actionEvent);
    }
}

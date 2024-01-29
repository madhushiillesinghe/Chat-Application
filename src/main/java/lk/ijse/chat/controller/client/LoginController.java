package lk.ijse.chat.controller.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.ijse.chat.util.Navigation;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LoginController {

    @FXML
    private TextField txtUserName;
    public static String userName;
    List<String> user=new ArrayList<>();
    private static LoginController controller;
    public LoginController(){
        controller = this;
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        userName = txtUserName.getText();
        user.add(userName);
        Navigation.switchNavigation("ClientForm.fxml", actionEvent);
    }
}

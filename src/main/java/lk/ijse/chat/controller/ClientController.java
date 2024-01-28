package lk.ijse.chat.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;


public class ClientController {

    @FXML
    private Label lblClientName;

    @FXML
    private VBox msgVbox;

    @FXML
    private TextField txtMessage;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    @FXML
    void sendOnAction(MouseEvent event) {

    }

    @FXML
    void txtMessageOnAction(ActionEvent event) {

    }

}

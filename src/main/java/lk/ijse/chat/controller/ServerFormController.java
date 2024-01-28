package lk.ijse.chat.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ServerFormController {

    @FXML
    private TextArea areaDetail;

    @FXML
    private Button btnStop;

    @FXML
    private Label lblNumClients;

    @FXML
    void btnStopOnAction(ActionEvent event) {
        System.exit(0);
    }

}

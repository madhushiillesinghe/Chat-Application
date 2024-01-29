package lk.ijse.chat.controller.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController extends Thread implements Initializable {

    @FXML
    private ImageView addImg;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img5;

    @FXML
    private Label lblClientName;

    @FXML
    private VBox msgVbox;

    @FXML
    private TextField txtMessage;
    BufferedReader reader;
    Socket socket;
    PrintWriter writer;

    @FXML
    void addImgOnAction(MouseEvent event) {

    }

    @FXML
    void img1OnAction(MouseEvent event) {
    }

    @FXML
    void img2OnAction(MouseEvent event) {

    }

    @FXML
    void img3OnAction(MouseEvent event) {

    }

    @FXML
    void img4OnAction(MouseEvent event) {

    }

    @FXML
    void img5OnAction(MouseEvent event) {

    }

    @FXML
    void sendOnAction(MouseEvent event) {
        if(!txtMessage.getText().equals("")) {
            String message = txtMessage.getText();
            txtMessage.setText("");
            if (message.equalsIgnoreCase("bye")) {
                System.exit(0);
            }
        }else
            new Alert(Alert.AlertType.ERROR, "Please enter your message! ").show();

    }

    @FXML
    void txtMessageOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectSocket();
        lblClientName.setText(LoginController.userName);
    }

    private void connectSocket() {
        try {
            socket = new Socket("localhost",3000);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(LoginController.userName);
            dataOutputStream.flush();

            System.out.println(LoginController.userName+" Connected");

            reader = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(),true);

            this.start();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void run(){
        try{
            while (true){
                String  message=reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

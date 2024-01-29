package lk.ijse.client1.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import lk.ijse.client1.util.Navigation;

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
    private JFXButton btnSend;

    @FXML
    private TextField txtMessage;
    BufferedReader reader;
    Socket socket;
    PrintWriter writer;

    @FXML
    public Pane imojiPaneId;
    private static ClientController controller;

    public ClientController(){
        controller = this;
    }

    public static ClientController getInstance(){
        return controller;
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
    void btnSendOnAction(ActionEvent event) {
        System.out.println("send button"+txtMessage.getText());
        if(!(txtMessage.getText().isEmpty())) {
            String message = txtMessage.getText();
            System.out.println("message"+lblClientName.getText()+": "+ message);
            writer.println(lblClientName.getText()+": "+ message);
            if (message.equalsIgnoreCase("bye")) {
                System.exit(0);
            }
            txtMessage.clear();
        }else
            new Alert(Alert.AlertType.ERROR, "Please enter your message! ").show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblClientName.setText(LoginController.userName);
        new Thread(() ->{
            connectSocket();
        }).start();
    }

    private void connectSocket() {
            try {
                socket = new Socket("localhost", 5600);
                System.out.println(LoginController.userName + " Connected");

                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);
                while (true) {
                    String message = reader.readLine();
                    String[] splitMessage=message.split(":");
                    String userName=splitMessage[0];
                    String typeMessage=splitMessage[1];
                    String fName="";
                    if(userName.length()>3){
                        fName=userName.substring(0,3);
                        System.out.println("ftg"+fName);
                    }
                    if(fName.equalsIgnoreCase("img")){
                    }else {
                        if(LoginController.userName.equalsIgnoreCase(userName)){
                            if(!typeMessage.isEmpty()){
                                HBox hBox=new HBox();
                                hBox.setAlignment(Pos.CENTER_RIGHT);
                                hBox.setPadding(new Insets(5,10,5,10));

                                HBox innerhbox=new HBox();
                                innerhbox.setPadding(new Insets(1,10,2,10));
                                innerhbox.setStyle(
                                        "-fx-background-color: #25479b;"+
                                        "-fx-background-radius: 15px"
                                );

                                Text text=new Text(typeMessage);
                                TextFlow textFlow=new TextFlow(text);
                                textFlow.setPadding(new Insets(5,10,5,10));

                                innerhbox.getChildren().add(textFlow);

                                hBox.getChildren().add(innerhbox);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        msgVbox.getChildren().add(hBox);
                                    }
                                });
                            }
                        }
                        else {
                            if(!LoginController.userName.equalsIgnoreCase(userName)){
                                HBox hBox=new HBox();
                                hBox.setAlignment(Pos.CENTER_LEFT);
                                hBox.setPadding(new Insets(5,10,5,10));

                                HBox innerhbox=new HBox();
                                innerhbox.setPadding(new Insets(1,10,2,10));
                                innerhbox.setStyle(
                                        "-fx-background-color: #5e65e0;"+
                                        "-fx-background-radius: 15px"
                                );

                                Text txtUser=new Text(userName+":" );
                                txtUser.setFont(Font.font(12.5));

                                Text text=new Text(typeMessage);
                                TextFlow textFlow=new TextFlow(txtUser,text);
                                textFlow.setPadding(new Insets(5,10,5,10));

                                innerhbox.getChildren().add(textFlow);

                                hBox.getChildren().add(innerhbox);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        msgVbox.getChildren().add(hBox);
                                    }
                                });
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
    }


    public void addImageOnAction(MouseEvent mouseEvent) throws IOException {
    }

    public void imojiOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.popupPane("ImojiPaneForm.fxml");

    }

    public void txtOnAction(ActionEvent actionEvent) {
        btnSendOnAction(actionEvent);
    }
}

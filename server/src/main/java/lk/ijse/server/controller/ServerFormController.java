package lk.ijse.server.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import lk.ijse.server.ClientHandler;


import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ServerFormController implements Initializable {

    @FXML
    private TextArea areaDetail;

    @FXML
    private Button btnStop;
    ServerSocket serverSocket;
    Socket clientSocket;
    List<ClientHandler>clientList=new ArrayList<>();

    @FXML
    void btnStopOnAction(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            try{
                serverSocket= new ServerSocket(5600);
                System.out.println("server started");
                areaDetail.appendText("Server Strated");
                while (true) {
                    clientSocket=serverSocket.accept();
                    areaDetail.appendText("\nClient connected: " + clientSocket);
                    ClientHandler clientHandler = new ClientHandler(clientSocket,clientList);
                    clientList.add(clientHandler);
                    clientHandler.start();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}

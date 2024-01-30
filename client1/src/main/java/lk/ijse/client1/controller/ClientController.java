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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import lk.ijse.client1.util.Navigation;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Random;
import java.util.ResourceBundle;

public class ClientController extends Thread implements Initializable {

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
    private String encodeImage;

    @FXML
    public  TextField txtMessage;
    @FXML
    public Pane imojiPaneId;
    private BufferedReader reader;
    private Socket socket;
    private File file;
    private PrintWriter writer;



    @FXML
    void img1OnAction(MouseEvent event) {
        String SmilingFaceImoji = "\uD83D\uDE0A";
        txtMessage.appendText(SmilingFaceImoji);
    }

    @FXML
    void img2OnAction(MouseEvent event) {
        String CryingFaceImoji = "\uD83D\uDE22";
        txtMessage.appendText(CryingFaceImoji);
    }

    @FXML
    void img3OnAction(MouseEvent event) {
        String CryingFaceImoji = "\uD83D\uDE0D";
        txtMessage.appendText(CryingFaceImoji);
    }

    @FXML
    void img4OnAction(MouseEvent event) {
        String TearWithJoyFaceImoji = "\uD83D\uDE02";
        txtMessage.appendText(TearWithJoyFaceImoji);
    }

    @FXML
    void img5OnAction(MouseEvent event) {
        String thumbsUp = "\uD83D\uDC4D";
        txtMessage.appendText(thumbsUp);}


    @FXML
    void btnSendOnAction(ActionEvent event) {
        if (file != null) {
            writer.println("img" + lblClientName.getText() + ":" + encodeImage);
            file = null;
        } else {
            writer.println(LoginController.userName + ":" + txtMessage.getText());
        }
        txtMessage.clear();
        imojiPaneId.setVisible(false);
        txtMessage.setEditable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            lblClientName.setText(LoginController.userName);
            new Thread(() -> {
                try {
                    System.out.println(LoginController.userName + " Connected");
                    socket = new Socket("localhost", 5600);
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer = new PrintWriter(socket.getOutputStream(), true);
                    while (true) {
                        String message = reader.readLine();
                        String[] splitMessage = message.split(":");
                        String userName = splitMessage[0];
                        String typeMessage = splitMessage[1];
                        String fName = "";
                        if (userName.length() > 3) {
                            //kotaswalt kadno
                            fName = userName.substring(0, 3);
                            System.out.println("ftg" + fName);
                        }
                        //case eka adalm na
                        if (fName.equalsIgnoreCase("img")) {
                            String path=typeMessage;
                            //assign to the byte type array
                            byte[] imageDecode =  Base64.getDecoder().decode(path);
                            //random number >more than genarate the random number and it same rarely
                            int random=new Random().nextInt(1000000000);
                            String fileName="image file"+random+".png";
                            File filePath=new File("client1/src/main/resources/ImageFile");
                            File receivedImage= new File(filePath,fileName);

                            try( FileOutputStream fileOutputStream = new FileOutputStream(receivedImage)){
                                fileOutputStream.write(imageDecode);
                            }

                            Image image=new Image(receivedImage.toURI().toString());
                            ImageView imageView=new ImageView(image);
                            imageView.setFitWidth(200);
                            imageView.setFitHeight(200);

                            HBox imageHbox=new HBox(imageView);
                            //space ekk widiyt thynna
                            imageHbox.setPadding(new Insets(5));


                            HBox hBox=new HBox(10);
                            hBox.setAlignment(Pos.BOTTOM_RIGHT);

                            //bubble ekk athule set karana nisa  thaw hbox ekk use krnwa
                            HBox innerBox=new HBox();
                            innerBox.setPadding(new Insets(5,10,5,10));

                            String[] user=userName.split("img");
                            String realName=user[1];

                            //use name capitaor simple no problem
                            if(lblClientName.getText().equalsIgnoreCase(realName)){
                                innerBox.setStyle(
                                        "-fx-background-color: #0077cc;" +
                                        "-fx-background-radius: 15px"
                                );

                                Label textTime=new Label(Navigation.timeNow());
                                textTime.setFont(Font.font(10.5));

                                HBox hBoxTime=new HBox(textTime);
                                hBoxTime.setAlignment(Pos.BOTTOM_RIGHT);

                                innerBox.getChildren().addAll(imageHbox,hBoxTime);
                                hBox.getChildren().add(innerBox);
                                hBox.setAlignment(Pos.TOP_RIGHT);

                                hBox.setPadding(new Insets(5,5,5,10));
                            }else {
                                innerBox.setStyle(
                                        "-fx-background-color: #3498db;" +
                                                "-fx-background-radius: 15px"
                                );
                                Text txtUser = new Text(""+realName + ":");
                                txtUser.setFont(Font.font(12.5));

                                Label textTime=new Label(Navigation.timeNow());
                                textTime.setFont(Font.font(10.5));

                                HBox hBoxTime=new HBox(textTime);
                                hBoxTime.setAlignment(Pos.BOTTOM_RIGHT);

                                innerBox.getChildren().addAll(txtUser,imageHbox,hBoxTime);
                                hBox.getChildren().add(innerBox);
                                hBox.setAlignment(Pos.TOP_LEFT);

                                hBox.setPadding(new Insets(5,5,5,10));

                            }
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    msgVbox.getChildren().add(hBox);
                                }
                            });

                        } else {
                            if (LoginController.userName.equalsIgnoreCase(userName)) {
                                if (!typeMessage.isEmpty()) {
                                    HBox hBox = new HBox();
                                    hBox.setAlignment(Pos.CENTER_RIGHT);
                                    hBox.setPadding(new Insets(5, 10, 5, 10));

                                    HBox innerhbox = new HBox();
                                    innerhbox.setPadding(new Insets(1, 10, 2, 10));
                                    innerhbox.setStyle(
                                            "-fx-background-color: #0077cc;" +
                                                    "-fx-background-radius: 15px"
                                    );

                                    Text text = new Text(typeMessage);

                                    Label textTime=new Label(Navigation.timeNow());
                                    textTime.setFont(Font.font(10.5));

                                    HBox hBoxTime=new HBox(textTime);
                                    hBoxTime.setAlignment(Pos.BOTTOM_RIGHT);

                                    TextFlow textFlow = new TextFlow(text);
                                    textFlow.setPadding(new Insets(5, 10, 5, 10));

                                    innerhbox.getChildren().addAll(textFlow,hBoxTime);

                                    hBox.getChildren().add(innerhbox);
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            msgVbox.getChildren().add(hBox);
                                        }
                                    });
                                }
                            } else {
                                if (!LoginController.userName.equalsIgnoreCase(userName)) {
                                    HBox hBox = new HBox();
                                    hBox.setAlignment(Pos.CENTER_LEFT);
                                    hBox.setPadding(new Insets(5, 10, 5, 10));

                                    HBox innerhbox = new HBox();
                                    innerhbox.setPadding(new Insets(1, 10, 2, 10));
                                    innerhbox.setStyle(
                                            "-fx-background-color: #3498db;" +
                                                    "-fx-background-radius: 15px"
                                    );

                                    Text txtUser = new Text(userName + ":");
                                    txtUser.setFont(Font.font(12.5));

                                    Text text = new Text(typeMessage);

                                    Label textTime=new Label(Navigation.timeNow());
                                    textTime.setFont(Font.font(10.5));

                                    HBox hBoxTime=new HBox(textTime);
                                    hBoxTime.setAlignment(Pos.BOTTOM_RIGHT);


                                    TextFlow textFlow = new TextFlow(txtUser, text);
                                    textFlow.setPadding(new Insets(5, 10, 5, 10));

                                    innerhbox.getChildren().addAll(textFlow,hBoxTime);

                                    hBox.getChildren().add(innerhbox);
                                    //vobx ekt h box eka set karno
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
                    throw new RuntimeException(e);
                }
            }).start();
    }



    public void txtOnAction(ActionEvent actionEvent) {
        btnSendOnAction(actionEvent);
    }

    public void imojiOnAction(ActionEvent actionEvent) {
        imojiPaneId.setVisible(!imojiPaneId.isVisible());
    }

    public void fileOnAction(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");
        // only select this extension include image
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("image files", "*.jpeg", "*.jpg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(extensionFilter);
        //window eka open krl denne meken
        file = fileChooser.showOpenDialog(txtMessage.getScene().getWindow());
        if (file != null) {
            txtMessage.setText("File Selected");
            txtMessage.setEditable(false);
            //argument ekk widiyt pass krnwa file path eka
            byte[] imageToByte = Files.readAllBytes(file.toPath());
            //yawna image eka encode kranna puluwn decoder use krnne inizilize method ekedi decoder krgnno
            encodeImage = Base64.getEncoder().encodeToString(imageToByte);
            btnSendOnAction(actionEvent);
        }
    }


    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Navigation.minimize();
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}

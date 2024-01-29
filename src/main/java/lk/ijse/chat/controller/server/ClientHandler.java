package lk.ijse.chat.controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    Socket socket;
    List<ClientHandler> clientList;
    BufferedReader reader;
    PrintWriter writer;

    public ClientHandler(Socket socket, List<ClientHandler> clientList ) throws IOException {
        this.clientList = clientList;
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream() , true);
    }

    @Override
    public void run() {
        try {

            String message;
            while ((message=reader.readLine()) != null){

                if(message.equalsIgnoreCase("finish")){
                    break;
                }

                for (ClientHandler clientHandler : clientList) {
                    clientHandler.writer.println(message);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);

        }finally {
            try {
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
    }
}

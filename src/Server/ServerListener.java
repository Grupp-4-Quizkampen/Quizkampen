package Server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by: Anton Rolin
 * Date: 12/11/2020
 * Time: 13:22
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ServerListener {

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(44444)) {
            while(true){
                Socket clientSocket = serverSocket.accept();
                Server server = new Server(clientSocket);
                server.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by: Anton Rolin
 * Date: 12/11/2020
 * Time: 13:22
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Client extends Thread{
    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 44444;
        String message = "Hopp";

        try(Socket clientSocket = new Socket(address, port);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
            while(true) {
                out.writeObject(message);
                sleep(3000);
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

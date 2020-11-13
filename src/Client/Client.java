package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by: Anton Rolin
 * Date: 12/11/2020
 * Time: 13:22
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Client {

    public static void main(String[] args) throws UnknownHostException {

        InetAddress adr = InetAddress.getByName("localhost");
        int port = 44444;

        try (
                Socket clientSocket = new Socket(adr, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);) {
                System.out.println(" Client/Server-Setup complete\n");


            // Player: output.println("WELCOME ");

            Object fromServer;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws UnknownHostException {

        InetAddress adr = InetAddress.getByName("localhost");
        int port = 44444;

        try (
                Socket clientSocket = new Socket(adr, port);
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream((clientSocket.getInputStream()))) {



            System.out.println("What's your name?");
            Scanner scanner = new Scanner(System.in);
            String playerName = scanner.nextLine().trim();
            out.writeObject(playerName);

            Object fromServer;

            while ((fromServer = in.readObject()) != null) {
                System.out.println("Server: " + fromServer);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

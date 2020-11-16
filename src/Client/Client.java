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
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                System.out.println(" Client/Server-Setup complete\n");


            // Player: output.println("WELCOME ");

            System.out.println("What's your name?");
            Scanner scanner = new Scanner(System.in);
            String playerName = scanner.nextLine().trim();
            out.println(playerName);

            Object fromServer;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

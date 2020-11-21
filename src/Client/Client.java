package Client;

import Server.GameRound;
import Server.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    InetAddress adr;
    int port = 44444;
    Player opponent;
    Socket clientSocket;
    ObjectOutputStream out;
    ObjectInputStream in;

    public Client() {
        try {
            adr = InetAddress.getByName("localhost");
            clientSocket = new Socket(adr, port);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println(" Client/Server-Setup complete\n");


            // Player: output.println("WELCOME ");

            System.out.println("What's your name?");
            Scanner scanner = new Scanner(System.in);
            String playerName = scanner.nextLine().trim();
            out.writeObject(playerName);

            Object fromServer;

            while (true) {
                fromServer = in.readObject();
                if (fromServer instanceof GameRound) {
                    System.out.println(((GameRound) fromServer).getRoundQuestionList().get(0).getCategory());
                } else if (fromServer instanceof String) {
                    System.out.println("Server: " + fromServer);
                } else if (fromServer instanceof Player) {
                    opponent = (Player)fromServer;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        new Client();
    }

}

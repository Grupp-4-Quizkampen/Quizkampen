package Client;

import Server.GameRound;
import Server.Player;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    InetAddress adr;
    int port = 44444;
    Player opponent;
    Socket clientSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    private JFrame gui;

    public Client() {
        gui = new MainGUI(this);
    }

    public void connectToServer() {
        try {
            adr = InetAddress.getByName("localhost");
            clientSocket = new Socket(adr, port);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println(" Client/Server-Setup complete\n");


            // Player: output.println("WELCOME ");

            setPlayerName("test");

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

    public void setPlayerName(String playerName) throws IOException {
        out.writeObject(playerName);
    }

    public void answer(int answerIndex) throws IOException {
        out.writeObject(answerIndex);
    }

    public static void main(String[] args) {
        new Client();
    }

}

package Client;

import Server.GameRound;
import Server.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements ActionListener {
    InetAddress adr;
    int port = 44444;
    Player opponent;
    Socket clientSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    private final JFrame mainFrame;
    JPanel mainPanel = new JPanel();
    StartPanel startPanel;
    GamePanel gamePanel;

    public Client() {
        mainFrame = new MainGUI();
        mainFrame.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        startPanel = new StartPanel(this);
        mainPanel.add(BorderLayout.CENTER, startPanel);
        mainFrame.revalidate();
    }

    public void connectToServer() {
        try {
            adr = InetAddress.getByName("localhost");
            clientSocket = new Socket(adr, port);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println(" Client/Server-Setup complete\n");

            setPlayerName("test");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenToServer() {

        while (true) {
            Object fromServer;
            try {

                fromServer = in.readObject();
                if (fromServer instanceof GameRound) {
                    GameRound gameRound = (GameRound) fromServer;
                    System.out.println(gameRound.getRoundQuestionList().get(0).getCategory());
                    mainPanel.remove(startPanel);
//                    JLabel hi = new JLabel("hi");
//                    mainPanel.add(hi);
//                    gui.revalidate();
                    mainPanel.add(BorderLayout.CENTER, new GamePanel(gameRound.getRoundQuestionList().get(0)));
                    mainPanel.revalidate();
                    System.out.println("crash?");
                    Scanner scanner = new Scanner(System.in);
                    String something = scanner.nextLine();
                    System.out.println(something);
                } else if (fromServer instanceof String) {
                    System.out.println("Server: " + fromServer);
                } else if (fromServer instanceof Player) {
                    opponent = (Player) fromServer;
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(startPanel.getStartButton())) {
            System.out.println("hej");
            connectToServer();
            System.out.println("då");
            new Thread(this::listenToServer).start();
//            listenToServer();
//            listenToServer();
//            listenToServer();
//            listenToServer();
//            mainPanel.remove(startPanel);
//            JLabel hi = new JLabel("hi");
//            mainPanel.add(hi);
//            gui.revalidate();
        }
    }
}

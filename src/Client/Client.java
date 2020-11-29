package Client;

import Server.GameRound;
import Server.Player;
import Server.RoundResults;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

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
    ResultPanel resultPanel;

    public Client() {
        mainFrame = new MainGUI();
        mainFrame.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        startPanel = new StartPanel(this);
        gamePanel = new GamePanel(this);
        resultPanel = new ResultPanel();
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
                    GameRound nextRound = (GameRound) fromServer;
                    mainPanel.remove(startPanel);
                    mainPanel.add(gamePanel);
                    gamePanel.nextRound(nextRound);
                    mainPanel.revalidate();
                } else if (fromServer instanceof String) {
                    System.out.println("Server: " + fromServer);
                } else if (fromServer instanceof Player) {
                    opponent = (Player) fromServer;
                }   else if (fromServer instanceof RoundResults) {
                    mainPanel.remove(gamePanel);
                    mainPanel.add(resultPanel);
                    mainPanel.revalidate();
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPlayerName(String playerName) {
        try {
            out.writeObject(playerName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void submitResults(RoundResults roundResults) {
        try {
            out.writeObject(roundResults);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(startPanel.getStartButton())) {
            for (JButton button : startPanel.getButtonList()) {
                button.setEnabled(false);
            }
            startPanel.getStartButton().setEnabled(false);
            startPanel.getNameField().setEnabled(false);
            connectToServer();
            new Thread(this::listenToServer).start();
        }
    }
}

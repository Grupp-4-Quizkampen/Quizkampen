package Client;

import Server.GameRound;
import Server.Player;
import Server.PlayerData;
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
    PlayerData localPlayerData;
    PlayerData opponentPlayerData;

    public Client() {
        mainFrame = new MainGUI();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setSize(new Dimension(1000, 750));
        mainFrame.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        startPanel = new StartPanel(this);
        gamePanel = new GamePanel(this);
        resultPanel = new ResultPanel(this);
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

            localPlayerData = new PlayerData(startPanel.getNameField().getText(), startPanel.getChosenAvatarIndex());
            sendPlayerData();

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
                    mainPanel.remove(resultPanel);
                    mainPanel.add(gamePanel = new GamePanel(this));
                    gamePanel.nextRound(nextRound);
                    mainPanel.revalidate();
                } else if (fromServer instanceof String) {
                    System.out.println("Server: " + fromServer);
                } else if (fromServer instanceof Player) {
                    opponent = (Player) fromServer;
                } else if (fromServer instanceof RoundResults) {
                    mainPanel.remove(gamePanel);
                    resultPanel.updatePlayer2Results((RoundResults) fromServer);
                    mainPanel.add(resultPanel);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                } else if (fromServer instanceof PlayerData) {
                    opponentPlayerData = (PlayerData)fromServer;
                    resultPanel.setPlayerData(localPlayerData, opponentPlayerData);
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void requestNextRound() {
        try {
            out.writeObject(Boolean.TRUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPlayerData() {
        try {
            out.writeObject(localPlayerData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void submitResults(RoundResults roundResults) {
        try {
            resultPanel.updatePlayer1Results(roundResults);
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

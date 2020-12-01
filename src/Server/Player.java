package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class Player extends Thread implements Serializable {

    String playerName;
    Game activeGame;
    int currentRoundIndex = 0;
    ObjectOutputStream out;
    ObjectInputStream in;
    ArrayList<RoundResults> roundResultsList = new ArrayList<>();
    public Player opponent;
    PlayerData playerData;


    public Player(Socket socket) {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());  //Connects to the socket provided by Server
            out.writeObject("Welcome, to Quizkampen!");
            out.writeObject("Waiting for an opponent to connect to the game...");
            new Thread(this::listenToClient).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenToClient() {
        while (true) {
            Object fromClient;
            try {
                fromClient = in.readObject();
                if (fromClient instanceof RoundResults) {

                    // save round results, to be able to check for later
                    roundResultsList.add((RoundResults) fromClient);

                    // check if opponent has stored round results
                    if (opponent.roundResultsList.size() > 0) {
                        System.out.println("Opponent result list exists");
                        RoundResults opponentsRoundResults = opponent.roundResultsList.get(activeGame.currentRoundIndex);
                        if (opponent.roundResultsList.size() == currentRoundIndex) {
                            System.out.println("sending results");
                            // ok yey, both players has finished round and ready to receive results from their opponents
                            opponent.sendRoundResults(roundResultsList.get(activeGame.currentRoundIndex));
                            sendRoundResults(opponentsRoundResults);
                        }
                    }
                } else if (fromClient instanceof PlayerData) {
                    setPlayerData((PlayerData) fromClient);
                } else if (fromClient instanceof Boolean) {
                    out.writeObject(activeGame.getGameRounds().get(currentRoundIndex++));
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    private void sendRoundResults(RoundResults roundResults) {
        try {
            out.writeObject(roundResults);
            System.out.println(roundResults);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerData(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public void run() {
        try {
            out.writeObject("All players are connected, we are ready to play!");
            out.writeObject(opponent.getPlayerData());
        } catch (IOException e) {
            e.printStackTrace();
        }

        GameRound currentRound = activeGame.getGameRounds().get(currentRoundIndex++);
        System.out.println("Question sent");
        try {
            out.writeObject(currentRound);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setActiveGame(Game activeGame) {
        this.activeGame = activeGame;
    }
}


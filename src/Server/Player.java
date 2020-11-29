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
    ObjectOutputStream out;
    ObjectInputStream in;
    ArrayList<RoundResults> roundResultsList = new ArrayList<>();
    public Player opponent;


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
                    roundResultsList.add((RoundResults)fromClient);

                    // check if opponent has stored round results
                    RoundResults opponentsRoundResults = opponent.roundResultsList.get(activeGame.currentRoundIndex);
                    if (opponentsRoundResults != null) {

                        // ok yey, both players has finished round and ready to recieve results from their opponents
                        opponent.sendRoundResults(roundResultsList.get(activeGame.currentRoundIndex));
                        sendRoundResults(opponentsRoundResults);
                    }


                } else if (fromClient instanceof String) {
                    setPlayerName((String)fromClient);
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendRoundResults(RoundResults roundResults) {
        try {
            out.writeObject(roundResults);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void run() {
        try {
            out.writeObject("All players are connected, we are ready to play!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        GameRound currentRound = activeGame.getGameRounds().get(0);
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


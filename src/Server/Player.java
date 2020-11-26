package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Player extends Thread {

    Socket socket;
    String playerName;
    Game activeGame;
    ObjectOutputStream out;
    ObjectInputStream in;
    List<ArrayList<Boolean>> gameResults = new ArrayList<>();



    public Player(Socket socket) {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());  //Connects to the socket provided by Server
            out.writeObject("Welcome, to Quizkampen!");
            out.writeObject("Waiting for an opponent to connect to the game...");
            while (true) {
                Object fromClient;
                try {
                    fromClient = in.readObject();
                    if (fromClient instanceof RoundResults) {
                        saveResults((RoundResults)fromClient);
                    } else if (fromClient instanceof String) {
                        setPlayerName((String)fromClient);
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveResults(RoundResults roundResults) {
        gameResults.add(roundResults);
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void run() {
        try {
            out.writeObject("All players are connected, we are ready to play!");
            //Client asks for playerName
            playerName = (String)in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        GameRound currentRound = activeGame.getGameRounds().get(0);
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


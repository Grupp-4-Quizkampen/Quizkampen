package Server;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Player extends Thread {

    Socket socket;
    String playerName;
    Game activeGame;
    ObjectInputStream in;
    ObjectOutputStream out;


    public Player(Socket socket) {
        this.socket = socket;
        try {
            in = new ObjectInputStream(socket.getInputStream());  //Connects to the socket provided by Server
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject("Welcome, to Quizkampen!");
            out.writeObject("Waiting for an opponent to connect to the game...");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void run() {

    // Tråden startar bara när två spelare har anslutit till spelet.


        try {
            out.writeObject("All players are connected, we are ready to play!");
            //Client asks for playerName
            playerName = (String)in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Question> round1Questions = activeGame.getGameRounds().get(0).getRoundQuestionList();
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


package Server;

import java.io.*;
import java.net.Socket;

public class Player extends Thread {

    Socket socket;
    String playerName;
    Game activeGame;
    BufferedReader in;
    PrintWriter out;


    public Player(Socket socket) {
        this.socket = socket;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));  //Connects to the socket provided by Server
            out.println("Welcome, to Quizkampen!");
            out.println("Waiting for an opponent to connect to the game...");

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

        out.println("All players are connected, we are ready to play!");
        //Client asks for playerName
        try {
            playerName = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Fortsätt skriva kod här: vad som sker när spelet börjar...




    }
}


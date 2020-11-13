package Server;

import java.io.*;
import java.net.Socket;

/**
 * Cretated by Madeleine Lennhamn
 * Date: 2020-11-13
 * Time: 10:35
 * Project: $ {PROJECT_NAME}
 * Copyright: MIT
 */
public class Player extends Thread {

    Socket socket;
    Player playerName;
    Game game;
    BufferedReader in;
    PrintWriter out;


    public Player(Socket socket) {
        this.socket = socket;
        this.playerName = playerName;
        try {
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));  //lyssnar efter anslutning

            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Welcome to Quizkampen!");
            out.println("Waiting for second opponent to connect to the game...");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerName(Player playerName) {
        this.playerName = playerName;
    }

    @Override
    public void run() {

    // Tråden startar bara när två spelare har anslutit till spelet.

        out.println("All players are connected, we are ready to play!");

        //Fortsätt skriva kod här: vad som sker när spelet börjar...




    }
}


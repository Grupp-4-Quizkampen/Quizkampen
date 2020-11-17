package Server;

import java.io.*;
import java.net.Socket;

public class Player extends Thread {

    Socket socket;
    Object playerName;
    Game activeGame;
    ObjectOutputStream out;
    ObjectInputStream in;



    public Player(Socket socket) {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());  //Connects to the socket provided by Server
            out.writeObject("Welcome to Quizkampen " + in.readObject() + "!");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void run() {


        //Client asks for playerName
        try {
            out.writeObject("All players are connected, we are ready to play!");
            playerName = in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }





    }
}


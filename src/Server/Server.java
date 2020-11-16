package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(44444);
        System.out.println("Server is connected!");


        while (true) {

            //  Game game = new Game();

            /* Listens for a connection to be made to this socket and accepts it.
            The method blocks until a connection is made.*/
            Player player1 = new Player(serverSocket.accept());
            Player player2 = new Player(serverSocket.accept());

            new Game(player1, player2);

        }
    }
}

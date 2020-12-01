package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(44444);
        System.out.println("Server is connected!");


        while (true) {

            Player player1 = new Player(serverSocket.accept());
            Player player2 = new Player(serverSocket.accept());

            new Game(player1, player2);

        }
    }
}

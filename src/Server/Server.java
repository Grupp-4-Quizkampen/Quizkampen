package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by: Anton Rolin
 * Date: 12/11/2020
 * Time: 13:30
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Server extends Thread{
    Socket clientSocket;

    Server(Socket clientSocket){
            this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try(ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())){
            while(true)
            System.out.println(in.readObject());


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

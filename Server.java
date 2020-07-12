package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() throws IOException {
        ServerSocket server = new ServerSocket(4242);
        Socket sock = new Socket("190.165.1.103", 4242);
      //  Socket sock = serverSock.accept();
        Socket socket = null;

        try {
            server = new ServerSocket(4242);
            System.out.println("Сервер запущен!");
            clients = new Vector<>();

            while (true){
                socket=server.accept();
                System.out.println("клиент подключился");
                clients.add(new ClientHandler(this,socket));
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }
}

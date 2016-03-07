/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.chatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public final class ChatServer implements Runnable {

    private ServerSocket server;
    private List<Connection> clients;

    public ChatServer() throws IOException {
        this.server = new ServerSocket(30000);
        clients = new LinkedList<>();
        run();
    }

    public ChatServer(int port) throws IOException {
        this.server = new ServerSocket(port);
        clients = new LinkedList<>();
        run();
    }

    public static void main(String[] args) throws IOException {
        new ChatServer();
    }

    public void broadcast(String msg) {
        for(Connection c:clients){
            c.printMsg(msg);
        }
    }

    @Override
    public void run() {
        int id=0;
        while(true){
            try {
                Socket client=server.accept();
                Connection c=new Connection(id++,client,this);
                clients.add(c); 
                c.start();
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(1);
            }
        }
    }

}

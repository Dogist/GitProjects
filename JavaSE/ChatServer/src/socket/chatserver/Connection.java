/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class Connection extends Thread {

    private int id;
    private Socket socket;
    private ChatServer server;
    private BufferedReader read;
    private PrintWriter write;

    public Connection(int id, Socket socket, ChatServer serv) {
        this.id = id;
        this.socket = socket;
        this.server = serv;
        try {
            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            write = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                //server.broadcast(id + ": " + read.readLine());
                server.broadcast(read.readLine());
            }
        } catch (Exception e) {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {

        }
    }

    public void printMsg(String msg) {
        write.println(msg);
    }

}

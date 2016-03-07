/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.multithreadeddaytimeserver;

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
public class MultithreadedDaytimeServer implements Runnable {

    private ServerSocket server;

    public MultithreadedDaytimeServer() throws IOException {
        this.server = new ServerSocket(32013);
        run();
    }

    public MultithreadedDaytimeServer(int port) throws IOException {
        this.server = new ServerSocket(port);
        run();
    }

    public static void main(String[] args) throws IOException {
        new MultithreadedDaytimeServer();
    }

    @Override
    public void run() {
        int id = 0;
        while (true) {
            try {
                Socket client = server.accept();
                DaytimeThread c = new DaytimeThread(client);
                c.start();
            } catch (IOException ex) {
                Logger.getLogger(MultithreadedDaytimeServer.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(1);
            }
        }
    }
}

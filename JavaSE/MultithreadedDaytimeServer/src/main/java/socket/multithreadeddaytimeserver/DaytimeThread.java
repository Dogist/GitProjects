/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.multithreadeddaytimeserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class DaytimeThread extends Thread {

    private final Socket connection;

    public DaytimeThread(Socket client) {
        connection = client;
    }

    @Override
    public void run() {
        try {
            PrintWriter write = new PrintWriter(connection.getOutputStream(), true);
            Date now = new Date();
            write.println(now.toString());
        } catch (Exception e) {
            Logger.getLogger(DaytimeThread.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(DaytimeThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

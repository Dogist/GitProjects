/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.minimalserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public final class MinServerWithEcho {

    private ServerSocket server;

    public MinServerWithEcho() throws IOException {
        server = new ServerSocket(1234);
        handle();
    }

    public MinServerWithEcho(int port) throws IOException {
        this.server = new ServerSocket(port);
        handle();
    }

    public void handle() {
        while (true) {
            Socket client = null;
            BufferedReader read = null;
            PrintWriter write = null;
            try {
                client = server.accept();

                read = new BufferedReader(new InputStreamReader(client.getInputStream()));
                write = new PrintWriter(client.getOutputStream(), true);
                //write.println("Willkommen!");
                System.out.println("Server started...");
                String str;
                do {
                    str = read.readLine();
                    if (str != null&&!str.contentEquals(".bye")) {
                        write.println(str);
                        System.out.println("Got (Server): " + str);
                    }
                }while(!str.contentEquals(".bye")) ;
                write.println("Server terminated...");
                System.out.println("Server terminated...");
            } catch (IOException ex) {
                Logger.getLogger(MinServerWithEcho.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (write != null) {
                    write.close();
                }
                if (read != null) {
                    try {
                        read.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MinServerWithEcho.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MinServerWithEcho.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        MinServerWithEcho server;
        try {
            server = new MinServerWithEcho();
        } catch (IOException ex) {
            Logger.getLogger(MinServerWithEcho.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

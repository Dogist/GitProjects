/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.websocket_chatserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.glassfish.tyrus.server.Server;

/**
 *
 * @author masix
 */
public class WebSocketServer {

    public final static int SERVERPORT = 32000;

    public static void main(String[] args) {
        runServer();
    }

    public static void runServer() {

        Server server = new Server("localhost", SERVERPORT, "/websockets", null, ChatServerEndpoint.class);

        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Please press a key to stop the server.");

            reader.readLine();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }
}

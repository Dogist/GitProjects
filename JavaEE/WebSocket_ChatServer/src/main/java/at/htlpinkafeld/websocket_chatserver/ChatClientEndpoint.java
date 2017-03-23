/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.websocket_chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import org.glassfish.tyrus.client.ClientManager;

/**
 *
 * @author masix
 */
@ClientEndpoint(encoders = MessageEncoderDecoder.class, decoders = MessageEncoderDecoder.class)
public class ChatClientEndpoint implements Runnable {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private boolean sessionOpen = true;
    private Session sess;

    public ChatClientEndpoint() {
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("You connected to the chat");
        sess = session;
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println(message);
    }

    @OnMessage
    public void onMessage(Message message, Session session) {
        System.out.println(message.getSender() + " sent: " + message.getMessage());
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        sessionOpen = false;
    }

    @Override
    public void run() {
        while (sessionOpen) {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            try {
                String userInput = bufferRead.readLine();
                if (sessionOpen) {
                    if ("quit".equals(userInput)) {
                        sess.close();
                    } else {
                        sess.getAsyncRemote().sendText(userInput);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ClientManager client = ClientManager.createClient();
        try {
            ChatClientEndpoint cce = new ChatClientEndpoint();

            client.connectToServer(cce,
                    new URI("ws://localhost:"
                            + ChatServerEndpoint.SERVERPORT
                            + "/websockets/chat"));

            cce.run();

        } catch (DeploymentException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}

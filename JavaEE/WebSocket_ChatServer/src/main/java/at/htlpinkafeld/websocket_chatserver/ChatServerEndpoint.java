/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.websocket_chatserver;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author masix
 */
@ServerEndpoint(value = "/chat", encoders = MessageEncoderDecoder.class, decoders = MessageEncoderDecoder.class)
public class ChatServerEndpoint {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    public final static int SERVERPORT = 32000;

    @OnOpen
    public void onOpen(Session session) {
        for (Session s : sessions) {
            try {
                s.getBasicRemote().sendText(session.getId() + " connected to chat");
            } catch (IOException ex) {
                Logger.getLogger(ChatServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        for (Session s : sessions) {
            try {
                if (!session.equals(s)) {
                    s.getBasicRemote().sendObject(new Message(session, message));
                }
            } catch (IOException | EncodeException ex) {
                Logger.getLogger(ChatServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        sessions.remove(session);
        for (Session s : sessions) {
            try {
                s.getBasicRemote().sendText(String.format("%s left chat", session.getId()));
            } catch (IOException ex) {
                Logger.getLogger(ChatServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

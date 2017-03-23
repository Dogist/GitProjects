package at.htlpinkafeld.websocket_echoserver;

import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.server.ServerEndpoint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author masix
 */
@ServerEndpoint(value = "/echo")
public class EchoServerEndpoint {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    boolean light = false;

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        switch (message) {
            case "quit":
                try {
                    session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "Server ended"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "off":
                light = false;
                return "Light toggled off";
            case "on":
                light = true;
                return "Light toggled on";
            default:
                return "Light is toggled " + light;
        }

        return message;

    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
}

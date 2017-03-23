/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.websocket_chatserver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author masix
 */
public class MessageEncoderDecoder implements Encoder.Binary<Message>, Decoder.Binary<Message> {

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Message decode(ByteBuffer bytes) throws DecodeException {
        Message message = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            try {
                bis = new ByteArrayInputStream(bytes.array());
                ois = new ObjectInputStream(bis);
            } catch (IOException ex) {
                Logger.getLogger(MessageEncoderDecoder.class.getName()).log(Level.SEVERE, null, ex);
            }
            message = (Message) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MessageEncoderDecoder.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException ex) {
                    Logger.getLogger(MessageEncoderDecoder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    Logger.getLogger(MessageEncoderDecoder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return message;
    }

    @Override
    public boolean willDecode(ByteBuffer bytes) {
        return true;
    }

    @Override
    public ByteBuffer encode(Message message) throws EncodeException {
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(message);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(MessageEncoderDecoder.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(MessageEncoderDecoder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException ex) {
                    Logger.getLogger(MessageEncoderDecoder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ByteBuffer.wrap(bytes);
    }

}

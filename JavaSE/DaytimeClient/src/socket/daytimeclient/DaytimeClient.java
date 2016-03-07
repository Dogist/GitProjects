/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.daytimeclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *
 * @author Martin Six
 */
public class DaytimeClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length>0)
            System.out.println(getTime(args[0]));
        else
            System.out.println(getTime(null));
    }

    public static String getTime(String host) {
        SocketAddress addr;
        if (host != null && host.isEmpty()) {
            addr = new InetSocketAddress(host, 32013);
        } else {
            addr = new InetSocketAddress("appserver.htlpinkafeld.at", 13);
        }
        Socket timeSocket = new Socket();
        try {
            timeSocket.connect(addr, 5000);
            BufferedReader read=new BufferedReader(new InputStreamReader(timeSocket.getInputStream()));
            return read.readLine();
        } catch (IOException ex) {
            return ex.toString();
        }
    }
}

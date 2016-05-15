/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.maxservice;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class MaxServer {

    public static final int PORT = 30000;

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            new MaxServer();
        } else {
            new MaxServer(Integer.parseInt(args[0]));
        }
    }

    public MaxServer(int port) {

        try {
            LocateRegistry.createRegistry(port);
            MaxServerImpl max = new MaxServerImpl();
            IMax stub = (IMax) UnicastRemoteObject.exportObject(max, port);
            //RemoteServer.setLog(System.out);

            Registry registry = LocateRegistry.getRegistry(port);
            registry.rebind("maxservice", stub);
        } catch (RemoteException ex) {
            Logger.getLogger(MaxServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MaxServer() {
        this(PORT);
    }

}

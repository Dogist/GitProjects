/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.say;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Martin Six
 */
public class SayServer {

    private final static int PORT = 30000;

    public SayServer(int port) throws RemoteException {

        LocateRegistry.createRegistry(port);

        SayImpl say = new SayImpl();
        Say stub = (Say) UnicastRemoteObject.exportObject(say, port);

        Registry reg = LocateRegistry.getRegistry(port);

        reg.rebind("Say", stub);
    }

    public SayServer() throws RemoteException {
        this(PORT);
    }

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     */
    public static void main(String[] args) throws RemoteException {
        new SayServer();
    }

}

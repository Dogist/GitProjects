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

    private static int PORT;

    public SayServer(int port) throws RemoteException {
        PORT = port;

        LocateRegistry.createRegistry(PORT);

        SayImpl say = new SayImpl();
        Say stub = (Say) UnicastRemoteObject.exportObject(say, PORT);

        Registry reg = LocateRegistry.getRegistry(PORT);

        reg.rebind("Say", stub);
    }

    public SayServer() throws RemoteException {
        this(30000);
    }

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     */
    public static void main(String[] args) throws RemoteException {
        new SayServer();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.say;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Martin Six
 */
public class SayClient {

    private static final int PORT = 30000;

    private Say say;

    public SayClient(int port) throws RemoteException, NotBoundException {

        Registry reg = LocateRegistry.getRegistry(port);
        say = (Say) reg.lookup("Say");
    }

    public SayClient() throws RemoteException, NotBoundException {
        this(PORT);
    }

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     */
    public static void main(String[] args) throws RemoteException, NotBoundException {
        SayClient sc = new SayClient();
    }

    public String say(String s) throws RemoteException {
        return say.say(s);
    }
}

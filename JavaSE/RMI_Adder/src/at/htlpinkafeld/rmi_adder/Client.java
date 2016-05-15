/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rmi_adder;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Martin Six
 */
public class Client {

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     */
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        Adder adder = (Adder) registry.lookup("Adder");
        System.out.println(adder.add(47, 11));
    }

}

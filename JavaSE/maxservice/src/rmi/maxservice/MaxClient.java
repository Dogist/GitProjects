/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.maxservice;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class MaxClient {

    private IMax maxServ;

    public MaxClient(int port) {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            maxServ = (IMax) registry.lookup("maxservice");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MaxClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MaxClient() {
        this(MaxServer.PORT);
    }

    public static void main(String[] args) {
        MaxClient maxC;
        if (args == null || args.length == 0) {
            maxC = new MaxClient();
        } else {
            maxC = new MaxClient(Integer.parseInt(args[0]));
        }
        System.out.println(maxC.getMax(5, 6));
    }

    public double getMax(double first, double second) {
        try {
            return maxServ.maxtwo(first, second);
        } catch (RemoteException ex) {
            Logger.getLogger(MaxClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}

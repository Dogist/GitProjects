/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rmi_adder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Martin Six
 */
public class AdderImpl extends UnicastRemoteObject implements Adder  {

    public AdderImpl() throws RemoteException{
    }

    @Override
    public int add(int x, int y) {
        return x + y;
    }
}

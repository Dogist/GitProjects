/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rmi_adder;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Martin Six
 */
public interface Adder extends Remote {

    int add(int x, int y) throws RemoteException;
}

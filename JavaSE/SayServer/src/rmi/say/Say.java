/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.say;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Martin Six
 */
public interface Say extends Remote {

    public String say(String s) throws RemoteException;
}

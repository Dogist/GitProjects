/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rmi_timeservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Martin Six
 */
public interface TimeService extends Remote {

    public String getTime()
            throws RemoteException;

    public TimeStore storeTime(TimeStore store)
            throws RemoteException;

}

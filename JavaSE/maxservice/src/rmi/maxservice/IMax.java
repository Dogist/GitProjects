/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.maxservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Martin Six
 */
public interface IMax extends Remote{

    public double maxtwo(double a, double b) throws RemoteException;
}

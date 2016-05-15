/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.maxservice;

import java.rmi.RemoteException;

/**
 *
 * @author Martin Six
 */
public class MaxServerImpl implements IMax {

    @Override
    public double maxtwo(double a, double b) throws RemoteException{
        return Double.max(a, b);
    }

}

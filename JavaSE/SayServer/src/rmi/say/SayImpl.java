/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.say;

import java.rmi.RemoteException;

/**
 *
 * @author Martin Six
 */
public class SayImpl implements Say {

    @Override
    public String say(String s) throws RemoteException {
        System.out.println(s);
        return s;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rmi_timeservice;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 *
 * @author Martin Six
 */
public class TimeServiceRegistration {

    public static void main(String[] args) {
        System.setSecurityManager(new RMISecurityManager());
        try {
            System.out.println("Registering TimeService");
            TimeServiceImpl tsi = new TimeServiceImpl();
            Naming.rebind("TimeService", tsi);
            System.out.println("  Done.");
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(1);
        }
    }
}

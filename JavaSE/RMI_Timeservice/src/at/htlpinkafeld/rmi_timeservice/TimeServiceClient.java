/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rmi_timeservice;

import java.rmi.Naming;

/**
 *
 * @author Martin Six
 */
public class TimeServiceClient {

    public static void main(String[] args) {
        try {
            String host = "ph02";
            String port = "33666";
            String srv = "TimeService";
            String url = "rmi://" + host + ":" + port + "/" + srv;
            System.out.println("Looking-up TimeService " + url);
            TimeService ts = (TimeService) Naming.lookup(url);
            System.out.println("  Server time is " + ts.getTime());
            System.out.print("  MyTimeStore contains ");
            TimeStore tsd = new MyTimeStore();
            tsd = ts.storeTime(tsd);
            System.out.println(tsd.getTime());
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(1);
        }

    }
}

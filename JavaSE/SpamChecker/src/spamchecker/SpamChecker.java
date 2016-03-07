/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spamchecker;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Martin Six
 */
public class SpamChecker {

    public static void main(String[] args) {
        for (String host : args) {
            if (isSpammer(host)) {
                System.out.println(host+" is a known spammer.");
            } else {
                System.out.println(host+" appears legitimate.");
            }
        }
    }

    public static boolean isSpammer(String host) {
        boolean res=true;
        try {
            InetAddress i = InetAddress.getByName(host);
            String[] hostBytes = i.getHostAddress().split("\\.");
            String checkHostString = hostBytes[3] + "." + hostBytes[2] + "." + hostBytes[1] + "." + hostBytes[0] + ".zen.spamhaus.org";
            InetAddress resIAddr = InetAddress.getByName(checkHostString);
        } catch (UnknownHostException ex) {
            res=false;
        }
        return res;
    }
}

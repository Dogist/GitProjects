/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package url.authandread;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Martin Six
 */
public class AuthAndRead {

    public static void main(String[] args) {
        System.out.println(readFromUrl("http://appserver.htlpinkafeld.at/news"));
        System.out.println(readFromSecretUrl("http://appserver.htlpinkafeld.at/secretnews"));        
    }

    public static String readFromUrl(String url) {
        StringBuilder page = new StringBuilder();
        try {
            URL u = new URL(url);
            InputStream stream = u.openStream();
            Scanner sc = new Scanner(stream);
            while (sc.hasNextLine()) {
                page.append(sc.nextLine());
                page.append('\n');
            }
        } catch (MalformedURLException ex) {
            System.out.println(ex.toString() + ex.getMessage());

        } catch (IOException ex) {
            System.out.println(ex.toString() + ex.getMessage());
        }
        return page.toString();
    }

    public static String readFromSecretUrl(String url) {
        String page;
        Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("htlpinkafeld", "geheim".toCharArray());
                }
            });
        page=readFromUrl(url);
        Authenticator.setDefault(null);
        return page;
    }
}

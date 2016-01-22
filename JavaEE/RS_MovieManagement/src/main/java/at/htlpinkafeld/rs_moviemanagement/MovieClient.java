/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_moviemanagement;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Martin Six
 */
public class MovieClient {

    public static void main(String[] args) {
        InputStream is = null;

        try {
            URL url = new URL("http://localhost:8084/RS_MovieManagement/webapi/movieservice/movies");
            is = url.openStream();
            System.out.println(new Scanner(is).useDelimiter("\\Z").next());
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        try {
            URL url = new URL("http://localhost:8084/RS_MovieManagement/webapi/movieservice/Titanic");
            is = url.openStream();
            System.out.println(new Scanner(is).useDelimiter("\\Z").next());
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }
}

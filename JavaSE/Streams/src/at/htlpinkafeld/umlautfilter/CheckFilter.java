/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.umlautfilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 *
 * @author Martin Six
 */
public class CheckFilter {

    public static void main(String[] args) throws IOException {
        StringWriter sw = new StringWriter();
        UmlautWriter uml = new UmlautWriter(sw);
        uml.write('Ä');
        uml.write("ltere würden Möven mit Öl füttern");
        System.out.println("Write: " + sw);

        StringReader sr = new StringReader(sw.toString());
        UmlautReader ur = new UmlautReader(sr);
        BufferedReader br = new BufferedReader(ur);

        System.out.println("Read: " + br.readLine());
    }
}

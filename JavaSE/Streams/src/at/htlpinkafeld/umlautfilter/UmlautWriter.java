/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.umlautfilter;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Martin Six
 */
public class UmlautWriter extends FilterWriter {

    private final Map<Character, String> umlautMap;

    public UmlautWriter(Writer out) {
        super(out);
        umlautMap = new HashMap<>();
        umlautMap.put('Ä', "Ae");
        umlautMap.put('ä', "ae");
        umlautMap.put('Ö', "Oe");
        umlautMap.put('ö', "oe");
        umlautMap.put('Ü', "Ue");
        umlautMap.put('ü', "ue");
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        String newS = new String(str);
        for (Character c : umlautMap.keySet()) {
            newS = newS.replaceAll(c.toString(), umlautMap.get(c));
        }
        super.write(newS, off, newS.length());
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < (off + len); i++) {
            write(cbuf[i]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        if (umlautMap.containsKey((char) c)) {
            String uml = umlautMap.get((char) c);
            super.write(uml.charAt(0));
            super.write(uml.charAt(1));
        } else {
            super.write(c);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.umlautfilter;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Martin Six
 */
public class UmlautReader extends PushbackReader {

    private Map<Character, Character> letterUmlautMap;

    public UmlautReader(Reader in) {
        super(in);
        letterUmlautMap = new HashMap<>();
        letterUmlautMap.put('A', 'Ä');
        letterUmlautMap.put('a', 'ä');
        letterUmlautMap.put('O', 'Ö');
        letterUmlautMap.put('o', 'ö');
        letterUmlautMap.put('U', 'Ü');
        letterUmlautMap.put('u', 'ü');
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        char c;
        int i;
        for (i = off; i < (off + len); i++) {
            c = (char) read();
            if (c == -1 || c == '￿') {
                break;
            } else {
                cbuf[i] = c;
            }
        }
        if (i != off) {
            return i - off;
        } else {
            return -1;
        }
    }

    @Override
    public int read() throws IOException {
        char c1 = (char) super.read();
        if (letterUmlautMap.containsKey((char) c1)) {
            int c2 = super.read();
            if (c2 == 'e') {
                c1 = letterUmlautMap.get((char) c1);
            } else {
                unread(c2);
            }
        }
        return c1;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.articletext.writer;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Martin Six
 */
public class CryptoWriter extends FilterWriter {

    private final char key;

    public CryptoWriter(Writer out, char key) {
        super(out);
        this.key = key;
    }

    public CryptoWriter(Writer out) {
        this(out, (char) 0xF3F3);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        String newS = "";
        for (char s : str.toCharArray()) {
            newS += s ^ key;
        }
        super.write(newS, off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        char[] newBuff = new char[off + len];
        for (int i = 0; i < off + len; i++) {
            newBuff[i] = (char) (cbuf[i] ^ key);
        }
        super.write(newBuff, off, len);
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c ^ key);
    }

}

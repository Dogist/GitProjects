/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.articletext.reader;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author Martin Six
 */
public class CryptoReader extends FilterReader {

    private final char key;

    public CryptoReader(Reader in, char key) {
        super(in);
        this.key = key;
    }

    public CryptoReader(Reader in) {
        this(in, (char) 0xF3F3);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int x = super.read(cbuf, off, len);
        if (x != -1) {
            for (int i = 0; i <= x; i++) {
                cbuf[i] = (char) (cbuf[i] ^ key);
            }
        }
        return x;
    }

    @Override
    public int read() throws IOException {
        int x = super.read();
        if (x != -1) {
            return x ^ key;
        }
        return x;
    }

}

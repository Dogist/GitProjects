/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.articletext.writer;

import at.htlpinkafeld.articletext.pojo.Article;
import java.io.BufferedWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class ArticleCSVWriter extends FilterWriter {

    private final BufferedWriter bw;

    public ArticleCSVWriter(Writer writer) {
        super(writer);
        this.bw = new BufferedWriter(writer);
    }

    public void write(Article a) {
        try {
            bw.append(a.getId() + ";" + a.getName() + ";" + a.getPrice());
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(ArticleCSVWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

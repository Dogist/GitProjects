/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.articletext.reader;

import at.htlpinkafeld.articletext.pojo.Article;
import java.io.BufferedReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class ArticleCSVReader extends FilterReader {

    private BufferedReader br;

    public ArticleCSVReader(Reader in) {
        super(in);
        br = new BufferedReader(in);
    }

    public Article readArticle() {
        try {
            String buff = br.readLine();
            if (buff != null) {
                String[] parts = buff.split(";");
                parts[2] = parts[2].substring(0, parts[2].length() - 1);
                return new Article(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]));
            }
        } catch (IOException ex) {
            Logger.getLogger(ArticleCSVReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.articletext;

import at.htlpinkafeld.articletext.writer.ArticleCSVWriter;
import at.htlpinkafeld.articletext.writer.CryptoWriter;
import at.htlpinkafeld.articletext.reader.CryptoReader;
import at.htlpinkafeld.articletext.reader.ArticleCSVReader;
import at.htlpinkafeld.articletext.pojo.Article;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class ArticleTextDao {

    private ArticleCSVWriter acsvw;
    private ArticleCSVReader acsvr;

    public ArticleTextDao() {
        try {
//            this.acsvw = new ArticleCSVWriter(new CryptoWriter(new FileWriter("Articles.csv")));
//            this.acsvr = new ArticleCSVReader(new CryptoReader(new FileReader("Articles.csv")));
            this.acsvw = new ArticleCSVWriter(new FileWriter("Articles.csv"));
            this.acsvr = new ArticleCSVReader(new FileReader("Articles.csv"));
        } catch (IOException ex) {
            Logger.getLogger(ArticleTextDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void write(List<Article> articleList) {
        for (Article a : articleList) {
            acsvw.write(a);
        }

    }

    public List<Article> read() {
        List<Article> artList = new ArrayList<>();
        Article a = acsvr.readArticle();
        while (a != null) {
            artList.add(a);
            a = acsvr.readArticle();
        }
        return artList;
    }

}
/*
public class ArticleTextDao {

    private BufferedWriter bw;
    private BufferedReader br;

    public ArticleTextDao() {
        try {
            this.bw = new BufferedWriter(new FileWriter("Articles.csv"));
            this.br = new BufferedReader(new FileReader("Articles.csv"));
        } catch (IOException ex) {
            Logger.getLogger(ArticleTextDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void write(List<Article> articleList) {
        for (Article a : articleList) {
            try {
                bw.append(a.getId() + "," + a.getName() + "," + a.getPrice() + ";");
                bw.flush();
            } catch (IOException ex) {
                Logger.getLogger(ArticleTextDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public List<Article> read() {
        List<Article> artList = new ArrayList<>();
        try {
            String buff = br.readLine();
            if (buff != null) {
                String[] objects = buff.split(";");
                for (String s : objects) {
                    String[] parts = s.split(",");
                    artList.add(new Article(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2])));
                }
                return artList;
            }
        } catch (IOException ex) {
            Logger.getLogger(ArticleTextDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}




 */

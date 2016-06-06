/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.articletext;

import at.htlpinkafeld.articletext.pojo.Article;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class ArticleText {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Article> artList = new ArrayList<>();
        artList.add(new Article(1, "NoPlan", 4.50));
        artList.add(new Article(2, "Papier", 1.33));
        artList.add(new Article(3, "WC", 450));
        ArticleTextDao atd = new ArticleTextDao();
        atd.write(artList);
        artList = atd.read();
        for (Article a : artList) {
            System.out.println(a);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.articletext;

import at.htlpinkafeld.articletext.pojo.Article;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin Six
 */
public class ArticleTextDaoTest {

    public ArticleTextDaoTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of write and read methods, of class ArticleTextDao.
     */
    @Test
    public void testWriteAndRead() {
        System.out.println("write and read");
        List<Article> artList = new ArrayList<>();
        artList.add(new Article(1, "NoPlan", 4.50));
        artList.add(new Article(2, "Papier", 1.33));
        artList.add(new Article(3, "WC", 450));
        ArticleTextDao dao = new ArticleTextDao();
        dao.write(artList);

        List<Article> result = new ArrayList<>();
        result = dao.read();
        assertTrue(artList.containsAll(result));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbc_articlemanager.dao;

import at.htlpinkafeld.jdbc_articlemanager.pojo.Article;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public interface Article_DAO {
    public List<Article> getArticleList();
    public void insertArticle(Article art);
    public void updateArticle(Article art);
    public void deleteArticle(Article art);
}

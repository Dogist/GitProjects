/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbc_articlemanager.service;

import at.htlpinkafeld.jdbc_articlemanager.dao.Article_DAO;
import at.htlpinkafeld.jdbc_articlemanager.pojo.Article;
import java.beans.PropertyChangeEvent;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class ArticleService {

    private final List<Article> artL;
    private final Article_DAO artDAO;
    private final ArticleChangeListener artCL;

    public ArticleService(Article_DAO artDAO, ArticleChangeListener artCL) {
        this.artDAO = artDAO;
        this.artL = artDAO.getArticleList();
        this.artCL = artCL;
    }

    public void addArticle(Article art) {
        art.addPropertyChangeListener(this.artCL);
        this.artL.add(art);
        this.artDAO.insertArticle(art);
    }

    public Article getArticle(int index) {
        return this.artL.get(index);
    }

    public void updateArticle(int index, Article art) {
        Article oldArt = this.artL.get(index);
        art.addPropertyChangeListener(this.artCL);
        this.artL.set(index, art);
        this.artDAO.updateArticle(art);
        if (oldArt.getName().compareTo(art.getName()) != 0) {
            art.firePropertyChange(new PropertyChangeEvent(art, "Name", oldArt.getName(), art.getName()));
        }
        if (oldArt.getPrice() != art.getPrice()) {
            art.firePropertyChange(new PropertyChangeEvent(art, "Price", oldArt.getPrice(), art.getPrice()));
        }
    }

    public void PrintAll() {
        for (Article a : artL) {
            System.out.println(a);
        }
    }

    public void removeArticles(int[] rows) {
        for (int i = rows[rows.length - 1]; i >= rows[0]; i--) {
            this.artDAO.deleteArticle(this.artL.get(i));
            this.artL.remove(i);
        }
    }

    public int size() {
        return this.artL.size();
    }
}

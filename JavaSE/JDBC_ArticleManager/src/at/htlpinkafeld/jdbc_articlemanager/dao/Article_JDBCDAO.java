/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbc_articlemanager.dao;

import at.htlpinkafeld.jdbc_articlemanager.pojo.Article;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class Article_JDBCDAO implements Article_DAO {

    private final static String GET_ALLARTICLE_STATEMENT = "SELECT * FROM article";

    @Override
    public List<Article> getArticleList() {
        List<Article> artList = new ArrayList<>();

        try {
            Connection con = ConnectionManager.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALLARTICLE_STATEMENT);

            while (rs.next()) {
                artList.add(new Article(rs.getInt("artno"), rs.getString("name"), rs.getDouble("price")));
            }
        } catch (SQLException ex) {
            System.out.println("Error while fetching Article-Data: " + ex);

        }

        return artList;
    }

    @Override
    public void insertArticle(Article art) {
        try {
            Connection con = ConnectionManager.getInstance().getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into article values(" + art.getNr() + ", '" + art.getName() + "', " + art.getPrice() + ")");

        } catch (SQLException ex) {
            System.out.println("Error while updating Article-Data: " + ex);
        }
    }

    @Override
    public void updateArticle(Article art) {
        try {
            Connection con = ConnectionManager.getInstance().getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("update article set name='" + art.getName() + "', price=" + art.getPrice() + " where artno=" + art.getNr() + ";");

        } catch (SQLException ex) {
            System.out.println("Error while updating Article-Data: " + ex);
        }
    }

    @Override
    public void deleteArticle(Article art) {
        try {
            Connection con = ConnectionManager.getInstance().getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from article where artno=" + art.getNr() + ";");

        } catch (SQLException ex) {
            System.out.println("Error while updating Article-Data: " + ex);
        }
    }
}

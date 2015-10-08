/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbc_articlemanager.gui;

import at.htlpinkafeld.jdbc_articlemanager.pojo.Article;
import at.htlpinkafeld.jdbc_articlemanager.service.ArticleService;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Martin Six
 */
public class ArticleTableModel extends AbstractTableModel {

    private final ArticleService artS;
    private final String columnNames[] = {"Nr", "Name", "Price"};

    public ArticleTableModel(ArticleService artS) {
        this.artS = artS;
    }

    public ArticleService getArtS() {
        return artS;
    }
    
    @Override
    public int getRowCount() {
        return artS.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Article art = this.artS.getArticle(rowIndex);
        switch (columnIndex) {
            case 0:
                return art.getNr();
            case 1:
                return art.getName();
            case 2:
                return art.getPrice();
            default:
                return "???";
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (this.columnNames.length > columnIndex) {
            return this.columnNames[columnIndex];
        }
        return "???";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Article art = this.artS.getArticle(rowIndex);
        int nr = art.getNr();
        double price = art.getPrice();
        String name = art.getName();
        switch (columnIndex) {
            case 0: nr=(int)aValue;
                break;
            case 1: name=(String)aValue;
                break;
            case 2: price=(double)aValue;
        }
        this.artS.updateArticle(rowIndex, new Article(nr, name, price));
    }
}

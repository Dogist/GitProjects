/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbc_articlemanager.gui;

import at.htlpinkafeld.jdbc_articlemanager.pojo.Article;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Martin Six
 */
public class AddAction extends AbstractAction {

    private final InputPanel iP;
    private final ArticleTableModel artTM;

    public AddAction(InputPanel iP, ArticleTableModel artTM) {
        super("Add");
        this.iP = iP;
        this.artTM = artTM;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (iP.getNrText() != 0 && iP.getNameText().compareTo("") != 0 && iP.getPriceText() != 0) {
            Article art = new Article(iP.getNrText(), iP.getNameText(), iP.getPriceText());
            iP.getArtS().addArticle(art);
            artTM.fireTableRowsInserted(iP.getArtS().size(), iP.getArtS().size());
        }
    }

}

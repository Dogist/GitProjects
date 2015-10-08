/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbc_articlemanager.gui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JTable;

/**
 *
 * @author Martin Six
 */
public class RemoveAction extends AbstractAction {

    private final JTable artT;
    private final ArticleTableModel artTM;

    RemoveAction(JTable artT, ArticleTableModel artTM) {
        super("Remove");
        this.artT = artT;
        this.artTM = artTM;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selection = artT.getSelectedRows();
        if (selection.length != 0) {
            this.artTM.getArtS().removeArticles(selection);
            this.artTM.fireTableDataChanged();
        }
    }

}

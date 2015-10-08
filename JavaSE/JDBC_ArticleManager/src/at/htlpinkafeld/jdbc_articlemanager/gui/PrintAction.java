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
public class PrintAction extends AbstractAction {

    private final InputPanel iP;
    public PrintAction(InputPanel iP) {
        super("Print");
        this.iP=iP;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        iP.getArtS().PrintAll();
    }    
}

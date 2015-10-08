/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbc_articlemanager.service;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;

/**
 *
 * @author Martin Six
 */
public class ArticleChangeListener implements PropertyChangeListener {
    
    private final JLabel statusBar;

    public ArticleChangeListener(JLabel statusBar) {
        this.statusBar = statusBar;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.statusBar.setText("The " + evt.getPropertyName() + " of the article has changed from " + evt.getOldValue() + " to " + evt.getNewValue() + "!");
    }

}

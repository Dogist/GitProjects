/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbc_articlemanager.gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Martin Six
 */
public class TablePanel extends JPanel{

    private final ArticleTableModel artTM;
    private final JLabel statusBar;
    
    TablePanel(ArticleTableModel artTM, JLabel statusBar) {
        this.setLayout(new BorderLayout());
        this.artTM=artTM;
        this.statusBar=statusBar;
        
        JTable tab=new JTable(artTM);
        JScrollPane sp=new JScrollPane(tab);
        this.add(sp, BorderLayout.CENTER);
        JButton deleteR = new JButton (new RemoveAction(tab, artTM));
        this.add(deleteR, BorderLayout.SOUTH);
        this.add(this.statusBar, BorderLayout.NORTH);
    }
    
}

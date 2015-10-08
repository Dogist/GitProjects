/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbc_articlemanager.gui;

import at.htlpinkafeld.jdbc_articlemanager.service.ArticleService;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Martin Six
 */
public class InputPanel extends JPanel {

    private final ArticleService artS;
    private final JTextField nrTextF = new JTextField();
    private final JTextField nameTextF = new JTextField();
    private final JTextField priceTextF = new JTextField();

    public InputPanel(ArticleService artS, ArticleTableModel artTM) {
        this.artS = artS;

        this.setLayout(new GridLayout(0, 2));

        this.add(new JLabel("Nr:"));
        this.add(nrTextF);
        this.add(new JLabel("Name:"));
        this.add(nameTextF);
        this.add(new JLabel("Price:"));
        this.add(priceTextF);

        JButton addB = new JButton("Add");
        addB.setAction(new AddAction(this, artTM));
        this.add(addB);

        JButton printB = new JButton("Print");
        printB.setAction(new PrintAction(this));
        this.add(printB);

        this.setVisible(true);
    }

    public int getNrText() {
        if (!nrTextF.getText().contentEquals("")) {
            return Integer.parseInt(nrTextF.getText());
        }
        return 0;
    }

    public String getNameText() {
        return nameTextF.getText();
    }

    public double getPriceText() {
        if (!priceTextF.getText().contentEquals("")) {
            return Double.parseDouble(priceTextF.getText());
        }
        return 0;
    }

    public ArticleService getArtS() {
        return artS;
    }

}

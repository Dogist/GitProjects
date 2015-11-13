/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbc_articlemanager.gui;

import at.htlpinkafeld.jdbc_articlemanager.dao.Article_DAO;
import at.htlpinkafeld.jdbc_articlemanager.dao.Article_JDBCDAO;
import at.htlpinkafeld.jdbc_articlemanager.pojo.AMProperties;
import at.htlpinkafeld.jdbc_articlemanager.service.ArticleChangeListener;
import at.htlpinkafeld.jdbc_articlemanager.service.ArticleService;
import java.awt.HeadlessException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Martin Six
 */
public class JDBC_ArticleManager extends JFrame {

    public JDBC_ArticleManager() throws HeadlessException {
        super("Article Manager");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane jTP = new JTabbedPane();
        JLabel statusBar = new JLabel("Nothing");

        Article_DAO artDAO = new Article_JDBCDAO();
        ArticleService artS = new ArticleService(artDAO, new ArticleChangeListener(statusBar));

        ArticleTableModel artTM = new ArticleTableModel(artS);
        JPanel tabPan = new TablePanel(artTM, statusBar);

        InputPanel artF = new InputPanel(artS, artTM);

        this.add(jTP);
        jTP.addTab("Input Panel", artF);
        jTP.addTab("Table", tabPan);
        
        AMProperties amp= AMProperties.getAMProperties();
        this.setSize(amp.getIntegerProperty(AMProperties.FRAMEWIDTH), amp.getIntegerProperty(AMProperties.FRAMEHIGHT));
        this.setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
//        Properties p = new Properties();
//        p.setProperty("frameWidth", "640");
//        p.setProperty("frameHight", "480");
//        p.setProperty("jdbcURL", "jdbc:hsqldb:file:");
//        p.setProperty("dbURL", "ArticleDB\\article");
//        p.setProperty("urlSettings", ";shutdown=true;ifexists=true");
//        p.setProperty("dbUser", "db");
//        p.setProperty("dbPW", "");
//        try {
//            p.store(new FileOutputStream("articlemanager.properties"), null);
//        } catch (IOException ex) {
//            Logger.getLogger(JDBC_ArticleManager.class.getName()).log(Level.SEVERE, null, ex);
//        }

        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {
                new JDBC_ArticleManager();
            }
        });
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbc_articlemanager.dao;

import at.htlpinkafeld.jdbc_articlemanager.pojo.AMProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class ConnectionManager {

    private static ConnectionManager conM = null;
    private Connection conn = null;

    private ConnectionManager() throws SQLException {
        AMProperties amp= AMProperties.getAMProperties();
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            //String url = "jdbc:hsqldb:file:ArticleDB/article;shutdown=true;ifexists=true";
            String url = amp.getStringProperty(AMProperties.JDBCURL)+amp.getStringProperty(AMProperties.DBURL)+amp.getStringProperty(AMProperties.URLSETTINGS);
            conn = DriverManager.getConnection(url, amp.getStringProperty(AMProperties.DBUSER), amp.getStringProperty(AMProperties.DBPW));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ConnectionManager getInstance() throws SQLException {
        if (conM == null) {
            conM = new ConnectionManager();
        }
        return conM;
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

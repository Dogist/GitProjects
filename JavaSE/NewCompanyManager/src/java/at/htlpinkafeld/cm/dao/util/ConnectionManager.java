/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Martin Six
 */
public class ConnectionManager {

    private volatile static ConnectionManager connectionManager = null;

    private DataSource datSrc;

    public ConnectionManager() {
        DBProperties properties = DBProperties.getInstance();

        try {
            Context ctx = new javax.naming.InitialContext();
            String dsName = properties.getStringProperty("dataSource");
            datSrc = (DataSource) ctx.lookup("java:comp/env/" + dsName);

        } catch (NamingException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized ConnectionManager getInstance() throws SQLException {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    public Connection getConnection() throws SQLException {
        return datSrc.getConnection();
    }

}

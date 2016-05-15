/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbcbase;

import at.htlpinkafeld.jdbcbase.WrappedConnection;
import at.htlpinkafeld.jdbcbase.DBProperties;
import java.sql.Connection;
import java.sql.DriverManager;
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
public abstract class ConnectionManager {

    private volatile static ConnectionManager connectionManager = null;

    public static synchronized ConnectionManager getInstance() throws SQLException {
        DBProperties properties = DBProperties.getInstance();
        if (connectionManager == null) {
            if (properties.isLocal()) {
                connectionManager = new LocalConnectionManager();
            } else {
                connectionManager = new WebConnectionManager();
            }
        }
        return connectionManager;
    }

    public abstract WrappedConnection getWrappedConnection() throws SQLException;

    public abstract void closeFinally();

    private static class LocalConnectionManager extends ConnectionManager {

        private WrappedConnection wrappedConnection;

        private LocalConnectionManager() throws SQLException {
            DBProperties properties = DBProperties.getInstance();

            try {
                Class.forName(properties.getStringProperty("dbDriver"));
                String url = properties.getStringProperty("dbUrl");
                String usr = properties.getStringProperty("user");
                String pwd = properties.getStringProperty("pwd");
                Connection conn = DriverManager.getConnection(url, usr, pwd);
                this.wrappedConnection = new WrappedConnection(conn, false);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        @Override
        public WrappedConnection getWrappedConnection() throws SQLException {
            return wrappedConnection;
        }

        @Override
        public void closeFinally() {
            // close the connection at the end of the program
            try {
                this.wrappedConnection.getConn().close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static class WebConnectionManager extends ConnectionManager {

        private DataSource datSrc;

        private WebConnectionManager() throws SQLException {
            DBProperties properties = DBProperties.getInstance();

            try {
                Context ctx = new javax.naming.InitialContext();
                String dsName = properties.getStringProperty("dataSource");
                datSrc = (DataSource) ctx.lookup("java:comp/env/" + dsName);

            } catch (NamingException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public WrappedConnection getWrappedConnection() throws SQLException {
            return new WrappedConnection(this.datSrc.getConnection(), true);
        }

        @Override
        public void closeFinally() {
            // close at the end not needed, do nothing    
        }

    }
}

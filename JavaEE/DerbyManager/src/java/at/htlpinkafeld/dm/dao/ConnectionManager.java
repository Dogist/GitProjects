/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.dm.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Martin Six
 */
public class ConnectionManager {
    private static final String DATASOURCE = "jdbc/sample";
    private static ConnectionManager connMgrInst = null;
    private DataSource ds = null;

    public static synchronized ConnectionManager getInst() {
        if (connMgrInst == null) {
            connMgrInst = new ConnectionManager();
        }

        return connMgrInst;
    }

    private ConnectionManager() {
        try {
            Context ctx;
            ctx = new javax.naming.InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/" + DATASOURCE);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConn() {
        Connection retVal = null;
        try {
            retVal = ds.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return retVal;
    }
}

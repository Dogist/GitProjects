/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.dao.connectionData;

import at.htlpinkafeld.database_manager.dao.database.DAOType;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class ConnectionDataManagement {

    public static List<ConnectionData> getConnectionData() {
        List<ConnectionData> connDataList = new LinkedList<>();

        try {

            File f = new File("dbproperties.properties");
            Properties p = new Properties();
            p.load(new FileReader(f));

            DAOType daot = (DAOType) p.get("daoType");
            ConnectionData cd = null;

            switch (daot) {
                case HSQLDB:
                    cd = new HSQLDBConnectionData(p.getProperty("username"), p.getProperty("password"), p.getProperty("path"));
                    break;
                case ORACLE:
                    cd = new OracleConnectionData(p.getProperty("url"), Integer.parseInt(p.getProperty("port")), p.getProperty("dbname"), p.getProperty("username"), p.getProperty("password"));
                    break;
                case MySQL:
                    cd = new MySQLConnectionData(p.getProperty("url"), Integer.parseInt(p.getProperty("port")), p.getProperty("dbname"), p.getProperty("username"), p.getProperty("password"));
                    break;
            }
            if (cd != null) {
                connDataList.add(cd);
            }

        } catch (IOException ex) {
            Logger.getLogger(ConnectionDataManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connDataList;
    }

    public static void insertConnectionData(ConnectionData cd) {

        Properties p = new Properties();
        switch (cd.getDaoType()) {
            case HSQLDB:
                HSQLDBConnectionData cdHsql = (HSQLDBConnectionData) cd;
                p.put("path", cdHsql.getPath());
                break;
            case ORACLE:
                OracleConnectionData cdOracle = (OracleConnectionData) cd;
                p.put("dbname", cdOracle.getDbName());
                p.put("port", cdOracle.getPort());
                p.put("url", cdOracle.getServerUrl());
                break;
            case MySQL:
                MySQLConnectionData cdMySQL = (MySQLConnectionData) cd;
                p.put("dbname", cdMySQL.getDbName());
                p.put("port", cdMySQL.getPort());
                p.put("url", cdMySQL.getServerUrl());
                break;
        }
        p.put("daoType", cd.getDaoType());
        p.put("username", cd.getUsername());
        p.put("password", cd.getPassword());
        try {
            File f = new File("dbproperties.properties");
            f.delete();
            p.store(new FileWriter(f), "");
        } catch (IOException ex) {
            Logger.getLogger(ConnectionDataManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteConnectionData(ConnectionData cd) {

    }
}

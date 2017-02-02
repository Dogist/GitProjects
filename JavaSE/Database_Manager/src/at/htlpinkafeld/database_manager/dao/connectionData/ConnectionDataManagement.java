/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.dao.connectionData;

import at.htlpinkafeld.database_manager.dao.database.DAOType;
import static at.htlpinkafeld.database_manager.dao.database.DAOType.HSQLDB;
import static at.htlpinkafeld.database_manager.dao.database.DAOType.MySQL;
import static at.htlpinkafeld.database_manager.dao.database.DAOType.ORACLE;
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
            f.createNewFile();

            Properties p = new Properties();
            p.load(new FileReader(f));

            DAOType daot;

            List<Integer> cdIdxList = new LinkedList<>();

            for (Object key : p.keySet()) {
                int x = Integer.parseInt(key.toString().split("_")[0]);
                if (!cdIdxList.contains(x)) {
                    cdIdxList.add(x);
                }
            }
            for (Integer idx : cdIdxList) {

                daot = DAOType.valueOf(p.get(idx + "_daoType").toString());
                ConnectionData cd = null;

                switch (daot) {
                    case HSQLDB:
                        cd = new HSQLDBConnectionData(p.getProperty(idx + "_username"), p.getProperty(idx + "_password"), p.getProperty(idx + "_path"));
                        break;
                    case ORACLE:
                        cd = new OracleConnectionData(p.getProperty(idx + "_url"), Integer.parseInt(p.getProperty(idx + "_port")), p.getProperty(idx + "_dbname"), p.getProperty(idx + "_username"), p.getProperty(idx + "_password"));
                        break;
                    case MySQL:
                        cd = new MySQLConnectionData(p.getProperty(idx + "_url"), Integer.parseInt(p.getProperty(idx + "_port")), p.getProperty(idx + "_dbname"), p.getProperty(idx + "_username"), p.getProperty(idx + "_password"));
                        break;
                }
                if (cd != null) {
                    connDataList.add(cd);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectionDataManagement.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return connDataList;
    }

    public static void insertConnectionData(ConnectionData cd) {

        try {

            File f = new File("dbproperties.properties");
            f.createNewFile();

            Properties p = new Properties();
            p.load(new FileReader(f));

            int idx = 0;
            for (Object e : p.keySet()) {
                int x = Integer.parseInt(e.toString().split("_")[0]);
                if (idx <= x) {
                    idx = x + 1;
                }
            }

            p.putIfAbsent(idx + "_daoType", cd.getDaoType().name());
//            p.putIfAbsent(idx + "_username", cd.getUsername());
//            p.putIfAbsent(idx + "_password", cd.getPassword());

            switch (cd.getDaoType()) {
                case HSQLDB:
                    HSQLDBConnectionData hcd = (HSQLDBConnectionData) cd;
                    p.putIfAbsent(idx + "_path", hcd.getPath());
                    break;
                case ORACLE:
                    OracleConnectionData ocd = (OracleConnectionData) cd;
                    p.putIfAbsent(idx + "_url", ocd.getServerUrl());
                    p.putIfAbsent(idx + "_port", Integer.toString(ocd.getPort()));
                    p.putIfAbsent(idx + "_dbname", ocd.getDbName());
                    break;
                case MySQL:
                    MySQLConnectionData mcd = (MySQLConnectionData) cd;
                    p.putIfAbsent(idx + "_url", mcd.getServerUrl());
                    p.putIfAbsent(idx + "_port", Integer.toString(mcd.getPort()));
                    p.putIfAbsent(idx + "_dbname", mcd.getDbName());
                    break;
            }

            p.store(new FileWriter(f), "");

        } catch (IOException ex) {
            Logger.getLogger(ConnectionDataManagement.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteConnectionData(ConnectionData connectionData) {
        try {
            File f = new File("dbproperties.properties");
            Properties p = new Properties();
            p.load(new FileReader(f));

            DAOType daot;

            List<Integer> cdIdxList = new LinkedList<>();

            for (Object key : p.keySet()) {
                int x = Integer.parseInt(key.toString().split("_")[0]);
                if (!cdIdxList.contains(x)) {
                    cdIdxList.add(x);
                }
            }
            for (Integer idx : cdIdxList) {

                daot = DAOType.valueOf(p.get(idx + "_daoType").toString());
                ConnectionData cd = null;

                switch (daot) {
                    case HSQLDB:
                        cd = new HSQLDBConnectionData(p.getProperty(idx + "_username"), p.getProperty(idx + "_password"), p.getProperty(idx + "_path"));
                        break;
                    case ORACLE:
                        cd = new OracleConnectionData(p.getProperty(idx + "_url"), Integer.parseInt(p.getProperty(idx + "_port")), p.getProperty(idx + "_dbname"), p.getProperty(idx + "_username"), p.getProperty(idx + "_password"));
                        break;
                    case MySQL:
                        cd = new MySQLConnectionData(p.getProperty(idx + "_url"), Integer.parseInt(p.getProperty(idx + "_port")), p.getProperty(idx + "_dbname"), p.getProperty(idx + "_username"), p.getProperty(idx + "_password"));
                        break;
                }
                if (cd.equals(connectionData)) {
                    p.remove(idx + "_daoType");
                    switch (cd.getDaoType()) {
                        case HSQLDB:
                            p.remove(idx + "_path");
                            break;
                        case ORACLE:
                            p.remove(idx + "_url");
                            p.remove(idx + "_port");
                            p.remove(idx + "_dbname");
                            break;
                        case MySQL:
                            p.remove(idx + "_url");
                            p.remove(idx + "_port");
                            p.remove(idx + "_dbname");
                            break;
                    }
                }
            }
            p.store(new FileWriter(f), "");

        } catch (IOException ex) {
            Logger.getLogger(ConnectionDataManagement.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }
}

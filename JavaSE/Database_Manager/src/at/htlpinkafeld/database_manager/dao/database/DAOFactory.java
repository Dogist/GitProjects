/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.dao.database;

import at.htlpinkafeld.database_manager.dao.connectionData.ConnectionData;
import at.htlpinkafeld.database_manager.dao.connectionData.HSQLDBConnectionData;
import at.htlpinkafeld.database_manager.dao.connectionData.MySQLConnectionData;
import at.htlpinkafeld.database_manager.dao.connectionData.OracleConnectionData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class DAOFactory {

    private static DAOFactory daoFactory;
    private static Connection dbConnection;
    private static ConnectionData connectionData;

    private final DeptDAO deptDAO;
    private final EmpDAO empDAO;

    protected Connection getDbConnection() {
        return dbConnection;
    }

    private DAOFactory() {
        deptDAO = new SQLDeptDAO();
        empDAO = new SQLEmpDAO();
    }

    public DeptDAO getDeptDAO() {
        return deptDAO;
    }

    public EmpDAO getEmpDAO() {
        return empDAO;
    }

    public static ConnectionData getConnectionData() {
        return connectionData;
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public static void setDAOFactory(ConnectionData connectionData) throws SQLException {
        DAOFactory.connectionData = connectionData;
        if (connectionData == null) {
            dbConnection = null;
        } else {
            switch (connectionData.getDaoType()) {
                case HSQLDB:
                    HSQLDBConnectionData hconDat = (HSQLDBConnectionData) connectionData;
                    dbConnection = DriverManager.getConnection("jdbc:hsqldb:file:" + hconDat.getPath() + ";shutdown=true;ifexists=true;hsqldb.lock_file=false;", hconDat.getUsername(), hconDat.getPassword());
                    break;
                case ORACLE:
                    OracleConnectionData oconDat = (OracleConnectionData) connectionData;
                    dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@" + oconDat.getServerUrl() + ":" + oconDat.getPort() + ":" + oconDat.getDbName(), oconDat.getUsername(), oconDat.getPassword());
                    break;
                case MySQL: {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");

                        MySQLConnectionData myconDat = (MySQLConnectionData) connectionData;
                        dbConnection = DriverManager.getConnection("jdbc:mysql://" + myconDat.getServerUrl() + ":" + myconDat.getPort() + "/" + myconDat.getDbName(), myconDat.getUsername(), myconDat.getPassword());
                        break;
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (dbConnection != null) {
                dbConnection.setAutoCommit(false);
            }
        }
    }

}

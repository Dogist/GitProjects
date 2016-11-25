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
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.pool.OracleDataSourceFactory;
import org.hsqldb.jdbc.JDBCCommonDataSource;
import org.hsqldb.jdbc.JDBCDataSource;

/**
 *
 * @author Martin Six
 */
public class DAOFactory {

    private static DataSource dataSource;

    private static DAOFactory daoFactory;
    private static Connection dbConnection;

    public Connection getDbConnection() {
        return dbConnection;
    }

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public static void setDAOFactory(ConnectionData connectionData) throws SQLException {
        switch (connectionData.getDaoType()) {
            case HSQLDB:
                HSQLDBConnectionData hconDat = (HSQLDBConnectionData) connectionData;
                dbConnection = DriverManager.getConnection("jdbc:hsqldb:file:" + hconDat.getPath(), hconDat.getUsername(), hconDat.getPassword());
                break;
            case ORACLE:
                OracleConnectionData oconDat = (OracleConnectionData) connectionData;
                dbConnection = DriverManager.getConnection("jdbc:oracle:", oconDat.getUsername(), oconDat.getPassword());
                break;
            case MySQL:
                MySQLConnectionData myconDat = (MySQLConnectionData) connectionData;
                dbConnection = DriverManager.getConnection(url, myconDat.getUsername(), myconDat.getPassword());
                break;
        }
    }
}

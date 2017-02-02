/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.dao.connectionData;

import at.htlpinkafeld.database_manager.dao.database.DAOType;
import java.util.Objects;

/**
 *
 * @author Martin Six
 */
public class MySQLConnectionData extends ConnectionData {

    private String serverUrl;
    private int port;
    private String dbName;

    public MySQLConnectionData(String serverUrl, int port, String dbName, String username, String password) {
        super(DAOType.MySQL, username, password);
        this.serverUrl = serverUrl;
        this.port = port;
        this.dbName = dbName;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 59 * hash + Objects.hashCode(this.serverUrl);
        hash = 59 * hash + this.port;
        hash = 59 * hash + Objects.hashCode(this.dbName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MySQLConnectionData other = (MySQLConnectionData) obj;
        if (!Objects.equals(super.getUsername(), other.getUsername())) {
            return false;
        }
        if (!Objects.equals(this.getPassword(), other.getPassword())) {
            return false;
        }
        if (this.getDaoType() != other.getDaoType()) {
            return false;
        }
        if (!Objects.equals(this.port, other.port)) {
            return false;
        }
        if (!Objects.equals(this.dbName, other.dbName)) {
            return false;
        }
        if (!Objects.equals(this.serverUrl, other.serverUrl)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MySQL - " + serverUrl + ":" + port + "/" + dbName;
    }

}

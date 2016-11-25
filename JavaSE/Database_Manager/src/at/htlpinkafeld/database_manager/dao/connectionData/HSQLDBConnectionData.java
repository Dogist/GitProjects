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
public class HSQLDBConnectionData extends ConnectionData {

    private String path;

    public HSQLDBConnectionData(String username, String password, String path) {
        super(DAOType.HSQLDB, username, password);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 29 * hash + Objects.hashCode(this.path);
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
        final HSQLDBConnectionData other = (HSQLDBConnectionData) obj;
        if (!Objects.equals(super.getUsername(), other.getUsername())) {
            return false;
        }
        if (!Objects.equals(this.getPassword(), other.getPassword())) {
            return false;
        }
        if (this.getDaoType() != other.getDaoType()) {
            return false;
        }
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        return true;
    }
}

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
public abstract class ConnectionData {

    private final DAOType daoType;
    private String username;
    private String password;

    public ConnectionData(DAOType daoType, String username, String password) {
        this.daoType = daoType;
        this.username = username;
        this.password = password;
    }

    public DAOType getDaoType() {
        return daoType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.daoType);
        hash = 23 * hash + Objects.hashCode(this.username);
        hash = 23 * hash + Objects.hashCode(this.password);
        return hash;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbcbase;

/**
 *
 * @author Martin Six
 */
public class DBProperties {

    private volatile static DBProperties inst = null;
    private boolean local;

    public static synchronized DBProperties getInstance() {
        if (inst == null) {
            inst = new DBProperties(false);
        }
        return inst;
    }

    public DBProperties(boolean b) {
        local = b;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public String getStringProperty(String key) {
        switch (key) {
            case "user":
                return "db";
            case "pwd":
                return "";
            case "dbDriver":
                return "org.hsqldb.jdbcDriver";
            case "dbUrl":
                return "jdbc:hsqldb:file:C:/Users/User/Documents/NetBeansDB/Company/company;shutdown=true;ifexists=true";
            case "dataSource":
                return "jdbc/company";
        }
        return "unknown key " + key;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.utils;

import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class PLProperties {

    private static volatile PLProperties plp;
    private Properties p;
    public final static String COLUMNHEADINGCOLOR = "columnheadingcolor";
    public final static String MSGCOLOR = "msgcolor";
    public final static String USER = "user";
    public final static String MAXPERSON = "maxperson";

    private PLProperties() {
        this.p = new Properties();
        try {
            String s = getRootPath("PersListLogin_Filter");
            this.p.load(new FileInputStream(s + "\\personlist.properties"));
        } catch (Exception ex) {
            Logger.getLogger(PLProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String getRootPath(String appName) throws URISyntaxException {
        Path  path = Paths.get(PLProperties.class.getResource("PLProperties.class").toURI());
        return path.getParent().getParent().getParent().getParent().getParent().getParent().toString();
    }

    public static synchronized PLProperties getInstance() {
        if (plp == null) {
            plp = new PLProperties();
        }
        return plp;
    }

    public String getStringProperty(String propertyName) {
        return this.p.getProperty(propertyName);
    }

    public int getIntProperty(String propertyName) {
        if (propertyName.equals(MAXPERSON)) {
            return Integer.parseInt(this.p.getProperty(propertyName));
        }
        return -1;
    }
}

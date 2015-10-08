/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbc_articlemanager.pojo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class AMProperties {

    private static AMProperties amp;
    private Properties p;
    public final static String FRAMEWIDTH = "frameWidth";
    public final static String FRAMEHIGHT = "frameHight";
    public final static String JDBCURL = "jdbcURL";
    public final static String DBURL = "dbURL";
    public final static String URLSETTINGS = "urlSettings";
    public final static String DBUSER = "dbUser";
    public final static String DBPW = "dbPW";

    private AMProperties() {
        this.p = new Properties();
        try {
            this.p.load(new FileInputStream("articlemanager.properties"));
        } catch (IOException ex) {
            Logger.getLogger(AMProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static AMProperties getAMProperties() {
        if (amp == null) {
            amp=new AMProperties();
        }
        return amp;
    }
    
    public String getStringProperty(String propertyName){
        return this.p.getProperty(propertyName);
    }
    
    public Integer getIntegerProperty(String propertyName){
        if((propertyName.matches(AMProperties.FRAMEHIGHT))||propertyName.matches(AMProperties.FRAMEWIDTH))
            return Integer.parseInt(this.p.getProperty(propertyName));
        else
            return null;
    }
}

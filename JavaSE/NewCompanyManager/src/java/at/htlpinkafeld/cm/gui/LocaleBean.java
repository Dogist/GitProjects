/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.gui;

import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Martin Six
 */
@ManagedBean
@SessionScoped
public final class LocaleBean {

    private Locale userLocale;

    public LocaleBean() {
        userLocale = new Locale("de");
    }

    public Locale getUserLocale() {
        return userLocale;
    }

    public String getLanguage() {
        return userLocale.getLanguage();
    }

    public void setLanguage(String language) {
        userLocale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(userLocale);
    }

}

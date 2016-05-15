/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.fc;

import java.util.ResourceBundle;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Martin Six
 */
public class FractionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Fraction frac = null;
        if (string != null) {
            try {
                String[] parts = string.split("/");
                if (parts.length == 2) {
                    if (parts[0].contains(" ")) {
                        String[] num = parts[0].split(" ");
                        frac = new Fraction(Integer.parseInt(num[0].trim()) * Integer.parseInt(parts[1].trim() + Integer.parseInt(num[1].trim())), Integer.parseInt(parts[1].trim()));
                    } else {
                        frac = new Fraction(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
                    }
                } else if (parts.length == 1) {
                    frac = new Fraction(Integer.parseInt(parts[0].trim()), 1);
                } else {
                    Application app = fc.getApplication();
                    ResourceBundle bundle = app.getResourceBundle(fc, "bundleVar");
                    throw new ConverterException(bundle.getString("error.fract_form"));
                }
            } catch (NumberFormatException | ConverterException ex) {
                Application app = fc.getApplication();
                ResourceBundle bundle = app.getResourceBundle(fc, "bundleVar");
                throw new ConverterException(bundle.getString("error.fract_form"));
            }
        }
        return frac;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Fraction f = (Fraction) o;
        if (f.getDenom() != 1) {
            if (f.getNum() < f.getDenom()) {
                return f.getNum() + "/" + f.getDenom();
            } else {
                return f.getNum() / f.getDenom() + " " + f.getNum() % f.getDenom() + "/" + f.getDenom();
            }
        } else {
            return Integer.toString(f.getNum());
        }
    }

}

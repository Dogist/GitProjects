/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.fc;

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
                if (parts.length > 2 || parts.length < 2) {
                    throw new ConverterException("Invalid Fraction-Format");
                }
                frac = new Fraction(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
            } catch (Exception e) {
                throw new ConverterException("Invalid Fraction-Format");
            }
        }
        return frac;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Fraction f = (Fraction) o;
        return f.getNum() + "/" + f.getDenom();
    }

}

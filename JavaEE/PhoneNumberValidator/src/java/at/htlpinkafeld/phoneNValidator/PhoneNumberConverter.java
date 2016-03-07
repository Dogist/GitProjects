/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.phoneNValidator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Martin Six
 */
public class PhoneNumberConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        PhoneNumber pn = null;
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Phone Number format!", null);
        try {
            if(!string.contains("+")||!string.contains("/")||!string.contains(" "))
                throw new ConverterException(message);
            int cc = Integer.parseInt(string.substring(1, string.indexOf(" ")));
            int ac = Integer.parseInt(string.substring(string.indexOf(" ")+1, string.indexOf("/")));
            String num = string.substring(string.indexOf("/")+1);
            pn=new PhoneNumber(cc, ac, num);
        } catch (Exception e) {
            
            throw new ConverterException(message);
        }
        return pn;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        PhoneNumber pn = (PhoneNumber) o;
        return "+" + pn.getCountryCode() + " " + pn.getAreaCode() + "/" + pn.getNumber();
    }

}

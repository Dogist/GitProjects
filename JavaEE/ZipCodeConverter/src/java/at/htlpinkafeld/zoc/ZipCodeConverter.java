/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.zoc;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Martin Six
 */
public class ZipCodeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        ZipCode retVal = null;
        try {
            if (string != null) {
                String[] parts = string.split("-");
                if (parts.length == 2) {
                    retVal = new ZipCode(parts[0].charAt(0), Integer.parseInt(parts[1]));
                } else if (parts.length == 1) {
                    retVal = new ZipCode(Integer.parseInt(parts[0]));
                }
                if (parts.length > 2 || (parts[0].length() != 1 && parts.length != 1)) {
                    throw new Exception();
                }
            }
            return retVal;
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid ZIP-Code format!", null);
            throw new ConverterException(message);
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String retVal = null;
        if (o != null && o instanceof ZipCode) {
            ZipCode c = (ZipCode) o;
            retVal = c.getCountry() + "-" + Integer.toString(c.getNumber());
            return retVal;
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Object is no ZipCode or Null", null);
        throw new ConverterException(message);
    }

    public void validateZipCode(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        ZipCode z = (ZipCode) o;
        FacesMessage message = new FacesMessage("The zip-code has the wrong length");

        switch (z.getCountry()) {
            case 'A':
                if (String.valueOf(z.getNumber()).length()!=4) {
                    throw new ValidatorException(message);
                }
                break;
            case 'D':
                if (String.valueOf(z.getNumber()).length()!=5) {
                    throw new ValidatorException(message);
                }
                break;
        }
    }
}

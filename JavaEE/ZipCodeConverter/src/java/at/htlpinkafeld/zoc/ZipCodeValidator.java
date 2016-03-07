/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.zoc;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Martin Six
 */
public class ZipCodeValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
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

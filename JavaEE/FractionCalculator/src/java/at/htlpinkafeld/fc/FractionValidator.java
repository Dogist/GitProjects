/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.fc;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Martin Six
 */
public class FractionValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        Fraction f = (Fraction) o;
        if (Math.abs(f.getNum()) > f.getDenom()) {
            throw new ValidatorException(new FacesMessage("No proper Fraction!"));
        }
    }

}

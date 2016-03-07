/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.gui;

import at.htlpinkafeld.pojo.Product;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Martin Six
 */
public class ProductConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Product p =new Product();
        if (string != null) {
            try {
                int id = Integer.parseInt(string);
                p.setId(id);
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid ID Format", "");
                throw new ConverterException(fm);
            }
        }
        return p;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String retVal=null;
        if( o instanceof Product){
            retVal= String.valueOf(((Product) o).getId());
        }
        return retVal;
    }

}

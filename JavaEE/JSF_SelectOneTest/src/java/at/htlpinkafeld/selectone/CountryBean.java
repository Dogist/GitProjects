/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.selectone;

import java.util.LinkedList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Martin Six
 */
public class CountryBean {
    private String Country;
    private List<SelectItem> countryList;
    
    public CountryBean() {
        countryList = new LinkedList<>();
        countryList.add(new SelectItem("0043", "Austria"));
        countryList.add(new SelectItem("0049", "Germany"));
        countryList.add(new SelectItem("0041", "Switzerland"));
        countryList.add(new SelectItem("0046", "Schweden"));
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public List<SelectItem> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<SelectItem> countryList) {
        this.countryList = countryList;
    }
}

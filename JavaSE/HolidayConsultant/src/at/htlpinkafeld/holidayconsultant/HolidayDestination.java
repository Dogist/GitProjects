/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.holidayconsultant;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author masix
 */
public class HolidayDestination {

    private String name;
    private String country;
    private HotelCategory category;
    private List<String> sportOptionList;

    public HolidayDestination(String name, String country, HotelCategory category, List<String> sportOptionList) {
        this.name = name;
        this.country = country;
        this.category = category;
        this.sportOptionList = sportOptionList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public HotelCategory getCategory() {
        return category;
    }

    public void setCategory(HotelCategory category) {
        this.category = category;
    }

    public List<String> getSportOptionList() {
        return sportOptionList;
    }

    public void setSportOptionList(List<String> sportOptionList) {
        this.sportOptionList = sportOptionList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.country);
        hash = 59 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HolidayDestination other = (HolidayDestination) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (this.category != other.category) {
            return false;
        }
        if (!Objects.equals(this.sportOptionList, other.sportOptionList)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HolidayDestination{" + "name=" + name + ", country=" + country + ", category=" + category + ", sportOptionList=" + sportOptionList + '}';
    }

}

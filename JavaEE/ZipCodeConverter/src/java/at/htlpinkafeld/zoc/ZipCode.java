/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.zoc;

import java.util.Objects;

/**
 *
 * @author Martin Six
 */
public class ZipCode {
    private static final Character DEFAULT_COUNTRY='A';
    private Character country;
    private int number;

    public ZipCode(Character country, int number) {
        this.country = country;
        this.number = number;
    }

    public ZipCode(int number) {
        this(DEFAULT_COUNTRY,number);
    }

    public Character getCountry() {
        return country;
    }

    public void setCountry(Character country) {
        this.country = country;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
        final ZipCode other = (ZipCode) obj;
        if (this.number != other.number) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        return true;
    }   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.country);
        hash = 83 * hash + this.number;
        return hash;
    }
    
    
}

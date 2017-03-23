/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.lamda.service;

import java.util.Objects;

/**
 *
 * @author masix
 */
public class Display<T> {

    private String dispString;   //String shown in the JList
    private T source;

    public Display(String dispString, T source) {
        this.dispString = dispString;
        this.source = source;
    }

    public String getDispString() {
        return dispString;
    }

    public void setDispString(String dispString) {
        this.dispString = dispString;
    }

    public T getSource() {
        return source;
    }

    @Override
    public String toString() {
        return dispString;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.dispString);
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
        final Display<?> other = (Display<?>) obj;
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        return true;
    }

}

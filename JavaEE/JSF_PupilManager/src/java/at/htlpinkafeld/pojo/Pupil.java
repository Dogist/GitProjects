/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.pojo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Martin Six
 */
public class Pupil {
    
    private String firstName;
    private String lastName;
    private String form;
    private Date birthdate;
    private Date entryDate;
    private Boolean assigned;
    
    public Pupil(String firstName, String lastName, Boolean assigned, String form, Date birthdate, Date entryDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.form = form;
        this.birthdate = birthdate;
        this.assigned = assigned;
        this.entryDate = entryDate;
    }
    
    public Pupil(Pupil p){
        this.firstName = p.firstName;
        this.lastName = p.lastName;
        this.form = p.form;
        this.birthdate = p.birthdate;
        this.assigned = p.assigned;
        this.entryDate = p.entryDate;
    }
    
    public Pupil() {
        assigned = false;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getForm() {
        return form;
    }
    
    public void setForm(String form) {
        this.form = form;
    }
    
    public Date getBirthdate() {
        return birthdate;
    }
    
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    
    public Boolean getAssigned() {
        return assigned;
    }
    
    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }
    
    public Date getEntryDate() {
        return entryDate;
    }
    
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
    
    @Override
    public String toString() {
        return "Pupil{" + "firstName=" + firstName + ", lastName=" + lastName + ", form=" + form + ", birthdate=" + birthdate.toString() + ", entry Date=" + entryDate.toString() + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pupil) {
            Pupil p = (Pupil) obj;
            if (Objects.equals(this.assigned, p.assigned)) {
                if (this.birthdate.equals(p.birthdate)) {
                    if (this.entryDate.equals(p.entryDate)) {
                        if (this.form == null || this.form.contentEquals(p.form)) {
                            if (this.firstName.contentEquals(p.firstName)) {
                                if (this.lastName.contentEquals(p.lastName)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.firstName);
        hash = 89 * hash + Objects.hashCode(this.lastName);
        hash = 89 * hash + Objects.hashCode(this.form);
        hash = 89 * hash + Objects.hashCode(this.birthdate);
        hash = 89 * hash + Objects.hashCode(this.assigned);
        hash = 89 * hash + Objects.hashCode(this.entryDate);
        return hash;
    }
}

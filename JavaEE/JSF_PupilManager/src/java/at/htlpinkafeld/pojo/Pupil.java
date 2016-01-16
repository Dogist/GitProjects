/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.pojo;

import at.htlpinkafeld.service.PupilManagerService;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author Martin Six
 */
public class Pupil {

    private String firstName;
    private String lastName;
    private String form;
    private Date birthdate;

    public Pupil(String firstName, String lastName, String form, Date birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.form = form;
        this.birthdate = birthdate;
    }

    public Pupil() {

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

    public String getBirthdateStr() {
        if (birthdate != null) {
            return PupilManagerService.DATEFORMAT.format(birthdate);
        }
        return null;
    }

    public void setBirthdateStr(String birthdate) throws ParseException {

        this.birthdate = PupilManagerService.DATEFORMAT.parse(birthdate);
    }

    @Override
    public String toString() {
        return "Pupil{" + "firstName=" + firstName + ", lastName=" + lastName + ", form=" + form + ", birthdate=" + getBirthdateStr() + '}';
    }
}

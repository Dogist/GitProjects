/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.lamda.service;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author masix
 */
public class Student {

    private String name;
    private String university;
    private LocalDate birthday;
    private Integer registrationNumber;

    public Student(String name, String university, LocalDate birthday, Integer registrationNumber) {
        this.name = name;
        this.university = university;
        this.birthday = birthday;
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", university=" + university + ", birthday=" + birthday + ", registrationNumber=" + registrationNumber + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.university);
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.registrationNumber, other.registrationNumber)) {
            return false;
        }
        return true;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.Map_User;

import java.sql.Date;

/**
 *
 * @author Martin Six
 */
public class User implements Comparable<User>{
    private String name;
    private String email;
    private int pw;
    private int lastPW;
    private String firstname;
    private String surname;
    private Date birthday;

    public User(String name, String email, String pw, String firstname, String surname, Date birthday) {
        this.name = name;
        this.email = email;
        this.pw = pw.hashCode();
        this.firstname=firstname;
        this.surname=surname;
        this.birthday=birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPw() {
        return pw;
    }

    public void setPw(int pw) {
        this.lastPW=this.pw;
        this.pw = pw;
        
    }

    public int getLastPW() {
        return lastPW;
    }

    @Override
    public int compareTo(User o) {
        int x;
        if((x=this.surname.compareTo(o.surname))!=0)
            return x;
        return this.firstname.compareTo(o.firstname);
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", email=" + email + ", pw=" + pw + ", lastPW=" + lastPW + ", firstname=" + firstname + ", surname=" + surname + ", birthday=" + birthday + '}';
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rmi_timeservice;

import java.io.Serializable;

/**
 *
 * @author Martin Six
 */
public class MyTimeStore implements TimeStore, Serializable {

    String time;

    @Override
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getTime() {
        return this.time;
    }
}

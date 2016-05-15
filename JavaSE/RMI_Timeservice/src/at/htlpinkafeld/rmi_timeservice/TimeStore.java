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
public interface TimeStore extends Serializable {

    public void setTime(String time);

    public String getTime();
}

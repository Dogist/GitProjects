/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.quadrat;

import javax.faces.event.ActionEvent;

/**
 *
 * @author Martin Six
 */
public class SessionBean {
    private int length;
    private int area;
    private int circumference;
    /**
     * Creates a new instance of SessionBean
     */
    public SessionBean() {
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getCircumference() {
        return circumference;
    }

    public void setCircumference(int circumference) {
        this.circumference = circumference;
    }
    
    public Object calculate(){
        area=length*length;
        this.circumference=length*4;
        
        return null;
    }
}

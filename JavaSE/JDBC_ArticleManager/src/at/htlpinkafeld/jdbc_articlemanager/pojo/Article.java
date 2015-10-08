/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbc_articlemanager.pojo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class Article {
    final private int nr;
    final private String name;
    final private double price;
    final private List<PropertyChangeListener> pclL; 

    public Article(int nr, String name, double price) {
        this.nr = nr;
        this.name = name;
        this.price = price;
        pclL=new LinkedList();
    }

    public int getNr() {
        return nr;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }    

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        this.pclL.add(pcl);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener pcl){
        this.pclL.remove(pcl);
    }
    
    public void firePropertyChange(PropertyChangeEvent evt){
        for(PropertyChangeListener pcl:this.pclL)
        {
            pcl.propertyChange(evt);
        }
    }
    
    @Override
    public String toString() {
        return "Article{" + "nr=" + nr + ", name=" + name + ", price=" + price + '}';
    }
}

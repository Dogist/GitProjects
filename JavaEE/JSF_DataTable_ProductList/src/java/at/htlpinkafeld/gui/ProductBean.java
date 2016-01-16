/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.gui;

import at.htlpinkafeld.pojo.Product;

/**
 *
 * @author Martin Six
 */
public class ProductBean {

    private String header;
    private Product actProd;
    private boolean idReadOnly=false;
    
    public ProductBean() {
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Product getActProd() {
        return actProd;
    }

    public void setActProd(Product actProd) {
        this.actProd = actProd;
    }

    public boolean isIdReadOnly() {
        return idReadOnly;
    }
    
    public Object  editProd(Product pr){
        actProd=new Product(pr);
        idReadOnly=true;
        this.header="Edit Product";
         return "productface.xhtml";
    }
    
    public Object newProd() {
        actProd=new Product(0,"",0.0);
        idReadOnly=false;
        this.header="New Product";
        return "productface.xhtml";
    }
}

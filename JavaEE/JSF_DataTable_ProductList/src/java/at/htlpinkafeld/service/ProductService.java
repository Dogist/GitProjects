/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.service;

import at.htlpinkafeld.pojo.Product;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class ProductService {
    private final List<Product> prodList;

    public ProductService() {
        prodList = new LinkedList<>();
    }

    public int size() {
        return prodList.size();
    }

    public boolean addProduct(Product e) {
        return prodList.add(e);
    }

    public boolean removeProduct(Product p) {
        return prodList.remove(p);
    }

    public Product getProduct(int index) {
        return prodList.get(index);
    }

    public Product removeProduct(int index) {
        return prodList.remove(index);
    }  

    public List<Product> getProdList() {
        return prodList;
    }

    public boolean containsProd(Product p) {
        return prodList.contains(p);
    }

    public Product set(int index, Product element) {
        return prodList.set(index, element);
    }

    public int indexOf(Object o) {
        return prodList.indexOf(o);
    }
    
}

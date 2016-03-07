/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.gui;

import at.htlpinkafeld.pojo.Product;
import at.htlpinkafeld.service.ProductService;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class PricelistBean {

    private final ProductService pServ;
    private Product selProd;

    public PricelistBean() {
        pServ = new ProductService();
        pServ.addProduct(new Product(1001, "electric toothbrush", 25.20));
        pServ.addProduct(new Product(1006, "teeth whitening strips", 13.90));
        pServ.addProduct(new Product(1007, "dental floss", 2.29));
        pServ.addProduct(new Product(1221, "electric shaver", 140.90));
    }

    public List<Product> getProdList() {
        return pServ.getProdList();
    }

    public Object saveProdAction(Product p) {
        if (pServ.containsProd(p)) {
            pServ.set(pServ.indexOf(p), p);
        } else {
            pServ.addProduct(p);
        }
        return "plist.xhtml";
    }

    public Object deleteProd(Product p) {
        pServ.removeProduct(p);
        return null;
    }

    public Product getSelProd() {
        return selProd;
    }

    public void setSelProd(Product selProd) {
        this.selProd = pServ.getProduct(pServ.indexOf(selProd));
    }  

    public Object returnToPage() {
        return "plist.xhtml";
    }

}

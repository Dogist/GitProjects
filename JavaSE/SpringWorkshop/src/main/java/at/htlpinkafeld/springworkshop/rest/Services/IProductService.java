/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.springworkshop.rest.Services;

import at.htlpinkafeld.springworkshop.pojo.Product;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public interface IProductService {

    public Product createProduct(String name, String number);

    public void updateProduct(Long id, String name, String number);

    public void deleteProduct(Long id);

    public Product getProduct(Long id);

    public List<Product> getProducts();
}

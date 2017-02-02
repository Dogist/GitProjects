/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.springworkshop.rest.controller;

import at.htlpinkafeld.springworkshop.pojo.Product;
import at.htlpinkafeld.springworkshop.rest.Services.IProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martin Six
 */
@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    ObjectMapper mapper;

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    public Product createProduct(@RequestParam(value = "name", defaultValue = "Toilet") String name,
            @RequestParam(value = "number", defaultValue = "123213") String number) throws JsonProcessingException {
        return productService.createProduct(name, number);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.POST)
    public void updateProduct(@PathVariable Long id,
            @RequestParam(value = "name", defaultValue = "Toilet") String name,
            @RequestParam(value = "number", defaultValue = "123213") String number) {
        productService.updateProduct(id, name, number);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public void updateProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable Long id) throws JsonProcessingException {
        return productService.getProduct(id);
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public List<Product> getAllProducts() throws JsonProcessingException {
        return productService.getProducts();
    }
}

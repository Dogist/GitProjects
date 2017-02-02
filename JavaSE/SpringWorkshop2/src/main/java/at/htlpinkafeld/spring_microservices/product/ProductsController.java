/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.spring_microservices.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
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
@RequestMapping(value = "products")
public class ProductsController {

    private Logger logger = Logger.getLogger(ProductsController.class.getName());

    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.PUT)
    public Product createProduct(@RequestParam(value = "name", defaultValue = "Toilet") String name,
            @RequestParam(value = "number", defaultValue = "123213") String number) throws JsonProcessingException {
        return productService.createProduct(name, number);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void updateProduct(@PathVariable Long id,
            @RequestParam(value = "name", defaultValue = "Toilet") String name,
            @RequestParam(value = "number", defaultValue = "123213") String number) {
        productService.updateProduct(id, name, number);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void updateProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable Long id) throws JsonProcessingException {
        return productService.getProduct(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Product> getAllProducts() throws JsonProcessingException {
        return productService.getProducts();
    }

}

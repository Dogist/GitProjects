/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.spring_microservices.product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Six
 */
@Service
public class ProductService {

    private Map<Long, Product> products;
    private final AtomicLong counter;

    public ProductService() {
        products = new HashMap<>();
        counter = new AtomicLong();
    }

    Product createProduct(String name, String number) {
        Long id = counter.incrementAndGet();
        Product p = new Product(id, name, number);
        products.put(id, p);
        return p;
    }

    void updateProduct(Long id, String name, String number) {
        Product p = new Product(id, name, number);
        products.replace(id, p);
    }

    void deleteProduct(Long id) {
        products.remove(id);
    }

    Product getProduct(Long id) {
        return products.get(id);
    }

    Collection<Product> getProducts() {
        return products.values();
    }

}

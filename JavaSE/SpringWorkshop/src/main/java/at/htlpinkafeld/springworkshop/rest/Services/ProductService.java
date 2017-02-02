/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.springworkshop.rest.Services;

import at.htlpinkafeld.springworkshop.pojo.Product;
import at.htlpinkafeld.springworkshop.service.ImbaService;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Six
 */
@Service
public class ProductService implements IProductService {

    private final AtomicLong counter = new AtomicLong();

    @Override
    public Product createProduct(String name, String number) {
        return ImbaService.createProduct(new Product(counter.incrementAndGet(), name, number));
    }

    @Override
    public void updateProduct(Long id, String name, String number) {
        ImbaService.updateProduct(new Product(id, name, number));
    }

    @Override
    public void deleteProduct(Long id) {
        ImbaService.deleteProduct(id);
    }

    @Override
    public Product getProduct(Long id) {
        return ImbaService.readProduct(id);
    }

    @Override
    public List<Product> getProducts() {
        return ImbaService.readProducts();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.spring_microservices.order;

import at.htlpinkafeld.spring_microservices.account.Account;
import at.htlpinkafeld.spring_microservices.product.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Martin Six
 */
@RestController
@RequestMapping(value = "orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate;

    public OrderController() {
        restTemplate = new RestTemplate();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Order createOrder(@RequestParam(value = "number", defaultValue = "Hello") String name,
            @RequestParam(value = "owner", defaultValue = "1232144") String number,
            @RequestParam(value = "balance", defaultValue = "1000") BigDecimal price,
            @RequestParam(value = "productId", defaultValue = "1") Long productId,
            @RequestParam(value = "accountId", defaultValue = "1") Long accountId) throws JsonProcessingException {

        URI uri = discoveryClient.getInstances("accounts-service").get(0).getUri();
        Account a = restTemplate.getForObject("http://localhost:" + uri.getPort() + "/accounts/" + accountId, Account.class);

        uri = discoveryClient.getInstances("products-service").get(0).getUri();
        Product p = restTemplate.getForObject("http://localhost:" + uri.getPort() + "/products/" + productId, Product.class);

        if (a == null || p == null) {
            throw new Error("no Account or Product with this ID");
        }

        return orderService.createOrder(name, number, price, p, a);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void updateOrder(@PathVariable Long id,
            @RequestParam(value = "number", defaultValue = "Hello") String name,
            @RequestParam(value = "owner", defaultValue = "1232144") String number,
            @RequestParam(value = "balance", defaultValue = "1000") BigDecimal price,
            @RequestParam(value = "productId", defaultValue = "1") Long productId,
            @RequestParam(value = "accountId", defaultValue = "1") Long accountId) throws JsonProcessingException {

        URI uri = discoveryClient.getInstances("accounts-service").get(0).getUri();
        Account a = restTemplate.getForObject("http://localhost:" + uri.getPort() + "/accounts/" + accountId, Account.class);

        uri = discoveryClient.getInstances("products-service").get(0).getUri();
        Product p = restTemplate.getForObject("http://localhost:" + uri.getPort() + "/products/" + productId, Product.class);

        if (a == null || p == null) {
            throw new Error("no Account or Product with this ID");
        }

        orderService.updateOrder(id, name, number, price, p, a);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable Long id) throws JsonProcessingException {
        return orderService.getOrder(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Collection<Order> getAllOrders() throws JsonProcessingException {
        return orderService.getOrders();
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Collection<Order> getOrdersForCustomer(@PathVariable Long accountId) throws JsonProcessingException {

        URI uri = discoveryClient.getInstances("accounts-service").get(0).getUri();
        Account a = restTemplate.getForObject("http://localhost:" + uri.getPort() + "/accounts/" + accountId, Account.class);

        if (a == null) {
            throw new Error("no Account with this ID");
        }

        return orderService.getOrdersForAccount(a);
    }
}

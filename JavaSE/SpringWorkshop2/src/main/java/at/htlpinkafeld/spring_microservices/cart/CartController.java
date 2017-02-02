/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.spring_microservices.cart;

import at.htlpinkafeld.spring_microservices.account.Account;
import at.htlpinkafeld.spring_microservices.order.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
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
@RequestMapping(value = "carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate;

    public CartController() {
        restTemplate = new RestTemplate();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Cart createCart(@RequestParam(value = "accountId", defaultValue = "1") Long accountId)
            throws JsonProcessingException {

        URI uri = discoveryClient.getInstances("accounts-service").get(0).getUri();
        Account a = restTemplate.getForObject("http://localhost:" + uri.getPort() + "/accounts/" + accountId, Account.class);

        if (a == null) {
            throw new Error("no Account with ID: " + accountId);
        }

        return cartService.createCart(a);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void updateCart(@PathVariable Long id,
            @RequestParam(value = "accountId", defaultValue = "1") Long accountId) throws JsonProcessingException {

        URI uri = discoveryClient.getInstances("accounts-service").get(0).getUri();
        Account a = restTemplate.getForObject("http://localhost:" + uri.getPort() + "/accounts/" + accountId, Account.class);

        if (a == null) {
            throw new Error("no Account with ID: " + accountId);
        }

        cartService.updateCart(id, a);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Cart getCart(@PathVariable Long id) throws JsonProcessingException {
        return cartService.getCart(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Collection<Cart> getAllCart() throws JsonProcessingException {
        return cartService.getCarts();
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Collection<Cart> getCartsForAccount(@PathVariable Long accountId) throws JsonProcessingException {

        URI uri = discoveryClient.getInstances("accounts-service").get(0).getUri();
        Account a = restTemplate.getForObject("http://localhost:" + uri.getPort() + "/accounts/" + accountId, Account.class);

        if (a == null) {
            throw new Error("no Account with ID: " + accountId);
        }
        return cartService.getCartsForAccount(a);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Cart orderCart(@PathVariable Long id)
            throws JsonProcessingException {

        return cartService.orderCart(id);
    }

    @RequestMapping(value = "/cart/{id}", method = RequestMethod.PUT)
    public void addOrder(@PathVariable Long id,
            @RequestParam(value = "orderId", defaultValue = "1") Long orderId)
            throws JsonProcessingException {

        URI uri = discoveryClient.getInstances("orders-service").get(0).getUri();
        Order o = restTemplate.getForObject("http://localhost:" + uri.getPort() + "/orders/" + orderId, Order.class);

        if (o == null) {
            throw new Error("no Order with ID: " + orderId);
        }

        cartService.addOrder(id, o);
    }

    @RequestMapping(value = "/cart/{id}", method = RequestMethod.DELETE)
    public void removeOrder(@PathVariable Long id,
            @RequestParam(value = "orderId", defaultValue = "1") Long orderId)
            throws JsonProcessingException {

        URI uri = discoveryClient.getInstances("orders-service").get(0).getUri();
        Order o = restTemplate.getForObject("http://localhost:" + uri.getPort() + "/orders/" + orderId, Order.class);

        if (o == null) {
            throw new Error("no Order with ID: " + orderId);
        }

        cartService.removeOrder(id, o);
    }

    @RequestMapping(value = "/cart/{id}", method = RequestMethod.GET)
    public Collection<Order> getOrders(@PathVariable Long id)
            throws JsonProcessingException {

        return cartService.getOrders(id);
    }

}

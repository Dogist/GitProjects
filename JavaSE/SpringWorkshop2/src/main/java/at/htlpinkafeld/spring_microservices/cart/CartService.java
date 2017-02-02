/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.spring_microservices.cart;

import at.htlpinkafeld.spring_microservices.order.*;
import at.htlpinkafeld.spring_microservices.account.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Martin Six
 */
@Service
public class CartService {

    private final Map<Long, Cart> carts;
    private final AtomicLong counter;

    @Autowired
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate;

    public CartService() {
        carts = new HashMap<>();
        counter = new AtomicLong();
        restTemplate = new RestTemplate();
    }

    public Cart createCart(Account a) throws JsonProcessingException {
        Long id = counter.incrementAndGet();

        Cart c = new Cart(id, a);
        carts.put(id, c);
        return c;
    }

    public void updateCart(Long id, Account a) throws JsonProcessingException {
        Cart o = new Cart(id, a);
        if (carts.replace(id, o) == null) {
            throw new Error("no Cart with this ID");
        }
    }

    public void deleteCart(Long id) {
        carts.remove(id);
    }

    public Cart getCart(Long id) {
        return carts.get(id);
    }

    public Collection<Cart> getCarts() {
        return carts.values();
    }

    public Collection<Cart> getCartsForAccount(Account a) {
        List<Cart> retList = new LinkedList<>();
        for (Cart o : carts.values()) {
            if (o.getAccount().equals(a)) {
                retList.add(o);
            }
        }
        return retList;
    }

    public Cart orderCart(Long cartId) {
        Cart cart = carts.get(cartId);
        if (cart == null) {
            throw new Error("No Cart with this Id");
        }
        if (!(cart.getAccount().getBalance().compareTo(cart.getTotalPrice()) >= 0)) {
            throw new Error("Not enough Money on this Account");
        }
        cart.getAccount().setBalance(cart.getAccount().getBalance().subtract(cart.getTotalPrice()));

        URI uri = discoveryClient.getInstances("accounts-service").get(0).getUri();
        String url = "http://localhost:" + uri.getPort() + "/accounts/" + cart.getAccount().getId() + "?number=" + cart.getAccount().getNumber() + "&owner=" + cart.getAccount().getOwner() + "&balance=" + cart.getAccount().getBalance();
        restTemplate.postForObject(url, null, String.class);

        cart.setOrderedTimeStamp(LocalDateTime.now());
        cart.setOrdered(true);

        return cart;
    }

    public void addOrder(Long cartId, Order o) {
        Cart cart = carts.get(cartId);
        if (cart == null) {
            throw new Error("No Cart with this Id");
        }

        cart.getOrders().add(o);
        cart.setTotalPrice(cart.getTotalPrice().add(o.getPrice()));
    }

    public void removeOrder(Long cartId, Order o) {
        Cart cart = carts.get(cartId);
        if (cart == null) {
            throw new Error("No Cart with this Id");
        }

        cart.getOrders().remove(o);
        cart.setTotalPrice(cart.getTotalPrice().subtract(o.getPrice()));
    }

    public Collection<Order> getOrders(Long cartId) {
        Cart cart = carts.get(cartId);
        if (cart == null) {
            throw new Error("No Cart with this Id");
        }

        return cart.getOrders();
    }

}

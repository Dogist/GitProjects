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
public class OrderService {

    private final Map<Long, Order> orders;
    private final AtomicLong counter;

    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    public OrderService() {
        orders = new HashMap<>();
        counter = new AtomicLong();
        restTemplate = new RestTemplate();
    }

    Order createOrder(String name, String number, BigDecimal price, Product p, Account a) throws JsonProcessingException {
        Long id = counter.incrementAndGet();

        Order o = new Order(id, name, number, price, p, a);
        orders.put(id, o);
        return o;
    }

    void updateOrder(Long id, String name, String number, BigDecimal price, Product p, Account a) throws JsonProcessingException {

        Order o = new Order(id, name, number, price, p, a);
        if (orders.replace(id, o) == null) {
            throw new Error("no Order with this ID");
        }
    }

    void deleteOrder(Long id) {
        orders.remove(id);
    }

    Order getOrder(Long id) {
        return orders.get(id);
    }

    Collection<Order> getOrders() {
        return orders.values();
    }

    Collection<Order> getOrdersForAccount(Account a) {
        List<Order> retList = new LinkedList<>();
        for (Order o : orders.values()) {
            if (o.getAccount().equals(a)) {
                retList.add(o);
            }
        }
        return retList;
    }

}

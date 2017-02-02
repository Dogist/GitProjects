/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.springworkshop.rest.Services;

import at.htlpinkafeld.springworkshop.pojo.Account;
import at.htlpinkafeld.springworkshop.pojo.Order;
import at.htlpinkafeld.springworkshop.pojo.Product;
import at.htlpinkafeld.springworkshop.service.ImbaService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Six
 */
@Service
public class OrderService implements IOrderService {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    IProductService productService;
    @Autowired
    IAccountService accountService;

    @Override
    public Order createOrder(String name, String number, BigDecimal price, Long pId, Long aId) {
        return ImbaService.createOrder(new Order(counter.incrementAndGet(), name, number, price, productService.getProduct(pId), accountService.getAccount(aId)));
    }

    @Override
    public void updateOrder(Long id, String name, String number, BigDecimal price, Long pId, Long aId) {
        ImbaService.updateOrder(new Order(id, name, number, price, productService.getProduct(pId), accountService.getAccount(aId)));
    }

    @Override
    public void deleteOrder(Long id) {
        ImbaService.deleteOrder(id);
    }

    @Override
    public Order getOrder(Long id) {
        return ImbaService.readOrder(id);
    }

    @Override
    public List<Order> getOrders() {
        return ImbaService.readOrders();
    }

    @Override
    public List<Order> getOrderByAccount(Long aId) {
        List<Order> orders = ImbaService.readOrders();
        List<Order> retOrders = new ArrayList<>();
        for (Order o : orders) {
            if (o.getAccount().getId().equals(aId)) {
                retOrders.add(o);
            }
        }
        return retOrders;
    }
}

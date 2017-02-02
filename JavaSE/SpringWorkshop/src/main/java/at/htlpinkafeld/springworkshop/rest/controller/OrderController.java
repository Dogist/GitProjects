/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.springworkshop.rest.controller;

import at.htlpinkafeld.springworkshop.pojo.Order;
import at.htlpinkafeld.springworkshop.rest.Services.IOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
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
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private AccountController accountController;
    @Autowired
    private ProductController productController;

    @Autowired
    ObjectMapper mapper;

    @RequestMapping(value = "/order", method = RequestMethod.PUT)
    public Order createOrder(@RequestParam(value = "number", defaultValue = "Hello") String name,
            @RequestParam(value = "owner", defaultValue = "1232144") String number,
            @RequestParam(value = "balance", defaultValue = "1000") BigDecimal price,
            @RequestParam(value = "productId", defaultValue = "1") Long productId,
            @RequestParam(value = "articleId", defaultValue = "1") Long articleId) throws JsonProcessingException {
        return orderService.createOrder(name, number, price, productId, articleId);

    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.POST)
    public void updateOrder(@PathVariable Long id,
            @RequestParam(value = "number", defaultValue = "Hello") String name,
            @RequestParam(value = "owner", defaultValue = "1232144") String number,
            @RequestParam(value = "balance", defaultValue = "1000") BigDecimal price,
            @RequestParam(value = "productId", defaultValue = "1") Long productId,
            @RequestParam(value = "articleId", defaultValue = "1") Long articleId) {
        orderService.updateOrder(id, name, number, price, productId, articleId);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable Long id) throws JsonProcessingException {
        return orderService.getOrder(id);
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public List<Order> getAllOrders() throws JsonProcessingException {
        return orderService.getOrders();
    }

    @RequestMapping(value = "/orderCustomer/{id}", method = RequestMethod.GET)
    public List<Order> getOrders() throws JsonProcessingException {
        return orderService.getOrders();
    }
}

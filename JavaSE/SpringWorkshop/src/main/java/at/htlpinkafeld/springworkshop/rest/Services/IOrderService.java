/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.springworkshop.rest.Services;

import at.htlpinkafeld.springworkshop.pojo.Order;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public interface IOrderService {

    public Order createOrder(String name, String number, BigDecimal price, Long pId, Long aId);

    public void updateOrder(Long id, String name, String number, BigDecimal price, Long pId, Long aId);

    public void deleteOrder(Long id);

    public Order getOrder(Long id);

    public List<Order> getOrders();

    public List<Order> getOrderByAccount(Long aId);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.springworkshop.service;

import at.htlpinkafeld.springworkshop.pojo.Order;
import at.htlpinkafeld.springworkshop.pojo.Account;
import at.htlpinkafeld.springworkshop.pojo.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Martin Six
 */
public class ImbaService {

    private static final Map<Long, Account> accounts;
    private static final Map<Long, Product> products;
    private static final Map<Long, Order> orders;

    static {
        accounts = Collections.synchronizedMap(new HashMap<Long, Account>());
        products = Collections.synchronizedMap(new HashMap<Long, Product>());
        orders = Collections.synchronizedMap(new HashMap<Long, Order>());
    }

    //Account Funktions
    public static Account createAccount(Account a) {
        accounts.putIfAbsent(a.getId(), a);
        return a;
    }

    public static void deleteAccount(Long id) {
        accounts.remove(id);
    }

    public static void updateAccount(Account a) {
        accounts.replace(a.getId(), a);
    }

    public static Account readAccount(Long id) {
        return accounts.get(id);
    }

    public static List<Account> readAccounts() {
        return new ArrayList<>(accounts.values());
    }

    //Product Funktions
    public static Product createProduct(Product p) {
        products.putIfAbsent(p.getId(), p);
        return p;
    }

    public static void deleteProduct(Long id) {
        products.remove(id);
    }

    public static void updateProduct(Product p) {
        products.replace(p.getId(), p);

    }

    public static Product readProduct(Long id) {
        return products.get(id);
    }

    public static List<Product> readProducts() {
        return new ArrayList<>(products.values());
    }

    //Order-Funktions
    public static Order createOrder(Order o) {
        orders.putIfAbsent(o.getId(), o);
        return o;
    }

    public static void deleteOrder(Long id) {
        orders.remove(id);
    }

    public static void updateOrder(Order o) {
        orders.replace(o.getId(), o);
    }

    public static Order readOrder(Long id) {
        return orders.get(id);
    }

    public static List<Order> readOrders() {
        return new ArrayList<>(orders.values());
    }

}

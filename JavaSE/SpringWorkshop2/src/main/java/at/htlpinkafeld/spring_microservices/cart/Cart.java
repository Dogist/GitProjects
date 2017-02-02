/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.spring_microservices.cart;

import at.htlpinkafeld.spring_microservices.account.Account;
import at.htlpinkafeld.spring_microservices.order.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Martin Six
 */
@XmlRootElement
public class Cart {

    private Long id;
    private Account account;
    private List<Order> orders;
    private BigDecimal totalPrice;

    private LocalDateTime orderedTimeStamp;
    private boolean ordered;

    public Cart() {
    }

    public Cart(Long id) {
        this.id = id;
    }

    public Cart(Long id, Account account, List<Order> orders, BigDecimal totalPrice, LocalDateTime orderedTimeStamp) {
        this(account, orders, totalPrice, orderedTimeStamp);
        this.id = id;
    }

    public Cart(Account account, List<Order> orders, BigDecimal totalPrice, LocalDateTime orderedTimeStamp) {
        this.account = account;
        this.orders = orders;
        this.totalPrice = totalPrice;
        this.orderedTimeStamp = orderedTimeStamp;
        this.ordered = true;
    }

    public Cart(Long id, Account account) {
        this(id);
        this.account = account;
        this.orders = new LinkedList<>();
        this.totalPrice = new BigDecimal(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOrderedTimeStamp() {
        return orderedTimeStamp;
    }

    public void setOrderedTimeStamp(LocalDateTime orderedTimeStamp) {
        this.orderedTimeStamp = orderedTimeStamp;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.account);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cart other = (Cart) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", account=" + account + ", orders=" + orders + ", totalPrice=" + totalPrice + ", orderedTimeStamp=" + orderedTimeStamp + ", ordered=" + ordered + '}';
    }

}

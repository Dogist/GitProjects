/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.springworkshop.pojo;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Martin Six
 */
public class Order {

    private Long id;
    private String name;
    private String number;
    private BigDecimal price;
    private Product product;
    private Account account;

    public Order() {
    }

    public Order(Long id) {
        this.id = id;
    }

    public Order(String name, String number, BigDecimal price, Product p, Account a) {
        this.name = name;
        this.number = number;
        this.price = price;
    }

    public Order(Long id, String name, String number, BigDecimal price, Product p, Account a) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.price = price;
        this.product=p;
        this.account=a;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", name=" + name + ", number=" + number + ", price=" + price + ", p=" + product + ", a=" + account + '}';
    }

}

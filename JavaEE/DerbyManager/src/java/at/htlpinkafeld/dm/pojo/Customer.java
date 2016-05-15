/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.dm.pojo;

/**
 *
 * @author Martin Six
 */
public class Customer {

    private Integer customer_id;
    private Character discount_code;
    private Integer zip;
    private String name;
    private String addressline1;
    private String addressline2;
    private String city;
    private String state;
    private String phone;
    private String fax;
    private String email;
    private Integer credit_limit;

    public Customer(Integer customer_id, Character discount_code, Integer zip, String name, String addressline1, String addressline2, String city, String state, String phone, String fax, String email, Integer credit_limit) {
        this.customer_id = customer_id;
        this.discount_code = discount_code;
        this.zip = zip;
        this.name = name;
        this.addressline1 = addressline1;
        this.addressline2 = addressline2;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.credit_limit = credit_limit;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Character getDiscount_code() {
        return discount_code;
    }

    public void setDiscount_code(Character discount_code) {
        this.discount_code = discount_code;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCredit_limit() {
        return credit_limit;
    }

    public void setCredit_limit(Integer credit_limit) {
        this.credit_limit = credit_limit;
    }
    
}

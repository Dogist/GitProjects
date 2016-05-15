/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_customermanagement_json.dao;

import at.htlpinkafeld.rs_customermanagement_json.model.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class CustomerDao {

    List<Customer> customers;

    public CustomerDao() {
        customers = new ArrayList<>();
        customers.add(new Customer(1, "Tom", "Teacher"));
    }

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public Customer getCustomer(int id) {
        for (Customer c : customers) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

}

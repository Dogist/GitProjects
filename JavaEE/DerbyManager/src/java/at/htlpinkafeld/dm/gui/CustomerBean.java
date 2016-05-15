/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.dm.gui;

import at.htlpinkafeld.dm.dao.CustomerDAO;
import at.htlpinkafeld.dm.pojo.Customer;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class CustomerBean {

    private List<Customer> custList;
    private String loadRes;
    private Integer customer_id;

    public CustomerBean() {
        CustomerDAO custDAO = new CustomerDAO();
        custList = custDAO.getEntityList();
        customer_id = 0;
    }

    public List<Customer> getCustList() {
        return custList;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getLoadRes() {
        return loadRes;
    }

    public Object loadAction() {
        CustomerDAO custDAO = new CustomerDAO();
        Customer c = custDAO.findByID(customer_id);
        if (c == null) {
            loadRes = "Customer not found!";
        } else {
            loadRes = c.getName() + " in " + c.getCity();
        }
        return null;
    }
}

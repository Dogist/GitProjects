/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_customermanagement_json.service;

import at.htlpinkafeld.rs_customermanagement_json.dao.CustomerDao;
import at.htlpinkafeld.rs_customermanagement_json.model.Customer;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author Martin Six
 */
@Path("customerservice")
public class CustomerService {

    private final CustomerDao cDao;

    public CustomerService() {
        cDao = new CustomerDao();
    }

    @Path("customers")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Customer> getCustomers() {
        return cDao.getCustomers();
    }

    @Path("customers/{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Customer getCustomer(@PathParam("id") int id) {
        return cDao.getCustomer(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Customer createCustomer(@FormParam("id") int id, @FormParam("name") String name, @FormParam("profession") String profession) {
        Customer c = new Customer();
        c.setId(id);
        c.setName(name);
        c.setProfession(profession);
        cDao.addCustomer(c);
        return c;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Customer createCustomer(MultivaluedMap<String, String> formParams) {
        Customer c = new Customer();
        c.setId(Integer.parseInt(formParams.getFirst("id")));
        c.setName(formParams.getFirst("name"));
        c.setProfession(formParams.getFirst("profession"));
        cDao.addCustomer(c);
        return c;
    }
}

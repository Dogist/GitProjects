/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_customermanagement_json.client;

import at.htlpinkafeld.rs_customermanagement_json.model.Customer;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;

/**
 *
 * @author Martin Six
 */
public class RSClient {

    private WebTarget webTarget;
    private Client client;

    public RSClient(int port) {
        client = ClientBuilder.newClient();
        webTarget = client.target("http://localhost:" + port + "/rs_customermanagement_json/webapi/customerservice");
    }

    public RSClient() {
        this(8084);
    }

    public static void main(String[] args) {
        RSClient cl = new RSClient();

        System.out.println(cl.createCustomer(5, "Max", "Painter"));

        MultivaluedMap mm = new MultivaluedStringMap();
        mm.add("id", 6);
        mm.add("name", "Franz");
        mm.add("profession", "Engineer");
        System.out.println(cl.createCustomer(mm));

        System.out.println(cl.getCustomer(1));

        for (Customer c : cl.getCustomers()) {
            System.out.println(c);
        }
    }

    public List<Customer> getCustomers() {
        return webTarget.path("customers").request(MediaType.APPLICATION_XML).get().readEntity(new GenericType<List<Customer>>() {
        });
    }

    public Customer createCustomer(int id, String name, String profession) {
        Form f = new Form();
        f.param("id", Integer.toString(id));
        f.param("name", name);
        f.param("profession", profession);
        return webTarget.request(MediaType.APPLICATION_XML).post(Entity.entity(f, MediaType.APPLICATION_FORM_URLENCODED)).readEntity(Customer.class);
    }

    public Customer createCustomer(MultivaluedMap<String, String> mm) {
        Form f = new Form(mm);
        return webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(f, MediaType.APPLICATION_FORM_URLENCODED)).readEntity(Customer.class);
    }

    public Customer getCustomer(int id) {
        return webTarget.path("customers/" + id).request(MediaType.APPLICATION_XML).get().readEntity(Customer.class);
    }
}

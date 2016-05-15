/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_personresource.rsclient;

import at.htlpinkafeld.rs_personresource.model.Person;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Martin Six
 */
public class RSClient {

    private WebTarget target;
    private Client client;

    public RSClient(int port) {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:" + port).path("/rs_personresource");
    }

    public RSClient() {
        this(8084);
    }

    public static void main(String[] args) {
        RSClient cl = new RSClient();
        System.out.println(cl.respondAsReady());
        System.out.println(cl.getPerson());
        System.out.println(cl.getPersons());
        System.out.println(cl.postPerson("Martin", "Six", "noplan@nix.com"));
    }

    public String respondAsReady() {
        return target.path("webapi/personservice").request(MediaType.TEXT_PLAIN).get().readEntity(String.class);
    }

    public Person getPerson() {
        return target.path("webapi/personservice/persons/0").request(MediaType.APPLICATION_XML).get().readEntity(Person.class);
    }

    public List<Person> getPersons() {
        return target.path("webapi/personservice/persons").request(MediaType.APPLICATION_XML).get().readEntity(new GenericType<List<Person>>() {
        });
    }

    public Person postPerson(String firstName, String lastName, String email) {
        Form f = new Form();
        f.param("firstName", firstName);
        f.param("lastName", lastName);
        f.param("email", email);

        return target.path("webapi/personservice").request(MediaType.APPLICATION_XML).
                post(Entity.entity(f, MediaType.APPLICATION_FORM_URLENCODED_TYPE)).readEntity(Person.class);
    }

}

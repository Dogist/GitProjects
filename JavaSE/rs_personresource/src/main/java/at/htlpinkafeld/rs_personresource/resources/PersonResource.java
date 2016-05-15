/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_personresource.resources;

import at.htlpinkafeld.rs_personresource.model.Person;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author Martin Six
 */
@Path("person")
public class PersonResource {

    private Person p;

    public PersonResource() {
        this.p = new Person(1, "Max", "Mustermann", "max_mustermann@htlpinkafeld.at");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String respondAsReady() {
        return "Person service is ready!";
    }

    @Path("sample")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Person getSamplePerson() {
        return p;
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Person postPerson(@FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email) {

        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setEmail(email);

        return p;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Person postPersonJSON(MultivaluedMap<String, String> formParams) {

        p.setFirstName(formParams.getFirst("firstName"));
        p.setLastName(formParams.getFirst("lastName"));
        p.setEmail(formParams.getFirst("email"));

        return p;
    }
}

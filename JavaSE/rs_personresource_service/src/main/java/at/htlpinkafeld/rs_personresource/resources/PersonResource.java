/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_personresource.resources;

import at.htlpinkafeld.connectionmanager.ConnectionManagerImpl;
import at.htlpinkafeld.connectionmanager.DAOHelper;
import at.htlpinkafeld.rs_personresource.dao.JdbcBaseDao;
import at.htlpinkafeld.rs_personresource.model.Person;
import at.htlpinkafeld.rs_personresource.service.DAOSysException;
import at.htlpinkafeld.rs_personresource.service.PersonService;
import at.htlpinkafeld.rs_personresource.service.PersonServiceImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Martin Six
 */
@Path("personservice")
public class PersonResource {

    private PersonService ps;

    public PersonResource() {
        //this.p = new Person(1, "Max", "Mustermann", "max_mustermann@htlpinkafeld.at");
        
        ps = new PersonServiceImpl();

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String respondAsReady() {
        return "Person service is ready!";
    }

    @Path("persons/{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Person getPerson(@PathParam("id") int id) {
        try {
            return ps.findPersonById(id);
        } catch (DAOSysException ex) {
            Logger.getLogger(PersonResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Path("persons")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getPersons() {
        try {
            return ps.getAllPersons();
        } catch (DAOSysException ex) {
            Logger.getLogger(PersonResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Person postPerson(@FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email) {

        try {
            Person p = new Person(-1, firstName, lastName, email);
            ps.insertPerson(p);
            return p;
        } catch (DAOSysException ex) {
            Logger.getLogger(PersonResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

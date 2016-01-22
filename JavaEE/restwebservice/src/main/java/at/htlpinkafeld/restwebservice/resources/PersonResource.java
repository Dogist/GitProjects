package at.htlpinkafeld.restwebservice.resources;

import at.htlpinkafeld.restwebservice.Person;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/person")
public class PersonResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    Person p = new Person(1, "Hans", "Wunderler", "dawd@dwda.com");

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("respondAsReady")
    @Produces(MediaType.TEXT_PLAIN)
    public String respondAsReady() {

        return "Person service is ready!";
    }
    
    @Path("sample")
    @GET
    @Produces(MediaType.TEXT_XML)
    public Person getSamplePerson() {

        return p;
    }
}

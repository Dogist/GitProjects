package at.htlpinkafeld.simplejaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("fruit")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_XML)
    public String get() {
        return Database.getAll();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.TEXT_XML)
    public String get(@PathParam("name") String s) {
        return Database.get(s);
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void post(String s) {
        Database.add(s);
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void put(String s) {
        Database.add(s);
    }

    @DELETE
    @Path("{name}")
    public void delete(@PathParam("name") String s) {
        Database.delete(s);
    }
}

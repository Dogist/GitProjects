package at.htlpinkafeld.celsiusfahrenheitrest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class CelsiusFahrenheitCalc {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @param grad
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("ctofservice/{celsius}")
    public String getFahrenheitFromCelsius(@PathParam("celsius") String grad) {
        return String.valueOf(Double.valueOf(grad)*1.8+32);
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("ftocservice/{fahrenheit}")
    public String getCelsiusFromFahrenheit(@PathParam("fahrenheit") String grad) {
        return String.valueOf((Double.valueOf(grad)-32)/1.8);
    }
}

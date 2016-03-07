package at.htlpinkafeld.celsiusfahrenheitrest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CelsiusFahrenheitCalcTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testGetFahrenheitFromCelsius() {
        System.out.println("getFahrenheitFromCelsius");
        String grad = "35";
        String expResult = "95";
        String result = target.path("ctofservice").path(grad).request().accept(MediaType.TEXT_PLAIN).get(String.class);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCelsiusFromFahrenheit() {
        System.out.println("getCelsiusFromFahrenheit");
        String grad = "95";
        String  expResult = "35";
        String result = target.path("ftocservice").path(grad).request().accept(MediaType.TEXT_PLAIN).get(String.class);
        assertEquals(expResult, result);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_webserviceee;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin Six
 */
public class MyResourceTest {
    
    private WebResource resource;
    Client client;
    URI BASE_URI;
    final static int PORT = 8084;
    
    public MyResourceTest() {
    }
    
    
    @Before
    public void setUp() {
        client=Client.create();
        BASE_URI=UriBuilder.fromUri("http://localhost/RS_WebserviceEE/webapi").port(PORT).build();
        resource =client.resource(BASE_URI.toString());
    }
    
    @After
    public void tearDown() {
    }

    @org.junit.Test
    public void testGetIt() {
        String responseMesg = resource.path("myresource").get(String.class);
        assertEquals(responseMesg, "Got it!");
        
    }
    
}

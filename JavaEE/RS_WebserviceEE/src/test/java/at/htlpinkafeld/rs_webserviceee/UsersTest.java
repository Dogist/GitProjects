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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author Martin Six
 */
public class UsersTest {
    
    private WebResource resource;
    Client client;
    URI BASE_URI;
    final static int PORT = 8084;
    
    public UsersTest() {
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
    public void testGetUser() {
        String responseMesg = resource.path("users/Hans").get(String.class);
        assertEquals(responseMesg, "Got user: Hans");
    }
    
}

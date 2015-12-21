/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_webserviceee;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Martin Six
 */
@Path("users/{username}")
public class Users{

 

@GET
@Produces("text/plain")
public String getUser(@PathParam("username") String  username){
      return "Got user: "+username;
}
}

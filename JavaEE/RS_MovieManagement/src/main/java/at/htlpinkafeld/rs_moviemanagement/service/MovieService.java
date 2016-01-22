/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_moviemanagement.service;

import at.htlpinkafeld.rs_moviemanagement.dao.MovieDao;
import at.htlpinkafeld.rs_moviemanagement.model.Movie;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Martin Six
 */
@Path("movieservice")
public class MovieService {
    
    @Path("movies")
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Movie> getMovies(){
        return MovieDao.getInst().getMovies();
    }
    
    @Path("movies/{title}")
    @GET
    @Produces(MediaType.TEXT_XML)
    public Movie getMovie(@PathParam("title") String title){
        return MovieDao.getInst().getMovie(title);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public void createMovie(@FormParam("director") String director, @FormParam("title") String title, @FormParam("year") String year, @Context HttpServletResponse servletResponse) throws IOException{
        MovieDao.getInst().addMovie(new Movie(director, title, Integer.parseInt(year)));
        
        servletResponse.sendRedirect("../");
    }
}

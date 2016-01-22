/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_moviemanagement.dao;

import at.htlpinkafeld.rs_moviemanagement.model.Movie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Six
 */

public class MovieDao {
    private static final MovieDao inst= new MovieDao();
    private final List<Movie> movies;

    public MovieDao() {
        this.movies = new ArrayList<>();
        movies.add(new Movie("noPlan","Titanic",2000));
        movies.add(new Movie("Nay", "Avatar", 2012));
    }
    
    public static MovieDao getInst(){
        return inst;
    }

    public boolean addMovie(Movie e) {
        return movies.add(e);
    }

    public Movie getMovie(String title) {
        for(Movie m:movies)
            if(m.getTitle().contentEquals(title))
                return m;
        return null;
    }

    public List<Movie> getMovies() {
        return movies;
    }
    
    
}

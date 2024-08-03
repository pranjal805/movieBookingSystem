package com.mangoapps.booking.controllers;

import com.mangoapps.booking.models.Movie;
import com.mangoapps.booking.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    public String addMovies(@RequestBody List<Movie> movies) {
        return movieService.addMovies(movies);
    }
    @GetMapping("/getAllMoviesWithShowsTimingAndSeats")
    public List<Map<String,Object>> getAllMoviesWithShowsTimingAndSeats(){
        return  movieService.getAllMoviesWithShowsTimingAndSeats();
    }

}

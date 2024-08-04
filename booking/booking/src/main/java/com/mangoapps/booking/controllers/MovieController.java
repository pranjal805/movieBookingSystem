package com.mangoapps.booking.controllers;

import com.mangoapps.booking.models.Movie;
import com.mangoapps.booking.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovies(@RequestBody List<Movie> movies) {
        try {
            String result = movieService.addMovies(movies);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add movies: " + e.getMessage());
        }
    }
    @GetMapping("/getAllMoviesWithShowsTimingAndSeats")
    public ResponseEntity<List<Map<String, Object>>> getAllMoviesWithShowsTimingAndSeats() {
        try {
            List<Map<String, Object>> movies = movieService.getAllMoviesWithShowsTimingAndSeats();
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }

}

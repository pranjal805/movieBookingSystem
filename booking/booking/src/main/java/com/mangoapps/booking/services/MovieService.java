package com.mangoapps.booking.services;

import com.mangoapps.booking.models.Movie;

import java.util.List;
import java.util.Map;

public interface MovieService {
    public String addMovies(List<Movie> movies);
    public List<Map<String,Object>> getAllMoviesWithShowsTimingAndSeats();
}

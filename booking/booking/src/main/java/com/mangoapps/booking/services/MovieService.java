package com.mangoapps.booking.services;

import com.mangoapps.booking.models.Movie;
import com.mangoapps.booking.models.Show;
import com.mangoapps.booking.repositories.MovieRepository;
import com.mangoapps.booking.repositories.ShowRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional
    public String addMovies(List<Movie> movies) {
        log.info("We have entered addMovies service");
        for (Movie movie : movies) {
            for (Show show : movie.getShows()) {
                for (int i = 1; i <= show.getTotalSeats(); i++) {
                    show.getAvailableSeatNumbers().add(i);
                }
                show.setMovie(movie);
                show.setAvailableSeats(show.getTotalSeats());
                showRepository.save(show);
            }
            movieRepository.save(movie);
        }
        return "Success";
    }

    public List<Map<String,Object>> getAllMoviesWithShowsTimingAndSeats(){
        String query="select a.title,a.genre,b.show_time,b.id as show_id,b.available_seats from movie a join show b on a.id=b.movie_id";
        return jdbcTemplate.queryForList(query);
    }
}

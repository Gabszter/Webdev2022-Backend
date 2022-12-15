package com.gabszter.movielistbackend.controller;

import com.gabszter.movielistbackend.model.Movie;
import com.gabszter.movielistbackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("http://localhost:3000")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @PostMapping("/movie")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        try {
            Movie _movie = movieRepository.save(new Movie(movie.getName(),movie.getWatched(),movie.getScore()));
            return new ResponseEntity<>(_movie,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(required = false) String name) {
        try {
            List<Movie> movies = new ArrayList<Movie>();

            if (name == null)
                movieRepository.findAll().forEach(movies::add);
            else movieRepository.findByNameContaining(name).forEach(movies::add);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") long id){
        Optional<Movie> movieData = movieRepository.findById(id);

        if (movieData.isPresent()) {
            return new ResponseEntity<>(movieData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") long id, @RequestBody Movie movie) {
        Optional<Movie> movieData = movieRepository.findById(id);

        if (movieData.isPresent()){
            Movie _movie = movieData.get();
            _movie.setName(movie.getName());
            _movie.setWatched(movie.getWatched());
            _movie.setScore(movie.getScore());
            return new ResponseEntity<>(movieRepository.save(_movie),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("id") long id){
        try {
            movieRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

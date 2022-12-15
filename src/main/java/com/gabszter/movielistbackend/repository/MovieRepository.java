package com.gabszter.movielistbackend.repository;

import com.gabszter.movielistbackend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findByNameContaining(String name);
}

package com.data.java_ss19.repository;

import com.data.java_ss19.entity.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> findAll();
    Movie findById(Long id);
    void save(Movie movie);
    void update(Movie movie);
    void delete(Long id);
}

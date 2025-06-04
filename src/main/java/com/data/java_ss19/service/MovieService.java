package com.data.java_ss19.service;

import com.data.java_ss19.entity.Movie;
import com.data.java_ss19.repository.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MovieService {
    @Autowired
    private MovieDao movieDao;
    public List<Movie> findAll() {
        return movieDao.findAll();
    }
    public Movie findById(Long id) {
        return movieDao.findById(id);
    }
    public void save(Movie movie) {
        movieDao.save(movie);
    }
    public void update(Movie movie) {
        movieDao.update(movie);
    }
    public void delete(Long id) {
        movieDao.delete(id);
    }
}

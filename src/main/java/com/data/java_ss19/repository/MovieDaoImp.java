package com.data.java_ss19.repository;

import com.data.java_ss19.entity.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MovieDaoImp implements MovieDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Movie> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Movie", Movie.class).list();
        }
    }

    @Override
    public Movie findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Movie.class, id);
        }
    }

    @Override
    public void save(Movie movie) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(movie);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Movie movie) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(movie);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Movie movie = session.get(Movie.class, id);
            if (movie != null) {
                session.delete(movie);
            }
            session.getTransaction().commit();
        }
    }
}

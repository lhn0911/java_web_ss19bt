package com.data.java_ss19.repository;

import com.data.java_ss19.entity.Theater;
import lombok.var;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TheaterDaoImpl implements TheaterDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Theater> findAll() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("FROM Theater", Theater.class).list();
        }
    }

    @Override
    public Theater findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            return session.get(Theater.class, id);
        }
    }

    @Override
    public void save(Theater theater) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.save(theater);
            transaction.commit();
        }
    }

    @Override
    public void update(Theater theater) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.update(theater);
            transaction.commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            Theater theater = session.get(Theater.class, id);
            if (theater != null) {
                session.delete(theater);
            }
            transaction.commit();
        }
    }

    @Override
    public boolean hasSchedule(Long theaterId) {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("SELECT COUNT(s) > 0 FROM Schedule s WHERE s.theater.id = :theaterId", Boolean.class);
            query.setParameter("theaterId", theaterId);
            return query.uniqueResult();
        }
    }
}

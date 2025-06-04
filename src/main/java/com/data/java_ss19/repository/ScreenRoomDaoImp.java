package com.data.java_ss19.repository;

import com.data.java_ss19.entity.ScreenRoom;
import lombok.var;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ScreenRoomDaoImp implements ScreenRoomDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void save(ScreenRoom screenRoom) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.save(screenRoom);
            transaction.commit();
        }
    }

    @Override
    public void update(ScreenRoom screenRoom) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.update(screenRoom);
            transaction.commit();
        }
    }

    @Override
    public void deactivate(Long id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            ScreenRoom screenRoom = session.get(ScreenRoom.class, id);
            if (screenRoom != null) {
                screenRoom.setStatus(false);
                session.update(screenRoom);
            }
            transaction.commit();
        }
    }

    @Override
    public ScreenRoom findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            return session.get(ScreenRoom.class, id);
        }
    }

    @Override
    public List<ScreenRoom> findAllActive() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("FROM ScreenRoom WHERE status = true", ScreenRoom.class).list();
        }
    }
}

package com.data.java_ss19.repository;

import com.data.java_ss19.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDaoImp implements UserDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getUsers(int page, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }

    @Override
    public long countUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT COUNT(u.id) FROM User u", Long.class).uniqueResult();
        }
    }

    @Override
    public void updateUserStatus(Long id, boolean isActive) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                user.setActive(isActive);
                session.update(user);
            }
            tx.commit();
        }
    }

    @Override
    public void saveUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }
    }


}

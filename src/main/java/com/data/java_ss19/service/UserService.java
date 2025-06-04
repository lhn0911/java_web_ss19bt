package com.data.java_ss19.service;

import com.data.java_ss19.entity.User;
import com.data.java_ss19.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public List<User> getUsers(int page, int pageSize) {
        return userDao.getUsers(page, pageSize);
    }
    public long countUsers() {
        return userDao.countUsers();
    }
    public void updateUserStatus(Long id, boolean isActive) {
        userDao.updateUserStatus(id, isActive);
    }
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
}

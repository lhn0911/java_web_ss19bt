package com.data.java_ss19.repository;

import com.data.java_ss19.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers(int page, int pageSize);
    long countUsers();
    void updateUserStatus(Long id, boolean isActive);
    void saveUser(User user);
}

package com.data.java_ss19.repository;

import com.data.java_ss19.entity.Theater;

import java.util.List;

public interface TheaterDao {
    List<Theater> findAll();
    Theater findById(Long id);
    void save(Theater theater);
    void update(Theater theater);
    void delete(Long id);
    boolean hasSchedule(Long theaterId);

}

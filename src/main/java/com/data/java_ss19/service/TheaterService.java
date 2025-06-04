package com.data.java_ss19.service;

import com.data.java_ss19.entity.Theater;
import com.data.java_ss19.repository.TheaterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {
    @Autowired
    private TheaterDao theaterDao;
    public List<Theater> findAll() {
        return theaterDao.findAll();
    }
    public Theater findById(Long id) {
        return theaterDao.findById(id);
    }
    public void save(Theater theater) {
        theaterDao.save(theater);
    }
    public void update(Theater theater) {
        theaterDao.update(theater);
    }
    public void delete(Long id) {
        theaterDao.delete(id);
    }
    public boolean hasSchedule(Long theaterId) {
        return theaterDao.hasSchedule(theaterId);
    }
}

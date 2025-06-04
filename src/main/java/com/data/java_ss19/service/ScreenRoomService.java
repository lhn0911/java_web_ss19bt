package com.data.java_ss19.service;

import com.data.java_ss19.entity.ScreenRoom;
import com.data.java_ss19.repository.ScreenRoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenRoomService {
    @Autowired
    private ScreenRoomDao screenRoomDao;

    public void save(ScreenRoom screenRoom) {
        screenRoomDao.save(screenRoom);
    }

    public void update(ScreenRoom screenRoom) {
        screenRoomDao.update(screenRoom);
    }

    public void deactivate(Long id) {
        screenRoomDao.deactivate(id);
    }

    public ScreenRoom findById(Long id) {
        return screenRoomDao.findById(id);
    }

    public List<ScreenRoom> findAllActive() {
        return screenRoomDao.findAllActive();
    }
}

package com.data.java_ss19.repository;

import com.data.java_ss19.entity.ScreenRoom;

import java.util.List;

public interface ScreenRoomDao {
    void save(ScreenRoom screenRoom);
    void update(ScreenRoom screenRoom);
    void deactivate(Long id);
    ScreenRoom findById(Long id);
    List<ScreenRoom> findAllActive();
}

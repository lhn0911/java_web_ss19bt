package com.data.java_ss19.entity;

import javax.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatName;
    private boolean status = true;
    @ManyToOne
    @JoinColumn(name = "screen_room_id")
    private ScreenRoom screenRoom;
}

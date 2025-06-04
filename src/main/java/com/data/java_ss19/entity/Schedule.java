package com.data.java_ss19.entity;

import javax.persistence.*;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
}

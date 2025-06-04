package com.data.java_ss19.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreenRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomName;
    private Integer capacity;
    private String screenType;
    private boolean status = true;
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
    @OneToMany(mappedBy = "screenRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();
}

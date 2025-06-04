package com.data.java_ss19.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String theaterName;
    private String address;
    private int numberScreenRoom;
    private boolean status = true;
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScreenRoom> screenRooms;
}

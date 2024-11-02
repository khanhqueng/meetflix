package com.khanhisdev.movieservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "theater")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<ProjectionRoom> projectionRoomList= new ArrayList<>();
    @OneToMany(mappedBy = "theater")
    private List<Showtime> showtimeList;
    @Column(name = "address")
    private String address;
}

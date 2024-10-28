package com.khanhisdev.movieservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "projection_room")
public class ProjectionRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectionRoom_id")
    private Long id;
    private int number;
    private int seats;
    @ManyToOne
    @JoinColumn(name = "theater_id",referencedColumnName = "theater_id", nullable = false)
    private Theater theater;
    @OneToMany(mappedBy = "projectionRoom")
    private List<Showtime> showtimeList;
    @OneToMany(mappedBy = "projectionRoom", cascade = CascadeType.ALL)
    private List<Seat> seat;
}

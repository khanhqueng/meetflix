package com.khanhisdev.movieservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "showtime")
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id")
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String time;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false, referencedColumnName = "movie_id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false, referencedColumnName = "theater_id")
    private Theater theater;
    @ManyToOne
    @JoinColumn(name = "projectionRoom_id", nullable = false,referencedColumnName = "projectionRoom_id")
    private ProjectionRoom projectionRoom;

}

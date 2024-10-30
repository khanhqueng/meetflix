package com.khanhisdev.userservice.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long id;
    private String name;
    private String description;
    // by minutes
    private String country;
    private LocalDate releaseDate;
    private int durationMin;
    private String urlImage;
}

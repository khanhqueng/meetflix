package com.khanhisdev.movieservice.dto.RequestDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.khanhisdev.movieservice.entity.Actor;
import com.khanhisdev.movieservice.entity.Category;
import com.khanhisdev.movieservice.entity.Director;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieUpdateDto {
    private String name;
    private String country;
    private String description;
    private int durationMin;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate releaseDate;
    private Set<Director> director;
    private Set<Actor> actors;
    private String urlImage;
    private Set<Category> categories;
    private Integer ratingPoint;
}

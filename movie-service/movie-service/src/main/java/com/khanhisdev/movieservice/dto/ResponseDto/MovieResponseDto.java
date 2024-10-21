package com.khanhisdev.movieservice.dto.ResponseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.khanhisdev.movieservice.dto.Message.CategoryMessage;
import com.khanhisdev.movieservice.entity.Actor;
import com.khanhisdev.movieservice.entity.Director;
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
public class MovieResponseDto {
    private Long id;
    private String name;
    private String description;
    private String country;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate releaseDate;
    // by minutes
    private int durationMin;
    private List<Director> director;
    private List<Actor> actors;
    private String urlImage;
    private List<CategoryMessage> categories;
}

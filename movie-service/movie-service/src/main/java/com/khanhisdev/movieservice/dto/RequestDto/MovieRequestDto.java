package com.khanhisdev.movieservice.dto.RequestDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.khanhisdev.movieservice.dto.Message.CategoryMessage;
import com.khanhisdev.movieservice.entity.Actor;
import com.khanhisdev.movieservice.entity.Director;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class MovieRequestDto {
    @NotEmpty(message = "Name movie cannot be empty")
    private String name;
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    @NotEmpty(message = "Country cannot be empty")
    private String country;
    @NotNull(message = "Release Date cannot be empty")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate releaseDate;
    @Positive(message = "Duration movie cannot be negative")
    private int durationMin;
    private List<Director> director;
    private List<Actor> actors;
    private String urlImage;
    @NotEmpty(message = "Categories cannot be empty")
    private List<Long> category_id;
}

package com.khanhisdev.movieservice.dto.Response;

import com.khanhisdev.movieservice.dto.Message.CategoryMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDto {
    private Long id;
    private String name;
    private String description;
    // by minutes
    private int durationMin;
    private List<String> director;
    private List<String> actors;
    private String urlImage;
    private List<CategoryMessage> categories;
}

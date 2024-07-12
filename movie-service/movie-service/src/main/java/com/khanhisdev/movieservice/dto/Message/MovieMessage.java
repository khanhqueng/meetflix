package com.khanhisdev.movieservice.dto.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieMessage {
    private Long id;
    private String name;
    private String description;
    // by minutes
    private int durationMin;
    private List<String> director;
    private List<String> actors;
    private String urlImage;
}

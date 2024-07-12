package com.khanhisdev.movieservice.dto.RequestDto;

import com.khanhisdev.movieservice.dto.Message.CategoryMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {
    @NotEmpty(message = "Name movie cannot be empty")
    private String name;
    @NotEmpty(message = "Description movie cannot be empty")
    private String description;
    // by minutes
    @Positive(message = "Duration movie cannot be negative")
    private int durationMin;
    private List<String> director;
    private List<String> actors;
    private String urlImage;
    private List<CategoryMessage> categories;


}

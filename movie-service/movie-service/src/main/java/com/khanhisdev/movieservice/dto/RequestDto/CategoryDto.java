package com.khanhisdev.movieservice.dto.RequestDto;

import com.khanhisdev.movieservice.dto.Message.MovieResponseDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    @NotEmpty(message = "Name category cannot be empty")
    private String name;
    private String thumbnailUrl;
    private String url_key;
    private List<MovieResponseDto> movies;

}

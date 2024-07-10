package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.movieservice.dto.RequestDto.MovieDto;
import com.khanhisdev.movieservice.dto.Response.MovieResponseDto;
import com.khanhisdev.movieservice.entity.Movie;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class MovieMapper {
    @Autowired
    private ModelMapper mapper;
    public Movie mapToEntity(MovieDto movieDto){
        Movie movie= mapper.map(movieDto, Movie.class);
        return movie;

    }
    public MovieDto mapToDto(Movie movie){
        MovieDto movieDto= mapper.map(movie, MovieDto.class);
        return movieDto;
    }
    public MovieResponseDto mapToResponseDto(Movie movie){
        MovieResponseDto movieResponseDto= mapper.map(movie, MovieResponseDto.class);
        return movieResponseDto;
    }

}

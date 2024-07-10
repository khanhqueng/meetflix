package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.dto.RequestDto.MovieDto;
import com.khanhisdev.movieservice.dto.Response.MovieResponseDto;
import com.khanhisdev.movieservice.dto.Response.ObjectResponse;

import java.util.List;

public interface MovieService {
    MovieResponseDto saveMovie(MovieDto movieDto);
    MovieResponseDto getMovieById(Long id);
    ObjectResponse<MovieResponseDto> getAllMovies(int pageNo, int pageSize,String sortBy,String sortDir);
    List<MovieResponseDto>  getMoviesByIds(List<Long > ids);
}

package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.dto.Model.MovieDto;
import com.khanhisdev.movieservice.dto.Response.ObjectResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {
    MovieDto saveMovie(MovieDto movieDto);
    MovieDto getMovieById(Long id);
    ObjectResponse<MovieDto> getAllMovies(int pageNo, int pageSize,String sortBy,String sortDir);
    List<MovieDto>  getMoviesByIds(List<Long > ids);
}

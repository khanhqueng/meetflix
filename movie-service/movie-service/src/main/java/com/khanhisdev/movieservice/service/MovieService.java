package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.dto.Model.MovieDto;
import com.khanhisdev.movieservice.dto.Response.ObjectResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface MovieService {
    MovieDto saveMovie(MovieDto movieDto);
    MovieDto getMovieByName(Long id);
    ObjectResponse<MovieDto> getAllMovies(int pageNo, int pageSize,String sortBy,String sortDir);

}

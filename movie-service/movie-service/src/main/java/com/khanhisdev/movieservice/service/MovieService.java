package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.dto.RequestDto.MovieRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.MovieResponseDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ObjectResponse;

import java.util.List;

public interface MovieService {
    MovieResponseDto saveMovie(MovieRequestDto movieDto);
    MovieResponseDto getMovieById(Long id);
    ObjectResponse<MovieResponseDto> getAllMovies(int pageNo, int pageSize,String sortBy,String sortDir);
    List<MovieResponseDto>  getMoviesByIds(List<Long > ids);
}

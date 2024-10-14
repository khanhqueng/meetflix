package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.dto.RequestDto.MovieRequestDto;
import com.khanhisdev.movieservice.dto.RequestDto.UserRatingDto;
import com.khanhisdev.movieservice.dto.ResponseDto.MovieResponseDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ObjectResponse;
import com.khanhisdev.movieservice.dto.ResponseDto.RatingPointResponseDto;
import com.khanhisdev.movieservice.entity.Movie;

import java.util.List;

public interface MovieService {
    MovieResponseDto saveMovie(MovieRequestDto movieDto);
    MovieResponseDto getMovieById(Long id);
    ObjectResponse<MovieResponseDto> getAllMovies(int pageNo, int pageSize,String sortBy,String sortDir);
    List<MovieResponseDto> getAllMoviesFromTheater(Long theaterId);
    List<MovieResponseDto>  getMoviesByIds(List<Long > ids);
    RatingPointResponseDto ratingMovie(UserRatingDto userRatingDto);
    ObjectResponse<MovieResponseDto> findMoviesByFullSearchText(String keyword, int pageNo, int pageSize,String sortBy,String sortDir);
}

package com.khanhisdev.movieservice.controller;

import com.khanhisdev.movieservice.dto.RequestDto.MovieRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.MovieResponseDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ObjectResponse;
import com.khanhisdev.movieservice.entity.Movie;
import com.khanhisdev.movieservice.repository.MovieRepository;
import com.khanhisdev.movieservice.service.MovieService;
import com.khanhisdev.movieservice.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;
    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie(@Valid @RequestBody MovieRequestDto movieDto){
        return new ResponseEntity<>(movieService.saveMovie(movieDto), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable("id") Long id){
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }
    @GetMapping
    public ObjectResponse<MovieResponseDto> getAllMovies(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return movieService.getAllMovies(pageNo, pageSize, sortBy, sortDir);
    }
    @GetMapping("/ids")
    public ResponseEntity<List<MovieResponseDto>> getMoviesByIds(@RequestParam List<Long> ids){
        return new ResponseEntity<>(movieService.getMoviesByIds(ids), HttpStatus.OK);
    }
    @GetMapping("/theater/{id}")
    public ResponseEntity<List<MovieResponseDto>> getMoviesByTheater(@PathVariable(name = "id") Long theaterId){
        return new ResponseEntity<>(movieService.getAllMoviesFromTheater(theaterId), HttpStatus.OK);
    }



}

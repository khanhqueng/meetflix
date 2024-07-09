package com.khanhisdev.movieservice.controller;

import com.khanhisdev.movieservice.dto.Model.MovieDto;
import com.khanhisdev.movieservice.dto.Response.ObjectResponse;
import com.khanhisdev.movieservice.service.MovieService;
import com.khanhisdev.movieservice.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto){
        return new ResponseEntity<>(movieService.saveMovie(movieDto), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("id") Long id){
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }
    @GetMapping
    public ObjectResponse<MovieDto> getAllMovies(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return movieService.getAllMovies(pageNo, pageSize, sortBy, sortDir);
    }
    @GetMapping("/ids")
    public ResponseEntity<List<MovieDto>> getMoviesByIds(@RequestParam List<Long> ids){
        return new ResponseEntity<>(movieService.getMoviesByIds(ids), HttpStatus.OK);
    }

}

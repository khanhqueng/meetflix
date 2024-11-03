package com.khanhisdev.movieservice.controller;

import com.khanhisdev.movieservice.dto.RequestDto.MovieRequestDto;
import com.khanhisdev.movieservice.dto.RequestDto.MovieUpdateDto;
import com.khanhisdev.movieservice.dto.RequestDto.UserRatingDto;
import com.khanhisdev.movieservice.dto.ResponseDto.MovieResponseDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ObjectResponse;
import com.khanhisdev.movieservice.dto.ResponseDto.RatingPointResponseDto;
import com.khanhisdev.movieservice.entity.Movie;
import com.khanhisdev.movieservice.repository.MovieRepository;
import com.khanhisdev.movieservice.service.MovieService;
import com.khanhisdev.movieservice.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@Tag(name = "Movie Controller")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    @Operation(summary = "Add movie", description = "API for create new movie")
    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie(@Valid @RequestBody MovieRequestDto movieDto){
        return new ResponseEntity<>(movieService.saveMovie(movieDto), HttpStatus.CREATED);
    }
    @Operation(summary = "get movie by id", description = "API for get movie by its id")
    @GetMapping("{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable("id") Long id){
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }
    @Operation(summary = "Get all movies by pageNo and pageSize", description = "API for get movie by paging")
    @GetMapping
    public ResponseEntity<ObjectResponse<MovieResponseDto>> getAllMovies(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return new ResponseEntity<>(movieService.getAllMovies(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK) ;
    }
    @Operation(summary = "Get movie by list id (used for user-services)", description = "API for get liked movie by userId")
    @GetMapping("/ids")
    public ResponseEntity<List<MovieResponseDto>> getMoviesByIds(@RequestParam List<Long> ids){
        return new ResponseEntity<>(movieService.getMoviesByIds(ids), HttpStatus.OK);
    }
    @Operation(summary = "Get movies by theater", description = "API for get movies a theater showing")
    @GetMapping("/theater/{id}")
    public ResponseEntity<List<MovieResponseDto>> getMoviesByTheater(@PathVariable(name = "id") Long theaterId){
        return new ResponseEntity<>(movieService.getAllMoviesFromTheater(theaterId), HttpStatus.OK);
    }
    @Operation(summary = "Rating movie", description = "API for add rating point for a movie")
    @PutMapping("/rating")
    public ResponseEntity<RatingPointResponseDto> ratingMovie(@RequestBody UserRatingDto request){
        return new ResponseEntity<>(movieService.ratingMovie(request), HttpStatus.OK);
    }
    @Operation(summary = "Get searched movie", description = "API for full text search a movie")
    @GetMapping("/search")
    public ResponseEntity<ObjectResponse<MovieResponseDto>> getMovieBySearch(
            @RequestParam(name = "searchText") String searchText,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ){
        return new ResponseEntity<>(movieService.findMoviesByFullSearchText(searchText,pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }
    @Operation(summary = "Update movie", description = "API for update movie")
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable(name = "id")Long movieId, @RequestBody MovieUpdateDto updateDto){
        return new ResponseEntity<>(movieService.updateMovie(updateDto,movieId), HttpStatus.OK);
    }
    @Operation(summary = "Delete movie", description = "API for delete movie")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable(name = "id")Long movieId){
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }


}

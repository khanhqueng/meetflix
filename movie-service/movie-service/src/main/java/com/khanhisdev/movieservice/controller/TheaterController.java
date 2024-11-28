package com.khanhisdev.movieservice.controller;

import com.khanhisdev.movieservice.dto.RequestDto.GetTheaterAndShowTimesDto;
import com.khanhisdev.movieservice.dto.RequestDto.TheaterRequestDto;
import com.khanhisdev.movieservice.dto.RequestDto.TheaterUpdateDto;
import com.khanhisdev.movieservice.dto.ResponseDto.TheaterResponseDto;
import com.khanhisdev.movieservice.dto.ResponseDto.TheatersAndShowTimesResponseDto;
import com.khanhisdev.movieservice.service.TheaterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
@Tag(name = "Theater Controller")
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;

    @Operation(summary = "Get All theater", description = "API for get all theaters")
    @GetMapping
    public ResponseEntity<List<TheaterResponseDto>> getAllTheaters(){
        return new ResponseEntity<>(theaterService.getAllTheaters(), HttpStatus.OK);
    }

    @Operation(summary = "Get theater by id", description = "API for get theater by id")
    @GetMapping("/{id}")
    public ResponseEntity<TheaterResponseDto> getTheaterById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(theaterService.getTheaterById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get theater with showtime by movie id and date", description = "API for get theater by id")
    @GetMapping("/specific")
    public ResponseEntity<List<TheatersAndShowTimesResponseDto>> getTheaterByMovieIdAndDate(@RequestBody GetTheaterAndShowTimesDto dto){
        return new ResponseEntity<>(theaterService.getByMovieIdAndDate(dto.getDate(),dto.getMovieId()), HttpStatus.OK);
    }

    @Operation(summary = "Add new theater", description = "API for create new theater")
    @PostMapping
    public ResponseEntity<TheaterResponseDto> createTheater(@RequestBody TheaterRequestDto theaterRequestDto){
        return new ResponseEntity<>(theaterService.createTheater(theaterRequestDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update theater", description = "API for update theater")
    @PutMapping("/{id}")
    public ResponseEntity<TheaterResponseDto> updateTheater(@RequestBody TheaterUpdateDto updateDto, @PathVariable(name = "id") Long id){
        return new ResponseEntity<>(theaterService.updateTheater(updateDto, id), HttpStatus.OK);
    }

    @Operation(summary = "Delete theater", description = "API for delete theater")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheater(@PathVariable(name = "id") Long id){
        theaterService.deleteTheater(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}

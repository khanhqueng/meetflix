package com.khanhisdev.movieservice.controller;

import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeDto;
import com.khanhisdev.movieservice.dto.RequestDto.TheaterDto;
import com.khanhisdev.movieservice.dto.Response.ShowtimeResponseDto;
import com.khanhisdev.movieservice.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showtime")
public class ShowtimeController {
    @Autowired
    private ShowtimeService showtimeService;
    @PostMapping
    public ResponseEntity<ShowtimeResponseDto> createShowtime(@RequestBody ShowtimeDto showtimeDto,
                                                              @RequestParam(name = "movie") Long movieId,
                                                              @RequestParam(name = "theater") Long theaterId,
                                                              @RequestParam(name = "projectionRoom") Long roomId){
        return new ResponseEntity<>(showtimeService.addShowtime(showtimeDto,movieId,theaterId,roomId), HttpStatus.CREATED);
    }
    @GetMapping("/movie/{id}")
    public ResponseEntity<List<ShowtimeResponseDto>> getByMovieId(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(showtimeService.getAllShowtimeByMovieId(id), HttpStatus.OK) ;
    }
    @GetMapping("/room/{id}")
    public ResponseEntity<List<ShowtimeResponseDto>> getByRoomId(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(showtimeService.getAllShowtimeByRoomId(id), HttpStatus.OK) ;
    }
    @PutMapping("/{id}")
    public ResponseEntity<ShowtimeResponseDto> updateShowtime(@PathVariable(name = "id") Long id,@RequestBody ShowtimeDto showtimeDto){
        return new ResponseEntity<>(showtimeService.updateShowtime(id,showtimeDto), HttpStatus.OK) ;
    }
}

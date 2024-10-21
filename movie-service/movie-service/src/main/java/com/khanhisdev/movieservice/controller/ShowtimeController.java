package com.khanhisdev.movieservice.controller;

import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeForOrderRequest;
import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ShowtimeForOrderDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ShowtimeResponseDto;
import com.khanhisdev.movieservice.service.ShowtimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showtime")
@Tag(name = "Showtime Controller")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeService showtimeService;
    @PostMapping
    @Operation(summary = "Create showtime", description = "API for create new showtime for a movie")
    public ResponseEntity<ShowtimeResponseDto> createShowtime(@RequestBody ShowtimeRequestDto showtimeRequestDto){
        return new ResponseEntity<>(showtimeService.addShowtime(showtimeRequestDto), HttpStatus.CREATED);
    }
    @Operation(summary = "Get all showtime by Movie id", description = "API for get all showtime for a movie")
    @GetMapping("/movie/{id}")
    public ResponseEntity<List<ShowtimeResponseDto>> getByMovieId(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(showtimeService.getAllShowtimeByMovieId(id), HttpStatus.OK) ;
    }
    @Operation(summary = "Get all seats ordered and info about that movie (used by order service)", description = "API for get seats ordered for movie")
    @PostMapping("/order")
    public ResponseEntity<List<ShowtimeForOrderDto>> getShowtimeFromOrder(@RequestBody List<ShowtimeForOrderRequest> requests){
        return new ResponseEntity<>(showtimeService.getShowtimeFromOrder(requests),HttpStatus.OK);
    }
    @Operation(summary = "Get showtime a room have", description = "API for get showtime a room have")
    @GetMapping("/room/{id}")
    public ResponseEntity<List<ShowtimeResponseDto>> getByRoomId(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(showtimeService.getAllShowtimeByRoomId(id), HttpStatus.OK) ;
    }
    @Operation(summary = "Update showtime", description = "API for update a showtime")
    @PutMapping("/{id}")
    public ResponseEntity<ShowtimeResponseDto> updateShowtime(@PathVariable(name = "id") Long id,@RequestBody ShowtimeRequestDto showtimeRequestDto){
        return new ResponseEntity<>(showtimeService.updateShowtime(id, showtimeRequestDto), HttpStatus.OK) ;
    }
    @Operation(summary = "Delete showtime", description = "API for delete showtime")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShowtime(@PathVariable(name = "id") Long id){
        showtimeService.deleteShowtime(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK) ;
    }

}

package com.khanhisdev.movieservice.controller;

import com.khanhisdev.movieservice.entity.Seat;
import com.khanhisdev.movieservice.service.SeatService;
import jakarta.ws.rs.Path;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Seat>> getAllSeatsByRoomId(@PathVariable(name = "id") Long roomId){
        return new ResponseEntity<>(seatService.getAllSeatsByRoomId(roomId), HttpStatus.OK);
    }
}

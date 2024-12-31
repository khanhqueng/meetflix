package com.khanhisdev.orderservice.controller;

import com.khanhisdev.orderservice.dto.Request.GetOrderedSeatsDto;
import com.khanhisdev.orderservice.dto.Response.SeatsResponse;
import com.khanhisdev.orderservice.dto.Response.StatisticDto;
import com.khanhisdev.orderservice.service.OrderService;
import com.khanhisdev.orderservice.utils.CustomHeaders;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
@RequiredArgsConstructor
public class SeatController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<SeatsResponse> getAllSeatsOrdered(@RequestBody GetOrderedSeatsDto getOrderedSeatsDto){
        return new ResponseEntity<>(orderService.getAllOrderedSeats(getOrderedSeatsDto), HttpStatus.OK);
    }

}

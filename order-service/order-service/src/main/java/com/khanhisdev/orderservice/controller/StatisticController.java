package com.khanhisdev.orderservice.controller;

import com.khanhisdev.orderservice.dto.Response.StatisticDto;
import com.khanhisdev.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticController {
    private final OrderService orderService;
    @GetMapping()
    public ResponseEntity<StatisticDto> statistic(){
        return new ResponseEntity<>(orderService.statistic(), HttpStatus.OK);
    }
}

package com.khanhisdev.orderservice.controller;

import com.khanhisdev.orderservice.dto.Request.AddTicketRequest;
import com.khanhisdev.orderservice.dto.Request.DeleteTicketRequest;
import com.khanhisdev.orderservice.service.BaseRedisService;
import com.khanhisdev.orderservice.service.OrderRedisService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.Name;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class RedisController {
    private final OrderRedisService redisService;
    @PostMapping
    public void set(){
        redisService.set("hello", "khanh");

    }
    @PostMapping("/{id}")
    public ResponseEntity<String> addTicketToOrder(@PathVariable(name = "id") String id, @RequestBody AddTicketRequest addTicketRequest){
        redisService.addTicketToCart(id,addTicketRequest);
        return new ResponseEntity<>("Added Successfully", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicketFromOrder(@PathVariable(name = "id") String id, @RequestBody DeleteTicketRequest deleteTicketRequest){
        redisService.deleteTicketInCart(id,deleteTicketRequest);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteAllTicketsFromOrder(@PathVariable(name = "id") String id){
        redisService.deleteAllTicket(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}

package com.khanhisdev.orderservice.controller;

import com.khanhisdev.orderservice.dto.Request.AddTicketRequest;
import com.khanhisdev.orderservice.dto.Request.DeleteTicketRequest;
import com.khanhisdev.orderservice.dto.Response.ShowtimeForOrderDto;
import com.khanhisdev.orderservice.service.BaseRedisService;
import com.khanhisdev.orderservice.service.OrderRedisService;
import com.khanhisdev.orderservice.utils.CustomHeaders;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.Name;
import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class RedisController {
    private final OrderRedisService redisService;
    @PostMapping("/test")
    public void set(){
        redisService.set("hello", "khanh");

    }
    @PostMapping
    public ResponseEntity<String> addTicketToOrder(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) String id, @RequestBody AddTicketRequest addTicketRequest){
        redisService.addTicketToCart(id,addTicketRequest);
        return new ResponseEntity<>("Added Successfully", HttpStatus.OK);
    }
    @GetMapping("/customer")
    public ResponseEntity<List<ShowtimeForOrderDto>> getSeatsOrdered(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) String id){
        return new ResponseEntity<>(redisService.getShowtimeFromCart(id), HttpStatus.OK);
    }
    @DeleteMapping("/ticket")
    public ResponseEntity<String> deleteTicketFromOrder(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) String id, @RequestBody DeleteTicketRequest deleteTicketRequest){
        redisService.deleteTicketInCart(id,deleteTicketRequest);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllTicketsFromOrder(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) String id){
        redisService.deleteAllTicket(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

}

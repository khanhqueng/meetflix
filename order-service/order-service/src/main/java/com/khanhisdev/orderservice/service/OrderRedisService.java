package com.khanhisdev.orderservice.service;

import com.khanhisdev.orderservice.dto.Request.AddTicketRequest;
import com.khanhisdev.orderservice.dto.Request.DeleteTicketRequest;
import com.khanhisdev.orderservice.dto.Response.ShowtimeForOrderDto;

import java.util.List;

public interface OrderRedisService extends BaseRedisService<String,String,Object>{
    void addTicketToCart(String userId, AddTicketRequest addTicketRequest);
    void deleteTicketInCart(String userId, DeleteTicketRequest deleteTicketRequest);
    List<ShowtimeForOrderDto> getShowtimeFromCart(String userId);
    void deleteAllTicket(String userId);

}

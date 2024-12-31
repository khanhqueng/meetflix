package com.khanhisdev.orderservice.service;

import com.khanhisdev.orderservice.dto.Request.AddTicketRequest;
import com.khanhisdev.orderservice.dto.Request.DeleteTicketRequest;
import com.khanhisdev.orderservice.dto.Request.GetOrderedSeatsDto;
import com.khanhisdev.commons.dto.ShowtimeForOrderDto;
import com.khanhisdev.orderservice.dto.Response.SeatsResponse;
import com.khanhisdev.orderservice.dto.Response.StatisticDto;

import java.util.List;

public interface OrderService extends BaseRedisService<String,String,List<String>>{
    void addTicketToCart(String userId, AddTicketRequest addTicketRequest);
    void deleteTicketInCart(String userId, DeleteTicketRequest deleteTicketRequest);
    List<ShowtimeForOrderDto> getShowtimeFromCart(String userId);
    void deleteAllTicket(String userId);
    SeatsResponse getAllOrderedSeats(GetOrderedSeatsDto dto);
    StatisticDto statistic();
}

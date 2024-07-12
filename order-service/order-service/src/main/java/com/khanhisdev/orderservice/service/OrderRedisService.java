package com.khanhisdev.orderservice.service;

import com.khanhisdev.orderservice.dto.Request.AddTicketRequest;
import com.khanhisdev.orderservice.dto.Request.DeleteTicketRequest;

public interface OrderRedisService extends BaseRedisService<String,String,Object>{
    void addTicketToCart(String userId, AddTicketRequest addTicketRequest);
    void deleteTicketInCart(String userId, DeleteTicketRequest deleteTicketRequest);
    void deleteAllTicket(String userId);
}

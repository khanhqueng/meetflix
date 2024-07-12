package com.khanhisdev.orderservice.service.impl;

import com.khanhisdev.orderservice.dto.Request.AddTicketRequest;
import com.khanhisdev.orderservice.dto.Request.DeleteTicketRequest;
import com.khanhisdev.orderservice.exception.ResourceNotFoundException;
import com.khanhisdev.orderservice.service.OrderRedisService;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRedisServiceImpl extends BaseRedisServiceImpl<String,String,Object> implements OrderRedisService{


    public OrderRedisServiceImpl(RedisTemplate<String, Object> redisTemplate, HashOperations<String, String, Object> hashOperations) {
        super(redisTemplate, hashOperations);
    }

    @Override
    public void addTicketToCart(String userId, AddTicketRequest addTicketRequest) {
        String key= "order:user-"+ userId;
        String fieldKey;
        List<String> seatsNeedOrder= addTicketRequest.getSeats();
        fieldKey= "TicketInfo_"+ "MovieId:"+ addTicketRequest.getMovieId()+","
                +"TheaterId:" + addTicketRequest.getTheaterId()+","
                +"RoomId:"+ addTicketRequest.getProjectionRoomId()+","
                +"StartTime:"+ addTicketRequest.getShowtime();
        if(this.hashExists(key,fieldKey)){
            List<String>  seatsOrdered = (List<String>) this.hashGet(key,fieldKey);
            seatsNeedOrder.addAll(seatsOrdered);

        }
        this.hashSet(key,fieldKey,seatsNeedOrder);
        this.setTimeToLive(key, 30);

    }

    @Override
    public void deleteTicketInCart(String userId, DeleteTicketRequest deleteTicketRequest) {
        String key= "order:user-"+ userId;
        String fieldKey;
        fieldKey= "TicketInfo_"+ "MovieId:"+ deleteTicketRequest.getMovieId()+","
                +"TheaterId:" + deleteTicketRequest.getTheaterId()+","
                +"RoomId:"+ deleteTicketRequest.getProjectionRoomId()+","
                +"StartTime:"+ deleteTicketRequest.getShowtime();
        if(!this.hashExists(key,fieldKey)){
            throw new ResourceNotFoundException("Order","id",deleteTicketRequest.getProjectionRoomId());
        }
        this.delete(key,fieldKey);
    }

    @Override
    public void deleteAllTicket(String userId) {
        String key= "order:user-"+ userId;
        this.delete(key);
    }


}

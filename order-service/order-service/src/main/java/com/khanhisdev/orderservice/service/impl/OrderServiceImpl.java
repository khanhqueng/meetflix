package com.khanhisdev.orderservice.service.impl;

import com.khanhisdev.commons.dto.ShowtimeForOrderDto;
import com.khanhisdev.orderservice.dto.Message.EmailContent;
import com.khanhisdev.orderservice.dto.Message.OrderEvent;
import com.khanhisdev.orderservice.dto.Request.AddTicketRequest;
import com.khanhisdev.orderservice.dto.Request.DeleteTicketRequest;
import com.khanhisdev.orderservice.dto.Request.GetOrderedSeatsDto;
import com.khanhisdev.orderservice.dto.Request.GetTicketRequest;
import com.khanhisdev.orderservice.dto.Response.SeatDto;
import com.khanhisdev.orderservice.dto.Response.SeatsResponse;
import com.khanhisdev.orderservice.dto.Response.StatisticDto;
import com.khanhisdev.orderservice.exception.ResourceNotFoundException;
import com.khanhisdev.orderservice.publisher.OrderProducer;
import com.khanhisdev.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class OrderServiceImpl extends BaseRedisServiceImpl<String,String,List<String>> implements OrderService {
    @Value("${movie.host}")
    private String movie_hostname;
    @Value("${user.host}")
    private String user_hostname;
    @Autowired
    private WebClient webClient;
    @Autowired
    private OrderProducer producer;

    public OrderServiceImpl(RedisTemplate<String, List<String>> redisTemplate, HashOperations<String, String, List<String>> hashOperations) {
        super(redisTemplate, hashOperations);
    }

    @Override
    public void addTicketToCart(String userId, AddTicketRequest addTicketRequest) {
        String key= "order:user-"+ userId;
        String fieldKey;
        List<String> seatsNeedOrder= addTicketRequest.getSeats();
        fieldKey= "TicketInfo_"+"RoomId*"+ addTicketRequest.getProjectionRoomId()+","
                +"StartTime*"+ addTicketRequest.getShowtime()+","
                +"MovieId*"+ addTicketRequest.getMovieId()+","
                +"TheaterId*" + addTicketRequest.getTheaterId();
        if(this.hashExists(key,fieldKey)){

            List<String>  seatsOrdered = (List<String>) this.hashGet(key,fieldKey);
            seatsNeedOrder.addAll(seatsOrdered);

        }
        this.hashSet(key,fieldKey,seatsNeedOrder);
        this.setTimeToLive(key, 30);
        //Get Email of User from User Id
        String email= webClient.get()
                .uri("http://"+user_hostname+ ":8092/user/email/"+ userId)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Set content Email and send to message queue
//        OrderEvent orderEvent= new OrderEvent();
//        orderEvent.setStatus("PENDING");
//        orderEvent.setMessage("Email is in pending status");
//        EmailContent emailContent= new EmailContent();
//        emailContent.setDesEmail(email);
//        emailContent.setNameMovie(addTicketRequest.getMovieName());
//        emailContent.setNameRoom(addTicketRequest.getRoomName());
//        emailContent.setShowtime(String.valueOf(addTicketRequest.getShowtime()));
//        emailContent.setNameTheater(addTicketRequest.getTheaterName());
//        emailContent.setSeats(addTicketRequest.getSeats());
//        orderEvent.setContent(emailContent);
//        producer.sendMessage(orderEvent);
    }

    @Override
    public void deleteTicketInCart(String userId, DeleteTicketRequest deleteTicketRequest) {
        String key= "order:user-"+ userId;
        String fieldKey;
        fieldKey= "TicketInfo_"+"RoomId*"+ deleteTicketRequest.getProjectionRoomId()+","
                +"StartTime*"+ deleteTicketRequest.getShowtime()+","
                +"MovieId*"+ deleteTicketRequest.getMovieId()+","
                +"TheaterId*" + deleteTicketRequest.getTheaterId();
        if(!this.hashExists(key,fieldKey)){
            throw new ResourceNotFoundException("Order","id",deleteTicketRequest.getProjectionRoomId());
        }
        this.delete(key,fieldKey);
    }

    @Override
    public List<ShowtimeForOrderDto> getShowtimeFromCart(String userId) {
        String key= "order:user-"+ userId;
        Map<String, List<String>> showtime= this.getField(key);
        List<GetTicketRequest> getTicketRequests= new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : showtime.entrySet()) {
            String info= entry.getKey().split("_")[1];
            String[] specificINfo = info.split(",");
            Long roomId = Long.valueOf(specificINfo[0].split("\\*")[1]);
            LocalDateTime startTime = LocalDateTime.parse(specificINfo[1].split("\\*")[1]);
            List<String>  seatsOrdered = (List<String>) entry.getValue();
            getTicketRequests.add(new GetTicketRequest(startTime,roomId,seatsOrdered));
        }

        List<ShowtimeForOrderDto> response= webClient.post()
                .uri("http://"+movie_hostname+ ":8091/showtime/order")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(getTicketRequests)
                .retrieve()
                .bodyToFlux(ShowtimeForOrderDto.class)
                .collectList()
                .block();
        return response;

    }

    @Override
    public void deleteAllTicket(String userId) {
        String key= "order:user-"+ userId;
        this.delete(key);
    }

    @Override
    public SeatsResponse getAllOrderedSeats(GetOrderedSeatsDto dto) {
        try{
            Set<String> keys=this.getKeyByPattern("order:user*");
            List<String> result= new ArrayList<>();
            String field= "TicketInfo_"+"RoomId*"+ dto.getProjectionRoomId()+","
                    +"StartTime*"+ dto.getShowtime()+","
                    +"MovieId*"+ dto.getMovieId()+","
                    +"TheaterId*" + dto.getTheaterId();
            System.out.println(keys);
            for (String key: keys){
                if(this.hashGet(key,field)!=null){
                    List<String> seats= (List<String>) this.hashGet(key, field);
                    result.addAll(seats);
                }
            }

            List<SeatDto> allSeats= webClient.get()
                    .uri("http://"+movie_hostname+ ":8091/seats/"+dto.getProjectionRoomId())
                    .retrieve()
                    .bodyToFlux(SeatDto.class)
                    .collectList()
                    .block();
            return new SeatsResponse(allSeats,result);
        }
        catch (Throwable e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public StatisticDto statistic() {
        Integer users= webClient.get()
                .uri("http://"+user_hostname+ ":8092/user/all")
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block().size();
        Set<String> keys=this.getKeyByPattern("order:user*");
        int totalTicket= 0;
        for(String key: keys){
            List<List<String>> orderedSeats= this.hashGetByFieldPrefix(key,"TicketInfo_");
            for (List<String> list: orderedSeats){
                totalTicket+=list.size();
            }
        }
        Integer movies= webClient.get()
                .uri("http://"+movie_hostname+ ":8091/movie/all")
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block().size();
        int revenue= totalTicket *50000;
        return new StatisticDto(users,totalTicket,movies,revenue);
    }


}

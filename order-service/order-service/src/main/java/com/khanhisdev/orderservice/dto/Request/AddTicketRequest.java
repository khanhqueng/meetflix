package com.khanhisdev.orderservice.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AddTicketRequest {
    private String showtime;
    private Long movieId;
    private Long theaterId;
    private Long projectionRoomId;
    private List<String> seats;

}

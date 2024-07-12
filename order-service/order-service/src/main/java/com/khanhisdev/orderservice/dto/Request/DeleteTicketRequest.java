package com.khanhisdev.orderservice.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DeleteTicketRequest {
    private String showtime;
    private Long movieId;
    private Long theaterId;
    private Long projectionRoomId;
}

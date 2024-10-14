package com.khanhisdev.orderservice.dto.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AddTicketRequest {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime showtime;
    private Long movieId;
    private Long theaterId;
    private Long projectionRoomId;
    private List<String> seats;
    private String movieName;
    private String theaterName;
    private String roomName;

}

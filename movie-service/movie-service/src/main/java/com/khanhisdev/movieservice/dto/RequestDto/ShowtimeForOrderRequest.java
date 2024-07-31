package com.khanhisdev.movieservice.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeForOrderRequest {
    private String time;
    private Long roomId;
    private List<String> seatsOrdered;
}

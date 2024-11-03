package com.khanhisdev.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowtimeForOrderDto {
    private Long id;
    private LocalDateTime startTime;
    private String movie;
    private String theater;
    private Integer projectionRoom;
    private List<String> seats;
}

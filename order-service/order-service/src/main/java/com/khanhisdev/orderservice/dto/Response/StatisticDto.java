package com.khanhisdev.orderservice.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDto {
    private Integer userCount;
    private Integer totalTicketSold;
    private Integer totalMovies;
    private Integer revenue;
}

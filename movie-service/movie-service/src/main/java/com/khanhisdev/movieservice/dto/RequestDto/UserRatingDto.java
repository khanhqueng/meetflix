package com.khanhisdev.movieservice.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserRatingDto {
    private Long movieId;
    private BigDecimal rating_point;
}

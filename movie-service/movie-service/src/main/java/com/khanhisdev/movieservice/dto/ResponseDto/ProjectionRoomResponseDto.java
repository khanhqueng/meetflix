package com.khanhisdev.movieservice.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectionRoomResponseDto {
    private Long id;
    private int number;
    private int seats;
}

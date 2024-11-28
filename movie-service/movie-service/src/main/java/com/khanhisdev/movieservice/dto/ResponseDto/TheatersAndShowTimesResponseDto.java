package com.khanhisdev.movieservice.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheatersAndShowTimesResponseDto {
    private Long id;
    private String name;
    private String address;
    private List<ShowtimeResponseDto> showTimes;
}

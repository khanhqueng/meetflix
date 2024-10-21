package com.khanhisdev.movieservice.dto.RequestDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectionRoomRequestDto {
    @NotEmpty(message = "Number must not be empty")
    private int number;
    @NotEmpty(message = "Seats must not be empty")
    private int seats;
}

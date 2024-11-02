package com.khanhisdev.orderservice.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTicketRequest {
    private LocalDateTime time;
    private Long roomId;
    List<String > seatsOrdered;
}

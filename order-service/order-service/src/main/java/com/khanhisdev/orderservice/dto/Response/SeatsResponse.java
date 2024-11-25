package com.khanhisdev.orderservice.dto.Response;

import java.util.List;

public record SeatsResponse(List<SeatDto> allSeats, List<String> orderedSeat) {
}

package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.entity.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> getAllSeatsByRoomId(Long roomId);
}

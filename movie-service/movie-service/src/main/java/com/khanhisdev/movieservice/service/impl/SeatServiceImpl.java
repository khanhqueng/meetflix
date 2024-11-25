package com.khanhisdev.movieservice.service.impl;

import com.khanhisdev.movieservice.entity.ProjectionRoom;
import com.khanhisdev.movieservice.entity.Seat;
import com.khanhisdev.movieservice.exception.ResourceNotFoundException;
import com.khanhisdev.movieservice.repository.ProjectionRoomRepository;
import com.khanhisdev.movieservice.repository.SeatRepository;
import com.khanhisdev.movieservice.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final ProjectionRoomRepository projectionRoomRepository;

    @Override
    public List<Seat> getAllSeatsByRoomId(Long roomId) {
        ProjectionRoom room = projectionRoomRepository.findById(roomId).orElseThrow(
                ()-> new ResourceNotFoundException("Room", "id", roomId)
        );
        List<Seat> seats= seatRepository.findByProjectionRoomId(room.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Seats", "room id", roomId)
        );
        return seats;
    }
}

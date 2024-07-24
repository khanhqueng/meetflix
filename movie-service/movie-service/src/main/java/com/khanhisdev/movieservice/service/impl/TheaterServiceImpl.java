package com.khanhisdev.movieservice.service.impl;

import com.khanhisdev.movieservice.dto.Mapper.TheaterMapper;
import com.khanhisdev.movieservice.dto.RequestDto.TheaterRequestDto;
import com.khanhisdev.movieservice.entity.Theater;
import com.khanhisdev.movieservice.exception.ResourceDuplicateException;
import com.khanhisdev.movieservice.exception.ResourceNotFoundException;
import com.khanhisdev.movieservice.repository.TheaterRepository;
import com.khanhisdev.movieservice.service.TheaterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class TheaterServiceImpl implements TheaterService {
    private TheaterRepository theaterRepository;
    private TheaterMapper mapper;
    @Override
    public List<TheaterRequestDto> getAllTheaters() {
        return theaterRepository.findAll().stream().map(theater -> mapper.mapToDto(theater)).toList();
    }

    @Override
    public TheaterRequestDto getTheaterById(Long id) {

        return mapper.mapToDto(theaterRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Theater","id",id)));
    }

    @Override
    public TheaterRequestDto createTheater(TheaterRequestDto theaterRequestDto) {
        if(theaterRepository.existsByName(theaterRequestDto.getName())){
            throw new ResourceDuplicateException("Theater", "name", theaterRequestDto.getName());
        }
        Theater theater= mapper.mapToEntity(theaterRequestDto);
        theater.getProjectionRoomList().forEach(projectionRoom -> projectionRoom.setTheater(theater));
        return mapper.mapToDto(theaterRepository.save(theater));
    }

    @Override
    public TheaterRequestDto updateTheater(TheaterRequestDto theaterRequestDto, Long id) {
        Theater theater= theaterRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Theater", "id", id));
        theater.setName(theaterRequestDto.getName());
        return mapper.mapToDto(theater);
    }

    @Override
    public void  deleteTheater(Long id) {
        Theater theater= theaterRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Theater", "id", id));
        theaterRepository.delete(theater);
    }
}

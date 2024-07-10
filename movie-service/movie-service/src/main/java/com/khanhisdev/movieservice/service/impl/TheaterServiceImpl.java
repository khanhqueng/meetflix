package com.khanhisdev.movieservice.service.impl;

import com.khanhisdev.movieservice.dto.Mapper.TheaterMapper;
import com.khanhisdev.movieservice.dto.RequestDto.TheaterDto;
import com.khanhisdev.movieservice.entity.Theater;
import com.khanhisdev.movieservice.exception.ResourceDuplicateException;
import com.khanhisdev.movieservice.exception.ResourceNotFoundException;
import com.khanhisdev.movieservice.repository.TheaterRepository;
import com.khanhisdev.movieservice.service.TheaterService;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TheaterServiceImpl implements TheaterService {
    private TheaterRepository theaterRepository;
    private TheaterMapper mapper;
    @Override
    public List<TheaterDto> getAllTheaters() {
        return theaterRepository.findAll().stream().map(theater -> mapper.mapToDto(theater)).toList();
    }

    @Override
    public TheaterDto getTheaterById(Long id) {

        return mapper.mapToDto(theaterRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Theater","id",id)));
    }

    @Override
    public TheaterDto createTheater(TheaterDto theaterDto) {
        if(theaterRepository.existsByName(theaterDto.getName())){
            throw new ResourceDuplicateException("Theater", "name", theaterDto.getName());
        }
        Theater theater= mapper.mapToEntity(theaterDto);
        theater.getProjectionRoomList().forEach(projectionRoom -> projectionRoom.setTheater(theater));
        return mapper.mapToDto(theaterRepository.save(theater));
    }

    @Override
    public TheaterDto updateTheater(TheaterDto theaterDto, Long id) {
        Theater theater= theaterRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Theater", "id", id));
        theater.setName(theaterDto.getName());
        return mapper.mapToDto(theater);
    }

    @Override
    public void  deleteTheater(Long id) {
        Theater theater= theaterRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Theater", "id", id));
        theaterRepository.delete(theater);
    }
}

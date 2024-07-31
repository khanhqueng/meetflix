package com.khanhisdev.movieservice;

import com.khanhisdev.movieservice.dto.ResponseDto.ShowtimeForOrderDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ShowtimeResponseDto;
import com.khanhisdev.movieservice.entity.Showtime;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieServiceApplication {
	@Bean
	public ModelMapper modelMapper(){
		ModelMapper mapper= new ModelMapper();
		mapper.typeMap(Showtime.class, ShowtimeForOrderDto.class).addMappings(map->{
			map.map( src-> src.getMovie().getName(), ShowtimeForOrderDto::setMovieName );
			map.map(src-> src.getTheater().getName(), ShowtimeForOrderDto::setTheaterName);
			map.map(src-> src.getProjectionRoom().getNumber(), ShowtimeForOrderDto::setProjectionRoomName);
		});
		return mapper;
	}


	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}

}

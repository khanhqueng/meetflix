package com.khanhisdev.movieservice;

import com.khanhisdev.movieservice.dto.ResponseDto.ShowtimeForOrderDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ShowtimeResponseDto;
import com.khanhisdev.movieservice.entity.Showtime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}

}

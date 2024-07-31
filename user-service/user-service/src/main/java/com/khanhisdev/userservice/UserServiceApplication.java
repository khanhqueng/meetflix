package com.khanhisdev.userservice;

import com.khanhisdev.userservice.dto.ResponseDto.CommentResponseDto;
import com.khanhisdev.userservice.dto.ResponseDto.UserResponseDto;
import com.khanhisdev.userservice.entity.Comment;
import com.khanhisdev.userservice.entity.LikedMovie;
import com.khanhisdev.userservice.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class UserServiceApplication {
	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}
	@Bean
	public ModelMapper modelMapper(){
		ModelMapper mapper= new ModelMapper();
		mapper.typeMap(Comment.class, CommentResponseDto.class).addMappings(map->{
			map.map(src->src.getUserId().getUsername(), CommentResponseDto::setUsername);
		});
		return mapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}

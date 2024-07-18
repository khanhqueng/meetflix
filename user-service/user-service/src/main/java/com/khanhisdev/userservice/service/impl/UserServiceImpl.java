package com.khanhisdev.userservice.service.impl;

import com.khanhisdev.userservice.dto.Mapper.UserMapper;
import com.khanhisdev.userservice.dto.RequestDto.MovieDto;
import com.khanhisdev.userservice.dto.RequestDto.UserDto;
import com.khanhisdev.userservice.dto.ResponseDto.APIResponseDto;
import com.khanhisdev.userservice.entity.LikedMovie;
import com.khanhisdev.userservice.entity.Role;
import com.khanhisdev.userservice.entity.User;
import com.khanhisdev.userservice.exception.ResourceDuplicateException;
import com.khanhisdev.userservice.exception.ResourceNotFoundException;
import com.khanhisdev.userservice.repository.RoleRepository;
import com.khanhisdev.userservice.repository.UserRepository;
import com.khanhisdev.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Value("${movie.host}")
    private String movie_hostname;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private WebClient webClient;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserDto createUser(UserDto userDto){

        if(userRepository.existsByUsername(userDto.getUsername())){
            throw new ResourceDuplicateException("user","username",userDto.getUsername());
        }
        User user= mapper.mapToEntity(userDto);
        passwordEncoder= new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Set<Role> roles= new HashSet<>();
        roles.add(roleRepository.findByName("USER").get());
        user.setRoles(roles);
        return mapper.mapToDto(userRepository.save(user));


    }

    @Override
    public APIResponseDto getUserById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        List<Long> ids = user.getMovieId().stream().map(LikedMovie::getMovieId).toList();
        String result = ids.stream()
                .map(String::valueOf) // Convert Long to String
                .collect(Collectors.joining(","));
        List<MovieDto> movieDtoList= webClient.get()
                .uri("http://"+movie_hostname+":8091/movie/ids?ids="+ result)
                .retrieve()
                .bodyToFlux(MovieDto.class)
                .collectList()
                .block();
        APIResponseDto apiResponseDto= new APIResponseDto(mapper.mapToDto(user),movieDtoList);
        return apiResponseDto;
    }

    @Override
    public UserDto getUserByUsername(String userName) {
        User user = this.userRepository.findByUsername(userName).orElseThrow(()->new UsernameNotFoundException("User not found with username: "+userName));
        return this.mapper.mapToDto(user);
    }
}

package com.khanhisdev.userservice.service.impl;

import com.khanhisdev.userservice.dto.Mapper.UserMapper;
import com.khanhisdev.userservice.dto.Model.MovieDto;
import com.khanhisdev.userservice.dto.Model.UserDto;
import com.khanhisdev.userservice.dto.Response.APIResponseDto;
import com.khanhisdev.userservice.entity.Role;
import com.khanhisdev.userservice.entity.User;
import com.khanhisdev.userservice.exception.ResourceDuplicateException;
import com.khanhisdev.userservice.exception.ResourceNotFoundException;
import com.khanhisdev.userservice.repository.RoleRepository;
import com.khanhisdev.userservice.repository.UserRepository;
import com.khanhisdev.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserMapper mapper;
    private PasswordEncoder passwordEncoder;
    private WebClient webClient;
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
        MovieDto movieDto= webClient.get()
                .uri("http://localhost:8091/api/v1/movie/"+ user.getMovieId())
                .retrieve()
                .bodyToMono(MovieDto.class)
                .block();
        APIResponseDto apiResponseDto= new APIResponseDto(mapper.mapToDto(user),movieDto);
        return apiResponseDto;
    }

    @Override
    public UserDto getUserByUsername(String userName) {
        User user = this.userRepository.findByUsername(userName);
        return this.mapper.mapToDto(user);
    }
}

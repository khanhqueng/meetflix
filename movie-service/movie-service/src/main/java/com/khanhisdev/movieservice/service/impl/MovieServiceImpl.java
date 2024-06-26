package com.khanhisdev.movieservice.service.impl;

import com.khanhisdev.movieservice.dto.Mapper.MovieMapper;
import com.khanhisdev.movieservice.dto.Model.MovieDto;
import com.khanhisdev.movieservice.dto.Response.ObjectResponse;
import com.khanhisdev.movieservice.entity.Category;
import com.khanhisdev.movieservice.entity.Movie;
import com.khanhisdev.movieservice.exception.ResourceDuplicateException;
import com.khanhisdev.movieservice.exception.ResourceNotFoundException;
import com.khanhisdev.movieservice.repository.CategoryRepository;
import com.khanhisdev.movieservice.repository.MovieRepository;
import com.khanhisdev.movieservice.service.MovieService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieMapper mapper;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public MovieDto saveMovie(MovieDto movieDto) {
        List<Category> categories= categoryRepository.findAll();
        Movie movie = movieRepository.findByName(movieDto.getName());
        if(movie != null){
            throw new ResourceDuplicateException("Movie", "name", movieDto.getName());

        }else{
        Movie newMovie= movieRepository.save(mapper.mapToEntity(movieDto));
        return mapper.mapToDto(newMovie);
        }
    }

    @Override
    public MovieDto getMovieByName(Long id) {
        Movie movie= movieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Movie","name",id));
        return mapper.mapToDto(movie);

    }

    @Override
    public ObjectResponse<MovieDto> getAllMovies(int pageNo, int pageSize, String sortBy, String sortDir) {
        // create sort
        Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        //create pageable instance
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);

        Page<Movie> movies= movieRepository.findAll(pageable);
        List<Movie> movieList= movies.getContent();
        List<MovieDto> content= movieList.stream().map(movie -> mapper.mapToDto(movie)).collect(Collectors.toList());
        return new ObjectResponse<MovieDto>(
            content, movies.getNumber(),movies.getSize(),movies.getTotalElements(),movies.getTotalPages(),movies.isLast()
        );

    }


}

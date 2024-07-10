package com.khanhisdev.movieservice.service.impl;

import com.khanhisdev.movieservice.dto.Mapper.CategoryMapper;
import com.khanhisdev.movieservice.dto.Mapper.MovieMapper;
import com.khanhisdev.movieservice.dto.Message.CategoryResponseDto;
import com.khanhisdev.movieservice.dto.RequestDto.MovieDto;
import com.khanhisdev.movieservice.dto.Response.MovieResponseDto;
import com.khanhisdev.movieservice.dto.Response.ObjectResponse;
import com.khanhisdev.movieservice.entity.Category;
import com.khanhisdev.movieservice.entity.Movie;
import com.khanhisdev.movieservice.exception.ResourceDuplicateException;
import com.khanhisdev.movieservice.exception.ResourceNotFoundException;
import com.khanhisdev.movieservice.repository.CategoryRepository;
import com.khanhisdev.movieservice.repository.MovieRepository;
import com.khanhisdev.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public MovieResponseDto saveMovie(MovieDto movieDto) {
        List<Category> categories= categoryRepository.findAll();
        Movie movie = movieRepository.findByName(movieDto.getName());
        if(movie != null){
            throw new ResourceDuplicateException("Movie", "name", movieDto.getName());

        }else{
            List<Category> categoryList= categoryRepository.findAll();
            List<CategoryResponseDto > categoryNeedCheck= movieDto.getCategories();
            List<CategoryResponseDto> listWillBeUpdated= new ArrayList<>();
            boolean flag=false;
            for(int i =0;i<categoryNeedCheck.size();i++){
                flag=false;
                for(int j=0;j<categoryList.size();j++){
                    if (categoryNeedCheck.get(i).equals(categoryList.get(j))) {
                        listWillBeUpdated.add(categoryMapper.mapToDto(categoryList.get(j)));
                        flag= true;
                        break;
                    };
                }
                if(flag== false) {
                    Category category = categoryRepository.save(categoryMapper.mapToEntity(categoryNeedCheck.get(i)));
                    listWillBeUpdated.add(categoryMapper.mapToDto(category));
                }
            }
        movieDto.setCategories(listWillBeUpdated);
        Movie newMovie= movieRepository.save(movieMapper.mapToEntity(movieDto));
        return movieMapper.mapToResponseDto(newMovie);
        }
    }

    @Override
    public MovieResponseDto getMovieById(Long id) {
        Movie movie= movieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Movie","name",id));
        return movieMapper.mapToResponseDto(movie);

    }

    @Override
    public ObjectResponse<MovieResponseDto> getAllMovies(int pageNo, int pageSize, String sortBy, String sortDir) {
        // create sort
        Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        //create pageable instance
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);

        Page<Movie> movies= movieRepository.findAll(pageable);
        List<Movie> movieList= movies.getContent();
        List<MovieResponseDto> content= movieList.stream().map(movie -> movieMapper.mapToResponseDto(movie)).collect(Collectors.toList());
        return new ObjectResponse<MovieResponseDto>(
            content, movies.getNumber(),movies.getSize(),movies.getTotalElements(),movies.getTotalPages(),movies.isLast()
        );

    }

    @Override
    public List<MovieResponseDto> getMoviesByIds(List<Long> ids) {
        return movieRepository.findAllByIdIn(ids).stream().map(movie -> movieMapper.mapToResponseDto(movie)).collect(Collectors.toList());
    }


}

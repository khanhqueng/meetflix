package com.khanhisdev.movieservice.service.impl;

import com.khanhisdev.movieservice.dto.Mapper.CategoryMapper;
import com.khanhisdev.movieservice.dto.Mapper.MovieMapper;
import com.khanhisdev.movieservice.dto.Message.CategoryMessage;
import com.khanhisdev.movieservice.dto.RequestDto.MovieRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.MovieResponseDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ObjectResponse;
import com.khanhisdev.movieservice.entity.Actor;
import com.khanhisdev.movieservice.entity.Category;
import com.khanhisdev.movieservice.entity.Director;
import com.khanhisdev.movieservice.entity.Movie;
import com.khanhisdev.movieservice.exception.ResourceDuplicateException;
import com.khanhisdev.movieservice.exception.ResourceNotFoundException;
import com.khanhisdev.movieservice.repository.*;
import com.khanhisdev.movieservice.service.MovieService;
import com.netflix.discovery.converters.Auto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final TheaterRepository theaterRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    @Override
    @Transactional
    public MovieResponseDto saveMovie(MovieRequestDto movieDto) {
        Movie movie = movieRepository.findByName(movieDto.getName());
        if(movie != null){
            throw new ResourceDuplicateException("Movie", "name", movieDto.getName());

        }else{
            // handle Category check
            List<Category> categoryList= categoryRepository.findAll();
            List<CategoryMessage> categoryNeedCheck= movieDto.getCategories();
            List<CategoryMessage> listWillBeUpdated= new ArrayList<>();
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
                if(!flag) {
                    Category category = categoryRepository.save(categoryMapper.mapToEntity(categoryNeedCheck.get(i)));
                    listWillBeUpdated.add(categoryMapper.mapToDto(category));
                }
            }
            List<Director> directors= new ArrayList<>();
            // handle Director check
            for(Director director: movieDto.getDirector()){
                if(directorRepository.findByName(director.getName()).isPresent()){
                    directors.add(directorRepository.findByName(director.getName()).get());
                }
                else {
                    directors.add(directorRepository.save(director));

                }
            }
            List<Actor> actors= new ArrayList<>();
            // handle Actor check
            for(Actor actor: movieDto.getActors()){
                if(actorRepository.findByName(actor.getName()).isPresent()){
                    actors.add(actorRepository.findByName(actor.getName()).get());
                }
                else {
                    actors.add(actorRepository.save(actor));

                }
            }
            movieDto.setDirector(directors);
            movieDto.setActors(actors);
            movieDto.setCategories(listWillBeUpdated);
            Movie newMovie= movieRepository.save(movieMapper.mapToEntity(movieDto));
            return movieMapper.mapToResponseDto(newMovie);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public MovieResponseDto getMovieById(Long id) {
        Movie movie= movieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Movie","name",id));
        return movieMapper.mapToResponseDto(movie);

    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<MovieResponseDto> getAllMoviesFromTheater(Long theaterId) {
        if( !theaterRepository.existsById(theaterId)) throw new ResourceNotFoundException("Theater","id",theaterId);
        List<Movie> movies= movieRepository.findByShowtimeListTheaterId(theaterId);
        return movies.stream().map(movie -> movieMapper.mapToResponseDto(movie)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieResponseDto> getMoviesByIds(List<Long> ids) {
        return movieRepository.findAllByIdIn(ids).stream().map(movie -> movieMapper.mapToResponseDto(movie)).collect(Collectors.toList());
    }


}

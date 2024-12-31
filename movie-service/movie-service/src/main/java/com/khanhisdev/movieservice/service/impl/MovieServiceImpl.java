package com.khanhisdev.movieservice.service.impl;

import com.khanhisdev.movieservice.dto.Mapper.CategoryMapper;
import com.khanhisdev.movieservice.dto.Mapper.MovieMapper;
import com.khanhisdev.movieservice.dto.Message.CategoryMessage;
import com.khanhisdev.movieservice.dto.RequestDto.MovieRequestDto;
import com.khanhisdev.movieservice.dto.RequestDto.MovieUpdateDto;
import com.khanhisdev.movieservice.dto.RequestDto.UserRatingDto;
import com.khanhisdev.movieservice.dto.ResponseDto.MovieResponseDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ObjectResponse;
import com.khanhisdev.movieservice.dto.ResponseDto.RatingPointResponseDto;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
            // check director
            Set<Director> directors= new HashSet<>();
            for(Director director: movieDto.getDirector()){
                if(directorRepository.existsByName(director.getName())){
                    directors.add(directorRepository.findByName(director.getName()));
                    continue;
                }
                directors.add(director);
            }
            // check actor
            Set<Actor> actors= new HashSet<>();
            for(Actor actor: movieDto.getActors()){
                if(actorRepository.existsByName(actor.getName())){
                    actors.add(actorRepository.findByName(actor.getName()));
                    continue;
                }
                actors.add(actor);
            }
            Set<Category> categories = new HashSet<>();
            movieDto.getCategory_id().forEach(category -> {
                categories.add(categoryRepository.findById(category).
                        orElseThrow(()-> new ResourceNotFoundException("Category", "id", category)));
            });
            Movie newMovie= movieMapper.mapToEntity(movieDto);
            newMovie.setActors(actors);
            newMovie.setCategories(categories);
            newMovie.setDirector(directors);
            return movieMapper.mapToDto(movieRepository.save(newMovie));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public MovieResponseDto getMovieById(Long id) {
        Movie movie= movieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Movie","name",id));
        return movieMapper.mapToDto(movie);

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
        List<MovieResponseDto> content= movieList.stream().map(movieMapper::mapToDto).collect(Collectors.toList());
        return new ObjectResponse<MovieResponseDto>(
            content, movies.getNumber(),movies.getSize(),movies.getTotalElements(),movies.getTotalPages(),movies.isLast()
        );

    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieResponseDto> getAllMoviesFromTheater(Long theaterId) {
        if( !theaterRepository.existsById(theaterId)) throw new ResourceNotFoundException("Theater","id",theaterId);
        List<Movie> movies= movieRepository.findByShowtimeListTheaterId(theaterId);
        return movies.stream().map(movieMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieResponseDto> getMoviesByIds(List<Long> ids) {
        return movieRepository.findAllByIdIn(ids).stream().map(movieMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public RatingPointResponseDto ratingMovie(UserRatingDto userRatingDto) {
        Movie movie= movieRepository.findById(userRatingDto.getMovieId()).orElseThrow(
                ()-> new ResourceNotFoundException("Movie", "id", userRatingDto.getMovieId())
        );
        Double totalScore= movie.getRatingPoint().doubleValue() * movie.getRatings();
        Double newScore= totalScore + userRatingDto.getRating_point().doubleValue();
        Integer newRatings= movie.getRatings()+1;
        Double newRatingPoint= newScore/newRatings;
        movie.setRatings(newRatings);
        movie.setRatingPoint(BigDecimal.valueOf(newRatingPoint));
        Movie updatedMovie= movieRepository.save(movie);
        return new RatingPointResponseDto(updatedMovie.getId(),updatedMovie.getRatingPoint());
    }

    @Override
    public ObjectResponse<MovieResponseDto> findMoviesByFullSearchText(String keyword, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        //create pageable instance
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);

        Page<Movie> page= movieRepository.findMoviesBySearchText(keyword,pageable);
        List<Movie> movies= page.getContent();
        List<MovieResponseDto> content= movies.stream().map(movieMapper::mapToDto).collect(Collectors.toList());
        return new ObjectResponse<MovieResponseDto>(
                content, page.getNumber(),page.getSize(),page.getTotalElements(),page.getTotalPages(),page.isLast()
        );
    }
    @Override
    public MovieResponseDto updateMovie(MovieUpdateDto updateDto, Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                ()-> new ResourceNotFoundException("Movie", "id", movieId)
        );
        // check director
        Set<Director> directors= new HashSet<>();
        for(Director director: updateDto.getDirector()){
            if(directorRepository.existsByName(director.getName())){
                directors.add(directorRepository.findByName(director.getName()));
                continue;
            }
            directors.add(director);
        }
        // check actor
        Set<Actor> actors= new HashSet<>();
        for(Actor actor: updateDto.getActors()){
            if(actorRepository.existsByName(actor.getName())){
                actors.add(actorRepository.findByName(actor.getName()));
                continue;
            }
            actors.add(actor);
        }
        // check category
        Set<Category> categories= new HashSet<>();
        for(Category category: updateDto.getCategories()){
            if(categoryRepository.existsByName(category.getName())){
                categories.add(categoryRepository.findByName(category.getName()));
                continue;
            }
            categories.add(category);
        }
        movie.setActors(actors);
        movie.setDirector(directors);
        movie.setCategories(categories);
        movie.setName(updateDto.getName());
        movie.setCountry(updateDto.getCountry());
        movie.setDescription(updateDto.getDescription());
        movie.setDurationMin(updateDto.getDurationMin());
        movie.setRatingPoint(BigDecimal.valueOf(updateDto.getRatingPoint()));
        movie.setReleaseDate(updateDto.getReleaseDate());
        movie.setUrlImage(updateDto.getUrlImage());
        return movieMapper.mapToDto(movieRepository.save(movie));
    }

    @Override
    public void deleteMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                ()-> new ResourceNotFoundException("Movie", "id", movieId)
        );
        movieRepository.delete(movie);
    }

    @Override
    public List<MovieResponseDto> getAllWithoutPaging() {
        return movieRepository.findAll().stream().map(movie -> movieMapper.mapToDto(movie)).toList();
    }


}

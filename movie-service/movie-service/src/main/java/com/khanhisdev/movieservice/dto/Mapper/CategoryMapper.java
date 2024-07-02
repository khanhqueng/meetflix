package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.movieservice.dto.Message.CategoryResponseDto;
import com.khanhisdev.movieservice.dto.Model.MovieDto;
import com.khanhisdev.movieservice.entity.Category;
import com.khanhisdev.movieservice.entity.Movie;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CategoryMapper {
    @Autowired
    private ModelMapper mapper;
    public Category mapToEntity(CategoryResponseDto categoryResponseDto){
        Category category= mapper.map(categoryResponseDto, Category.class);
        return category;

    }
    public CategoryResponseDto mapToDto(Category category){
        CategoryResponseDto categoryResponseDto= mapper.map(category, CategoryResponseDto.class);
        return categoryResponseDto;
    }
}

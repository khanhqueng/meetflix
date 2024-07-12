package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.movieservice.dto.Message.CategoryMessage;
import com.khanhisdev.movieservice.entity.Category;
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
    public Category mapToEntity(CategoryMessage categoryMessage){
        Category category= mapper.map(categoryMessage, Category.class);
        return category;

    }
    public CategoryMessage mapToDto(Category category){
        CategoryMessage categoryMessage = mapper.map(category, CategoryMessage.class);
        return categoryMessage;
    }
}

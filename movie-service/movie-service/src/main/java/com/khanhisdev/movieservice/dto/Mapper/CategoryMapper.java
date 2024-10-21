package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.movieservice.dto.Message.CategoryMessage;
import com.khanhisdev.movieservice.entity.Category;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper
public interface CategoryMapper extends GenericMapper<Category, CategoryMessage, CategoryMessage>{

}

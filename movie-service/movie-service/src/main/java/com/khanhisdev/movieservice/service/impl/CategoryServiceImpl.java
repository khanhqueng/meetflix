package com.khanhisdev.movieservice.service.impl;

import com.khanhisdev.movieservice.dto.Mapper.CategoryMapper;
import com.khanhisdev.movieservice.dto.Message.CategoryMessage;
import com.khanhisdev.movieservice.entity.Category;
import com.khanhisdev.movieservice.exception.ResourceDuplicateException;
import com.khanhisdev.movieservice.repository.CategoryRepository;
import com.khanhisdev.movieservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public CategoryMessage createCategory(CategoryMessage request) {
        if(categoryRepository.existsByName(request.getName()))
            throw new ResourceDuplicateException("Category", "name", request.getName());
        Category category= categoryMapper.mapToEntity(request);
        return categoryMapper.mapToDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryMessage> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::mapToDto).toList();
    }
}

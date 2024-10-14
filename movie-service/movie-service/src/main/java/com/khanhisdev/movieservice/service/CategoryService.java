package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.dto.Message.CategoryMessage;

import java.util.List;

public interface CategoryService {
    CategoryMessage createCategory(CategoryMessage request);
    List<CategoryMessage> getAllCategories();
}

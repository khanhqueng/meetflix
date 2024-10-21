package com.khanhisdev.movieservice.controller;

import com.khanhisdev.movieservice.dto.Message.CategoryMessage;
import com.khanhisdev.movieservice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Tag(name = "Category Controller")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @Operation(summary = "Get all category", description = "API for get all category")
    @GetMapping
    public ResponseEntity<List<CategoryMessage>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }
    @Operation(summary = "Add new category", description = "API for create new category")
    @PostMapping
    public ResponseEntity<CategoryMessage> createCategory(@RequestBody CategoryMessage requestDto){
        return new ResponseEntity<>(categoryService.createCategory(requestDto), HttpStatus.CREATED);
    }
}

package com.example.Blog_App.controller;

import com.example.Blog_App.payloads.CategoryDTO;
import com.example.Blog_App.implement.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/categ")
    public List<CategoryDTO> getAllCategories(Pageable pageable){
        return categoryService.getAllCategories(pageable);
    }

    @GetMapping("/cat/{id}")
    public CategoryDTO getCatById(@PathVariable int id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/cat/{id}")
    public CategoryDTO modifyCategory(@Valid @RequestBody CategoryDTO cat, @PathVariable int id){
        return categoryService.updateCategory(cat,id);
    }

    @DeleteMapping("/cat/{id}")
    public void removeCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
    }

    @PostMapping("/cat")
    public CategoryDTO addCategory( @RequestBody CategoryDTO cat){
        return categoryService.addCategory(cat);
    }
}

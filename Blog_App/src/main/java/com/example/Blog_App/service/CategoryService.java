package com.example.Blog_App.service;

import com.example.Blog_App.payloads.CategoryDTO;
import com.example.Blog_App.payloads.UserDTO;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface CategoryService {

       CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO cat, int cat_id);

    CategoryDTO getCategoryById(int cat_id);

    List<CategoryDTO> getAllCategories(Pageable pageable);

    void deleteCategory(int cat_id);
}

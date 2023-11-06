package com.example.Blog_App.service;

import com.example.Blog_App.entity.Category;
import com.example.Blog_App.entity.User;
import com.example.Blog_App.exceptions.ResourceNotFoundException;
import com.example.Blog_App.payloads.CategoryDTO;
import com.example.Blog_App.payloads.UserDTO;
import com.example.Blog_App.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category cat = this.dtoToCategory(categoryDTO);
        Category save = categoryRepository.save(cat);
        CategoryDTO dto = this.categoryToDto(save);
        return dto;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO cat, int cat_id) {
       CategoryDTO categoryDTO = getCategoryById(cat_id);
        categoryDTO.setCatTitle(cat.getCatTitle() !=null ? cat.getCatTitle() : categoryDTO.getCatTitle());
        categoryDTO.setCatDescription(cat.getCatDescription()!=null ? cat.getCatDescription() : categoryDTO.getCatDescription());
        Category newCat = dtoToCategory(categoryDTO);
        return categoryToDto(categoryRepository.save(newCat));
    }

    @Override
    public CategoryDTO getCategoryById(int cat_id) {
        Category cat = categoryRepository.findById(cat_id).orElseThrow(()-> new ResourceNotFoundException("Category not found with Id: "+cat_id));
        return this.categoryToDto(cat);
    }

    @Override
    public List<CategoryDTO> getAllCategories(Pageable pageable) {
        List<Category> cat = categoryRepository.findAll(pageable).toList();
        List<CategoryDTO> dto = cat.stream().map(c->categoryToDto(c)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public void deleteCategory(int cat_id) {
        CategoryDTO dto = getCategoryById(cat_id);
        Category cat = this.dtoToCategory(dto);
        categoryRepository.delete(cat);

    }

    public Category dtoToCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        return category;
    }

    public CategoryDTO categoryToDto(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category,categoryDTO);
        return categoryDTO;
    }
}

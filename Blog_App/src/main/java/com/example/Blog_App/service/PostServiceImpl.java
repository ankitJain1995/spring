package com.example.Blog_App.service;

import com.example.Blog_App.repository.CategoryRepository;
import com.example.Blog_App.repository.PostRepository;
import com.example.Blog_App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;
}

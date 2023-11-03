package com.example.Blog_App.repository;

import com.example.Blog_App.entity.Category;
import com.example.Blog_App.entity.Post;
import com.example.Blog_App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}

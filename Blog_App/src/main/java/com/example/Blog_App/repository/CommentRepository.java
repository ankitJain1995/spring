package com.example.Blog_App.repository;

import com.example.Blog_App.entity.Comment;
import com.example.Blog_App.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPost(Post post);
}

package com.example.Blog_App.service;

import com.example.Blog_App.entity.Post;
import com.example.Blog_App.payloads.PostDTO;

import java.util.List;

public interface PostService {

    Post addPost(PostDTO postDTO,int user_id,int cat_id);

    Post updatePost(PostDTO postDTO);

    Post getPostById(int id);

    List<Post> getAllPost();

    public void deletePost(int id);
}
